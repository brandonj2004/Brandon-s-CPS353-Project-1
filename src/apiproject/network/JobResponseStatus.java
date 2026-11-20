package apiproject.network;

public enum JobResponseStatus {
  COMPLETED(true),
  FAILED(false),
  INVALID_INPUT(false),
  MISSING_FILE(false),
  FILE_ACCESS_ERROR(false),
  PROCESSING_ERROR(false);

  private final boolean successful;

  JobResponseStatus(boolean successful) {
    this.successful = successful;
  }

  public boolean isSuccessful() {
    return successful;
  }
}
