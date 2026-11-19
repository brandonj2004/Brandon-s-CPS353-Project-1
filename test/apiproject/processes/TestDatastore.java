package apiproject.processes;

import apiproject.conceptual.OutputValue;
import apiproject.network.Delimiters;
import apiproject.network.InputSource;
import apiproject.network.OutputSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

public class TestDatastore {

  @TempDir
  Path tempDir;

  @Test
  public void testExtractInputFromFile() throws Exception {
    Path inputFile = tempDir.resolve("input.txt");
    Files.write(inputFile, Arrays.asList("1", "2", "3"));

    DatastoreImpl datastore = new DatastoreImpl();

    Job job = new Job(
        new InputSource(inputFile.toString()),
        new OutputSource(),
        new Delimiters(","));

    InputForCompute input = datastore.extractInputFromJob(job);

    assertEquals(Arrays.asList(1, 2, 3), input.getValues());
  }

  @Test
  public void testExtractInputWithCommaSeparatedValues() throws Exception {
    Path inputFile = tempDir.resolve("input.csv");
    Files.write(inputFile, Arrays.asList("1,2,3"));

    DatastoreImpl datastore = new DatastoreImpl();

    Job job = new Job(
        new InputSource(inputFile.toString()),
        new OutputSource(),
        new Delimiters(","));

    InputForCompute input = datastore.extractInputFromJob(job);

    assertEquals(Arrays.asList(1, 2, 3), input.getValues());
  }

  @Test
  public void testWriteOutputToFile() throws Exception {
    Path outputFile = tempDir.resolve("output.txt");

    DatastoreImpl datastore = new DatastoreImpl();

    Job job = new Job(
        new InputSource(),
        new OutputSource(outputFile.toString(), new Delimiters(",")),
        new Delimiters(","));

    OutputValue output = new OutputValue() {
      @Override
      public List<String> getValues() {
        return Arrays.asList("1", "2 -> 1", "3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1");
      }
    };

    datastore.writeOutput(job, output);

    String writtenText = Files.readString(outputFile);

    assertEquals(
        "1,2 -> 1,3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1",
        writtenText);
  }

  @Test
  public void testMissingInputFilePathThrowsException() {
    DatastoreImpl datastore = new DatastoreImpl();

    Job job = new Job(
        new InputSource(),
        new OutputSource(),
        new Delimiters(","));

    assertThrows(
        IllegalArgumentException.class,
        () -> datastore.extractInputFromJob(job));
  }

  @Test
  public void testMissingOutputFilePathThrowsException() {
    DatastoreImpl datastore = new DatastoreImpl();

    Job job = new Job(
        new InputSource(),
        new OutputSource(),
        new Delimiters(","));

    OutputValue output = new OutputValue() {
      @Override
      public List<String> getValues() {
        return Arrays.asList("1", "2");
      }
    };

    assertThrows(
        IllegalArgumentException.class,
        () -> datastore.writeOutput(job, output));
  }

  @Test
  public void testGetRecentJobIDGeneratesUniqueIDs() {
    DatastoreImpl datastore = new DatastoreImpl();

    JobIdentifier firstID = datastore.getRecentJobID();
    JobIdentifier secondID = datastore.getRecentJobID();

    assertTrue(firstID.getID() > 0);
    assertNotEquals(firstID.getID(), secondID.getID());
  }

  @Test
  public void testSetIDAndWriteJobStoreJob() {
    DatastoreImpl datastore = new DatastoreImpl();

    Job job = new Job(
        new InputSource("input.txt"),
        new OutputSource("output.txt"),
        new Delimiters(","));

    JobIdentifier jobId = datastore.getRecentJobID();

    assertNotNull(datastore.setID(job, jobId));
    assertNotNull(datastore.writeJob(job, Status.INCOMPLETE));

    assertSame(job, datastore.getJob(jobId));
    assertEquals(Status.INCOMPLETE, datastore.isComplete(job));
  }

  @Test
  public void testRequestJobRecognizesStoredJob() {
    DatastoreImpl datastore = new DatastoreImpl();

    Job job = new Job(
        new InputSource("input.txt"),
        new OutputSource("output.txt"),
        new Delimiters(","));

    JobIdentifier jobId = datastore.getRecentJobID();

    datastore.setID(job, jobId);
    datastore.writeJob(job, Status.COMPLETE);

    assertNotNull(datastore.requestJob(jobId));
  }

  @Test
  public void testRequestJobReturnsNullForUnknownID() {
    DatastoreImpl datastore = new DatastoreImpl();

    assertNull(datastore.requestJob(new JobIdentifier(999)));
    assertNull(datastore.getJob(new JobIdentifier(999)));
  }
}
