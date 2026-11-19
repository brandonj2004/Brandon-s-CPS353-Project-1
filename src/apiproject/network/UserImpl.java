package apiproject.network;

import apiproject.processes.InputForCompute;
import apiproject.conceptual.ComputationResult;
import apiproject.conceptual.ComputeEngine;
import apiproject.conceptual.OutputValue;
import apiproject.processes.Datastore;
import apiproject.processes.Job;
import java.util.List;

public class UserImpl implements User {

  private final Datastore datastore;
  private final ComputeEngine computeEngine;
  private OutputSource outputSource;

  public UserImpl(Datastore datastore, ComputeEngine computeEngine) {
    this.datastore = datastore;
    this.computeEngine = computeEngine;
  }

  @Override
  public ConfigJobResponse configJobRequest(
      InputSource in, OutputSource out, Delimiters delim) {

    this.outputSource = out;

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

    this.outputSource = out;

    return new Job(in, out, delim);
  }

  @Override
  public JobSubmissionResponse submitJob(Job job) {

    InputForCompute input = datastore.extractInputFromJob(job);

    ComputationResult response = computeEngine.solve(input);
    if (response == null || response.getStatus() != JobResponseStatus.COMPLETED
        || response.getOutput() == null) {
      if (response != null && response.getStatus() == JobResponseStatus.INVALID_INPUT) {
        throw new IllegalArgumentException("Invalid compute input.");
      }
      throw new IllegalStateException("Computation failed.");
    }

    OutputValue result = response.getOutput();

    computeEngine.sendOutput(result);

    OutputSource destination = job.getOutputSource();

    if (destination == null) {
      destination = outputSource;
    }

    if (job.getOutputSource() != null) {
      datastore.writeOutput(job, result);
    } else if (destination != null) {
      List<String> formattedValues = result.getValues();
      destination.writeValues(formattedValues);
    }

    return new JobSubmissionResponse() {
    };
  }

  @Override
  public FormattedOutputResponse loadFormattedOutput(OutputSource src) {
    return new FormattedOutputResponse() {
    };
  }
}
