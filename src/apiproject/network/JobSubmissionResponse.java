package apiproject.network;

public interface JobSubmissionResponse {

  JobResponseStatus getStatus();

  String getErrorMessage();

  boolean isSuccessful();
}
