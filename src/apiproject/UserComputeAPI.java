package apiproject;

/**
 * The primary API boundary interface between the user and the compute engine.
 */
public interface UserComputeAPI {
    void submitJob(UserJobRequest request);
}