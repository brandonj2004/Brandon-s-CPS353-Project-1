
package apiproject.conceptual;

import apiproject.processes.InputForCompute;
import java.util.ArrayList;
import java.util.List;

public class ComputeEngineImpl implements ComputeEngine {

  private OutputValue computedOutput;

  @Override
  public OutputValue solve(InputForCompute input) {
    List<Integer> results = new ArrayList<>();

    for (Integer value : input.getValues()) {
      results.add(value * value);
    }

    computedOutput = new OutputValue() {
      @Override
      public List<Integer> getValues() {
        return new ArrayList<>(results);
      }
    };

    return computedOutput;
  }

  @Override
  public SendOutputResponse sendOutput(OutputValue val) {
    computedOutput = val;
    return null;
  }
}
