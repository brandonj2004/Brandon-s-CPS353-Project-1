package apiproject.conceptual;

import apiproject.network.JobResponseStatus;

public interface ComputationResult {

  JobResponseStatus getStatus();

  OutputValue getOutput();
}
