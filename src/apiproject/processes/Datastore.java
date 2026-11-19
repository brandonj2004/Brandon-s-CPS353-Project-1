package apiproject.processes;

import apiproject.annotations.ProcessAPI;
import apiproject.conceptual.OutputValue;

@ProcessAPI
public interface Datastore {

  JobIdentifier getRecentJobID();

  SetIDResponse setID(Job job, JobIdentifier jobId);

  Status isComplete(Job job);

  WriteJobResponse writeJob(Job job, Status status);

  JobRequestResponse requestJob(JobIdentifier jobId);

  Job getJob(JobIdentifier jobId);

  InputForCompute extractInputFromJob(Job job);

  WriteJobResponse writeOutput(Job job, OutputValue output);
}
