package project.conceptual;

import apiproject.annotations.ConceptualAPI;
import project.processes.InputForCompute;

@ConceptualAPI
public interface ComputeEngine {
  // Get output from computation program, given an input
  OutputValue solve(InputForCompute input);

  SendOutputResponse sendOutput(OutputValue val);
}