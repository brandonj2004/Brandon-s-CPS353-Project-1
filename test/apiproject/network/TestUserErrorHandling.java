package apiproject.network;

import apiproject.conceptual.ComputeEngine;
import apiproject.conceptual.ComputationResult;
import apiproject.conceptual.OutputValue;
import apiproject.processes.Datastore;
import apiproject.processes.InputForCompute;
import apiproject.processes.Job;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUserErrorHandling {

  @Test
  public void testDatastoreExceptionReturnsProcessingError() {
    Datastore datastore = mock(Datastore.class);
    ComputeEngine computeEngine = mock(ComputeEngine.class);
    UserImpl user = new UserImpl(datastore, computeEngine);

    Job job = new Job(
        new InputSource("missing-input.txt"),
        new OutputSource("output.txt"),
        new Delimiters(","));

    when(datastore.extractInputFromJob(job))
        .thenThrow(new IllegalStateException("Unable to read input file."));

    JobSubmissionResponse response = user.submitJob(job);

    assertNotNull(response);
    assertEquals(JobResponseStatus.PROCESSING_ERROR, response.getStatus());
    assertFalse(response.isSuccessful());
    assertEquals("Unable to read input file.", response.getErrorMessage());
  }

  @Test
  public void testNullJobReturnsInvalidInput() {
    UserImpl user = new UserImpl(
        mock(Datastore.class),
        mock(ComputeEngine.class));

    JobSubmissionResponse response = user.submitJob(null);

    assertNotNull(response);
    assertEquals(JobResponseStatus.INVALID_INPUT, response.getStatus());
    assertFalse(response.isSuccessful());
    assertTrue(response.getErrorMessage().contains("Job"));
  }

  @Test
  public void testComputationExceptionReturnsProcessingError() {
    Datastore datastore = mock(Datastore.class);
    ComputeEngine computeEngine = mock(ComputeEngine.class);
    UserImpl user = new UserImpl(datastore, computeEngine);

    Job job = new Job(
        new InputSource("input.txt"),
        new OutputSource("output.txt"),
        new Delimiters(","));

    when(datastore.extractInputFromJob(job))
        .thenReturn(new InputForCompute(java.util.Arrays.asList(1, 2, 3)));

    when(computeEngine.solve(
        org.mockito.ArgumentMatchers.any()))
        .thenThrow(new IllegalStateException("Computation failed."));

    JobSubmissionResponse response = user.submitJob(job);

    assertNotNull(response);
    assertEquals(JobResponseStatus.PROCESSING_ERROR, response.getStatus());
    assertFalse(response.isSuccessful());
    assertEquals("Computation failed.", response.getErrorMessage());
  }

  @Test
  public void testMissingOutputDestinationReturnsInvalidInput() {
    UserImpl user = new UserImpl(
        mock(Datastore.class),
        mock(ComputeEngine.class));

    Job job = new Job(
        new InputSource("input.txt"),
        null,
        new Delimiters(","));

    JobSubmissionResponse response = user.submitJob(job);

    assertNotNull(response);
    assertEquals(JobResponseStatus.INVALID_INPUT, response.getStatus());
    assertFalse(response.isSuccessful());
    assertTrue(response.getErrorMessage().contains("output"));
  }

  @Test
  public void testNullComputationStatusReturnsProcessingError() {
    Datastore datastore = mock(Datastore.class);
    ComputeEngine computeEngine = mock(ComputeEngine.class);
    UserImpl user = new UserImpl(datastore, computeEngine);

    Job job = new Job(
        new InputSource("input.txt"),
        new OutputSource("output.txt"),
        new Delimiters(","));

    when(datastore.extractInputFromJob(job))
        .thenReturn(new InputForCompute(java.util.Arrays.asList(1, 2, 3)));

    ComputationResult result = mock(ComputationResult.class);
    when(computeEngine.solve(org.mockito.ArgumentMatchers.any()))
        .thenReturn(result);
    when(result.getStatus()).thenReturn(null);

    JobSubmissionResponse response = user.submitJob(job);

    assertNotNull(response);
    assertEquals(JobResponseStatus.PROCESSING_ERROR, response.getStatus());
    assertFalse(response.isSuccessful());
  }

  @Test
  public void testConfigJobRequestRejectsNullDelimiter() {
    UserImpl user = new UserImpl(
        mock(Datastore.class),
        mock(ComputeEngine.class));

    ConfigJobResponse response = user.configJobRequest(
        new InputSource("input.txt"),
        new OutputSource("output.txt"),
        null);

    assertNotNull(response);
    assertEquals(ConfigJobResponseCode.Blocked, response.getResponseCode());
  }
}
