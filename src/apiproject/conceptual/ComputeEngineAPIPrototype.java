package apiproject.conceptual;

import apiproject.annotations.ConceptualAPIPrototype;
import apiproject.processes.InputForCompute;
import apiproject.network.ResponseCode;

public class ComputeEngineAPIPrototype {

  @ConceptualAPIPrototype
  public void prototypeComputeEngine(ComputeEngine api) {
    SolveResponse response = api.solve(new InputForCompute());

    if (response != null
        && response.getResponseCode() == ResponseCode.SUCCESS) {
      api.sendOutput(response.getOutput());
    }
  }
}
