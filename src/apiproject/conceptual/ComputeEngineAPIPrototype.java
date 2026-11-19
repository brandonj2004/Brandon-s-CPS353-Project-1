package apiproject.conceptual;

import apiproject.annotations.ConceptualAPIPrototype;
import apiproject.processes.InputForCompute;
import apiproject.network.JobResponseStatus;

public class ComputeEngineAPIPrototype {

  @ConceptualAPIPrototype
  public void prototypeComputeEngine(ComputeEngine api) {
    ComputationResult response = api.solve(new InputForCompute());

    if (response != null
        && response.getStatus() == JobResponseStatus.COMPLETED) {
      api.sendOutput(response.getOutput());
    }
  }
}
