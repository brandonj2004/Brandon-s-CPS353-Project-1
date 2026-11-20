package apiproject.network;

import apiproject.processes.InputForCompute;
import apiproject.conceptual.ComputeEngine;
import apiproject.conceptual.ComputationResult;
import apiproject.conceptual.OutputStatus;
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

    boolean valid = in != null && out != null && delim != null;

    if (valid) {
      this.outputSource = out;
    }

    final ConfigJobResponseCode responseCode =
        valid ? ConfigJobResponseCode.Passed : ConfigJobResponseCode.Blocked;

    return new ConfigJobResponse() {
      @Override
      public ConfigJobResponseCode getResponseCode() {
        return responseCode;
      }
    };
  }

  @Override
  public Job configJob(
      InputSource in, OutputSource out, Delimiters delim) {

    if (in == null || out == null || delim == null) {
      throw new IllegalArgumentException(
          "Input, output, and delimiters are required.");
    }

    this.outputSource = out;

    return new Job(in, out, delim);
  }

  @Override
  public JobSubmissionResponse submitJob(Job job) {

    try {
      if (job == null) {
        return createResponse(
            JobResponseStatus.INVALID_INPUT, "Job is required.");
      }

      if (job.getInputSource() == null
          || job.getDelimiters() == null) {
        return createResponse(
            JobResponseStatus.INVALID_INPUT,
            "Job input and delimiters are required.");
      }

      if (datastore == null || computeEngine == null) {
        return createResponse(
            JobResponseStatus.PROCESSING_ERROR,
            "Datastore and compute engine are required.");
      }

      OutputSource destination = job.getOutputSource();

      if (destination == null) {
        destination = outputSource;
      }

      if (destination == null) {
        return createResponse(
            JobResponseStatus.INVALID_INPUT,
            "An output destination is required.");
      }

      InputForCompute input = datastore.extractInputFromJob(job);

      ComputationResult solveResponse = computeEngine.solve(input);

      if (solveResponse == null || solveResponse.getStatus() == null) {
        return createResponse(
            JobResponseStatus.PROCESSING_ERROR,
            "Computation returned an invalid response.");
      }

      if (solveResponse.getStatus() != JobResponseStatus.COMPLETED) {
        JobResponseStatus status = solveResponse.getStatus();

        return createResponse(
            status,
            "Computation failed: " + status);
      }

      OutputValue result = solveResponse.getOutput();

      if (result == null) {
        return createResponse(
            JobResponseStatus.PROCESSING_ERROR,
            "Computation returned no output.");
      }

      OutputStatus sendResponse = computeEngine.sendOutput(result);

      if (sendResponse == null || sendResponse.getStatus() == null) {
        return createResponse(
            JobResponseStatus.PROCESSING_ERROR,
            "Output processing returned an invalid response.");
      }

      if (sendResponse.getStatus() != JobResponseStatus.COMPLETED) {
        return createResponse(
            sendResponse.getStatus(),
            "Unable to send computation output.");
      }

      if (job.getOutputSource() != null) {
        datastore.writeOutput(job, result);
      } else {
        List<String> formattedValues = result.getValues();
        destination.writeValues(formattedValues);
      }

      return createResponse(JobResponseStatus.COMPLETED, "");

    } catch (IllegalArgumentException e) {
      return createResponse(
          JobResponseStatus.INVALID_INPUT, getMessage(e));
    } catch (Exception e) {
      return createResponse(
          JobResponseStatus.PROCESSING_ERROR, getMessage(e));
    }
  }

  private String getMessage(Exception e) {
    String message = e.getMessage();

    if (message == null || message.isEmpty()) {
      return "Job submission failed.";
    }

    return message;
  }

  private JobSubmissionResponse createResponse(
      JobResponseStatus status, String errorMessage) {

    return new JobSubmissionResponse() {
      @Override
      public JobResponseStatus getStatus() {
        return status;
      }

      @Override
      public boolean isSuccessful() {
        return status.isSuccessful();
      }

      @Override
      public String getErrorMessage() {
        return errorMessage;
      }
    };
  }

  @Override
  public FormattedOutputResponse loadFormattedOutput(OutputSource src) {

    if (src == null) {
      throw new IllegalArgumentException("Output source is required.");
    }

    return new FormattedOutputResponse() {};
  }
}
