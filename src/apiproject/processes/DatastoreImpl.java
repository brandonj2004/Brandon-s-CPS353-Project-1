package apiproject.processes;

import java.io.IOException;

public class DatastoreImpl implements Datastore {

  @Override
  public JobIdentifier getRecentJobID() {
    return null;
  }

  @Override
  public SetIDResponse setID(Job job, JobIdentifier jobId) {
    return null;
  }

  @Override
  public Status isComplete(Job job) {
    return Status.COMPLETE;
  }

  @Override
  public WriteJobResponse writeJob(Job job, Status status) {
    return null;
  }

  @Override
  public JobRequestResponse requestJob(JobIdentifier jobId) {
    return null;
  }

  @Override
  public Job getJob(JobIdentifier jobId) {
    return null;
  }

  @Override
  public InputForCompute extractInputFromJob(Job job) {
    try {
      return new InputForCompute(job.getInputSource().readValues());
    } catch (IOException e) {
      throw new RuntimeException("Unable to read input file.", e);
    }
  }
}
