package apiproject.processes;

import apiproject.conceptual.OutputValue;
import apiproject.network.InMemoryInputSource;
import apiproject.network.InMemoryOutputSource;

public class InMemoryDatastore implements Datastore {
  private final InMemoryInputSource input;
  private final InMemoryOutputSource output;

  public InMemoryDatastore(
      InMemoryInputSource input,
      InMemoryOutputSource output) {
    this.input = input;
    this.output = output;
  }

  @Override
  public JobIdentifier getRecentJobID() {
    return new JobIdentifier();
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
    return new InputForCompute(input.getInputValues());
  }

  @Override
  public WriteJobResponse writeOutput(Job job, OutputValue result) {
    writeResults(result);

    return new WriteJobResponse() {
    };
  }

  public void writeResults() {
    for (Integer value : input.getInputValues()) {
      output.outputValues.add(String.valueOf(value));
    }
  }

  public void writeResults(OutputValue result) {
    output.outputValues.clear();

    for (String value : result.getValues()) {
      output.outputValues.add(value);
    }
  }
}
