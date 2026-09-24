package apiproject;

/**
 * Internal API interface managing computation requests.
 * The initialization component acts as the "user" client that calls this module.
 */
public interface ComputationEngineAPI {
    DataStreamWrapper performCollatzComputation(DataStreamWrapper inputData);
}