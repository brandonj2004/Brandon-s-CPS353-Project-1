package apiproject.conceptual;

import apiproject.annotations.ConceptualAPI;
import apiproject.processes.InputForCompute;

@ConceptualAPI
public interface ComputeEngine {

  ComputationResult solve(InputForCompute input);

  OutputStatus sendOutput(OutputValue val);
}
