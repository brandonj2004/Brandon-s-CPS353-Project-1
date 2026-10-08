package project.network;

import project.annotations.NetworkAPI;
import project.processes.Job;

@NetworkAPI
public interface User {
    ConfigJobResponse configJobRequest(InputSource in, OutputSource out, Delimiters delim);

    Job configJob(InputSource in, OutputSource out, Delimiters delim);

    JobSubmissionResponse submitJob(Job job);

    FormattedOutputResponse loadFormattedOutput(OutputSource src);
}