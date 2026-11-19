package apiproject.conceptual;

import apiproject.network.ResponseCode;
import apiproject.processes.InputForCompute;

import java.util.ArrayList;
import java.util.List;

public class ComputeEngineImpl implements ComputeEngine {

  private OutputValue computedOutput;

  @Override
  public SolveResponse solve(InputForCompute input) {

    try {
      if (input == null) {
        return createSolveResponse(ResponseCode.INVALID_ARGUMENT, null);
      }

      List<Integer> values = input.getValues();

      if (values == null || values.isEmpty()) {
        return createSolveResponse(ResponseCode.INVALID_ARGUMENT, null);
      }

      List<String> results = new ArrayList<>();

      for (Integer value : values) {
        if (value == null || value <= 0) {
          return createSolveResponse(ResponseCode.INVALID_ARGUMENT, null);
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

      return createSolveResponse(ResponseCode.SUCCESS, computedOutput);

    } catch (RuntimeException e) {
      return createSolveResponse(ResponseCode.INTERNAL_ERROR, null);
    }
  }

  private SolveResponse createSolveResponse(
      ResponseCode responseCode, OutputValue output) {

    return new SolveResponse() {
      @Override
      public ResponseCode getResponseCode() {
        return responseCode;
      }

      @Override
      public OutputValue getOutput() {
        return output;
      }
    };
  }

  @Override
  public SendOutputResponse sendOutput(OutputValue val) {

    try {
      if (val == null || val.getValues() == null) {
        return createSendOutputResponse(ResponseCode.INVALID_ARGUMENT);
      }

      computedOutput = val;
      return createSendOutputResponse(ResponseCode.SUCCESS);

    } catch (RuntimeException e) {
      return createSendOutputResponse(ResponseCode.INTERNAL_ERROR);
    }
  }

  private SendOutputResponse createSendOutputResponse(
      ResponseCode responseCode) {

    return new SendOutputResponse() {
      @Override
      public ResponseCode getResponseCode() {
        return responseCode;
      }
    };
  }
}
