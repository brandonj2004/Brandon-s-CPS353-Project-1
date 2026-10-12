
package apiproject.processes;

public class DatastoreImpl implements Datastore {

    @Override
    public JobIdentifier getRecentJobID() {
        return null;
    }

    @Override
    public SetIDResponse setID(Job job, JobIdentifier jobId) {
        return null;
    }

    @Override
    public Status isComplete(Job job) {
        return null;
    }

    @Override
    public WriteJobResponse writeJob(Job job, Status status) {
        return null;
    }

    @Override
    public JobRequestResponse requestJob(JobIdentifier jobId) {
        return null;
    }

    @Override
    public Job getJob(JobIdentifier jobId) {
        return null;
    }

    @Override
    public InputForCompute extractInputFromJob(Job job) {
        return null;
    }
}
