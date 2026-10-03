package apiproject.processes;

import apiproject.annotations.ProcessAPIPrototype;

public class DatastoreAPIPrototype {

  @ProcessAPIPrototype
  public void prototypeDatastore(Datastore api) {
    // Writing job
    JobIdentifier jobId = api.getRecentJobID();
    Job job = new Job();
    if (api.setID(job, jobId) != null) {
      Status isComplete = api.isComplete(job);
      api.writeJob(job, isComplete);
    }

    // Reading job
    JobIdentifier requestJobId = new JobIdentifier();
    if (api.requestJob(requestJobId) != null) {
      Job requestJob = api.getJob(requestJobId);
      api.extractInputFromJob(requestJob);
    }
  }
}