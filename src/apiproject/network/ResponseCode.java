package apiproject.network;

public enum ResponseCode {
  SUCCESS(true),
  FAILURE(false),
  INVALID_ARGUMENT(false),
  FILE_NOT_FOUND(false),
  IO_ERROR(false),
  INTERNAL_ERROR(false);

  private final boolean success;

  ResponseCode(boolean success) {
    this.success = success;
  }

  public boolean success() {
    return success;
  }
}
