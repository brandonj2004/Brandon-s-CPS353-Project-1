package project.conceptual;

import project.annotations.ConceptualAPI;
import project.processes.InputForCompute;

@ConceptualAPI
public interface ComputeEngine {
  // Get output from computation program, given an input
  OutputValue solve(InputForCompute input);

  SendOutputResponse sendOutput(OutputValue val);
}