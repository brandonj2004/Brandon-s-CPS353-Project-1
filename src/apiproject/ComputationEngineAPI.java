package apiproject;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputationEngineAPI {
  DataStreamWrapper performComputation(DataStreamWrapper inputData);
}