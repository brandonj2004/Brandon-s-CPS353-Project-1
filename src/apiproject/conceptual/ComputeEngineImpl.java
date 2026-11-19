package apiproject.conceptual;

import apiproject.network.JobResponseStatus;
import apiproject.processes.InputForCompute;

import java.util.ArrayList;
import java.util.List;

public class ComputeEngineImpl implements ComputeEngine {

  private OutputValue computedOutput;

  @Override
  public ComputationResult solve(InputForCompute input) {

    try {
      if (input == null) {
        return createComputationResult(
            JobResponseStatus.INVALID_INPUT, null);
      }

      List<Integer> values = input.getValues();

      if (values == null || values.isEmpty()) {
        return createComputationResult(
            JobResponseStatus.INVALID_INPUT, null);
      }

      List<String> results = new ArrayList<>();

      for (Integer value : values) {
        if (value == null || value <= 0) {
          return createComputationResult(
              JobResponseStatus.INVALID_INPUT, null);
        }

        long current = value;
        String sequence = String.valueOf(current);

        while (current != 1) {
          if (current % 2 == 0) {
            current = current / 2;
          } else {
            current = (current * 3) + 1;
          }

          sequence = sequence + " -> " + current;
        }

        results.add(sequence);
      }

      computedOutput = new OutputValue() {
        @Override
        public List<String> getValues() {
          return new ArrayList<>(results);
        }
      };

      return createComputationResult(
          JobResponseStatus.COMPLETED, computedOutput);

    } catch (RuntimeException e) {
      return createComputationResult(
          JobResponseStatus.PROCESSING_ERROR, null);
    }
  }

  private ComputationResult createComputationResult(
      JobResponseStatus status, OutputValue output) {

    return new ComputationResult() {
      @Override
      public JobResponseStatus getStatus() {
        return status;
      }

      @Override
      public OutputValue getOutput() {
        return output;
      }
    };
  }

  @Override
  public OutputStatus sendOutput(OutputValue val) {

    try {
      if (val == null || val.getValues() == null) {
        return createOutputStatus(JobResponseStatus.INVALID_INPUT);
      }

      computedOutput = val;
      return createOutputStatus(JobResponseStatus.COMPLETED);

    } catch (RuntimeException e) {
      return createOutputStatus(JobResponseStatus.PROCESSING_ERROR);
    }
  }

  private OutputStatus createOutputStatus(JobResponseStatus status) {

    return new OutputStatus() {
      @Override
      public JobResponseStatus getStatus() {
        return status;
      }
    };
  }
}
