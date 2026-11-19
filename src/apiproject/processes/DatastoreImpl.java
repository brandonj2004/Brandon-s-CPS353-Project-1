package apiproject.processes;

import apiproject.conceptual.OutputValue;
import apiproject.network.Delimiters;
import apiproject.network.OutputSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class DatastoreImpl implements Datastore {

  private final Map<Integer, Job> jobs = new HashMap<>();
  private final Map<Integer, Status> jobStatuses = new HashMap<>();
  private final Map<Job, Integer> assignedJobIDs = new IdentityHashMap<>();
  private int nextJobID = 1;
  private int recentJobID = 0;

  @Override
  public JobIdentifier getRecentJobID() {
    recentJobID = nextJobID++;
    return new JobIdentifier(recentJobID);
  }

  @Override
  public SetIDResponse setID(Job job, JobIdentifier jobId) {
    if (job == null || jobId == null || jobId.getID() <= 0) {
      throw new IllegalArgumentException("A valid job and job ID are required.");
    }

    assignedJobIDs.put(job, jobId.getID());

    return new SetIDResponse() {
    };
  }

  @Override
  public Status isComplete(Job job) {
    if (job == null) {
      throw new IllegalArgumentException("Job is required.");
    }

    Integer jobID = assignedJobIDs.get(job);

    if (jobID == null) {
      return Status.INCOMPLETE;
    }

    return jobStatuses.getOrDefault(jobID, Status.INCOMPLETE);
  }

  @Override
  public WriteJobResponse writeJob(Job job, Status status) {
    if (job == null || status == null) {
      throw new IllegalArgumentException("Job and status are required.");
    }

    Integer jobID = assignedJobIDs.get(job);

    if (jobID == null) {
      throw new IllegalArgumentException("Job must have an ID before it is stored.");
    }

    jobs.put(jobID, job);
    jobStatuses.put(jobID, status);

    return new WriteJobResponse() {
    };
  }

  @Override
  public JobRequestResponse requestJob(JobIdentifier jobId) {
    if (jobId == null || !jobs.containsKey(jobId.getID())) {
      return null;
    }

    return new JobRequestResponse() {
    };
  }

  @Override
  public Job getJob(JobIdentifier jobId) {
    if (jobId == null) {
      return null;
    }

    return jobs.get(jobId.getID());
  }

  @Override
  public InputForCompute extractInputFromJob(Job job) {
    if (job == null || job.getInputSource() == null
        || job.getInputSource().getFilePath() == null) {
      throw new IllegalArgumentException("Job input file is required.");
    }

    List<Integer> values = new ArrayList<>();

    try {
      List<String> lines =
          Files.readAllLines(Paths.get(job.getInputSource().getFilePath()));

      for (String line : lines) {
        String[] numbers = line.trim().split("[,\\s]+");

        for (String number : numbers) {
          if (!number.isEmpty()) {
            values.add(Integer.parseInt(number));
          }
        }
      }

      return new InputForCompute(values);

    } catch (IOException e) {
      throw new RuntimeException("Unable to read input file.", e);
    }
  }

  @Override
  public WriteJobResponse writeOutput(Job job, OutputValue output) {
    if (job == null || job.getOutputSource() == null
        || job.getOutputSource().getFilePath() == null) {
      throw new IllegalArgumentException("Job output file is required.");
    }

    if (output == null || output.getValues() == null) {
      throw new IllegalArgumentException("Computed output is required.");
    }

    OutputSource destination = job.getOutputSource();
    Delimiters delimiters = destination.getDelimiters();

    String separator = ",";

    if (job.getDelimiters() != null) {
      separator = job.getDelimiters().getSeparator();
    } else if (delimiters != null) {
      separator = delimiters.getSeparator();
    }

    String formattedOutput =
        String.join(separator, output.getValues());

    try {
      Files.write(
          Paths.get(destination.getFilePath()),
          formattedOutput.getBytes(StandardCharsets.UTF_8));

      return new WriteJobResponse() {
      };

    } catch (IOException e) {
      throw new RuntimeException("Unable to write output file.", e);
    }
  }
}
