package apiproject.conceptual;

import apiproject.processes.InputForCompute;
import java.util.ArrayList;
import java.util.List;

public class ComputeEngineImpl implements ComputeEngine {

  private OutputValue computedOutput;

  @Override
  public OutputValue solve(InputForCompute input) {
    List<String> results = new ArrayList<>();

    for (Integer value : input.getValues()) {

      if (value == null || value <= 0) {
        throw new IllegalArgumentException(
            "Collatz input must be a positive integer.");
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

    return computedOutput;
  }

  @Override
  public SendOutputResponse sendOutput(OutputValue val) {
    computedOutput = val;

    return new SendOutputResponse() {
    };
  }
}
