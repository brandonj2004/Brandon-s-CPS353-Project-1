package apiproject.conceptual;

import apiproject.annotations.ConceptualAPI;
import apiproject.processes.InputForCompute;

@ConceptualAPI
public interface ComputeEngine {
    // Get output from computation program, given an input
    OutputValue solve(InputForCompute input);

    SendOutputResponse sendOutput(OutputValue val);
}