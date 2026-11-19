package apiproject.network;

import apiproject.processes.InputForCompute;
import apiproject.conceptual.ComputeEngine;
import apiproject.conceptual.OutputValue;
import apiproject.processes.Datastore;
import apiproject.processes.Job;

public class UserImpl implements User {

  private final Datastore datastore;
  private final ComputeEngine computeEngine;

  public UserImpl(Datastore datastore, ComputeEngine computeEngine) {
    this.datastore = datastore;
    this.computeEngine = computeEngine;
  }

  @Override
  public ConfigJobResponse configJobRequest(
      InputSource in, OutputSource out, Delimiters delim) {

    return new ConfigJobResponse() {
      @Override
      public ConfigJobResponseCode getResponseCode() {
        return ConfigJobResponseCode.Passed;
      }
    };
  }

  @Override
  public Job configJob(
      InputSource in, OutputSource out, Delimiters delim) {

    return new Job(in, out, delim);
  }

  @Override
  public JobSubmissionResponse submitJob(Job job) {

    InputForCompute input = datastore.extractInputFromJob(job);

    OutputValue result = computeEngine.solve(input);

    computeEngine.sendOutput(result);

    datastore.writeOutput(job, result);

    return new JobSubmissionResponse() {
    };
  }

  @Override
  public FormattedOutputResponse loadFormattedOutput(OutputSource src) {
    return new FormattedOutputResponse() {
    };
  }
}
