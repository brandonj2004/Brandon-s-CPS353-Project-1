package apiproject;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {
    JobSubmissionResponse submitJob(UserJobRequest request);
}