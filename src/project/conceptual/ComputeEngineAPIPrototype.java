package project.conceptual;

import project.annotations.ConceptualAPIPrototype;
import project.processes.InputForCompute;

public class ComputeEngineAPIPrototype {

  @ConceptualAPIPrototype
  public void prototypeComputeEngine(ComputeEngine api) {
    OutputValue val = api.solve(new InputForCompute());
    api.sendOutput(val);
  }
}