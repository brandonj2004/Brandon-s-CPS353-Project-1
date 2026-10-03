package apiproject.conceptual;

import apiproject.annotations.ConceptualAPIPrototype;
import apiproject.processes.InputForCompute;

public class ComputeEngineAPIPrototype {

    @ConceptualAPIPrototype
    public void prototypeComputeEngine(ComputeEngine api) {
        OutputValue val = api.solve(new InputForCompute());
        api.sendOutput(val);
    }
}