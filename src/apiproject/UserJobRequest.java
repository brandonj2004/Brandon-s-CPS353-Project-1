package apiproject;
/**
 * Request configuration wrapper supplied by the user.
 * Provides fallback defaults for delimiters if none are explicitly specified.
 */
public interface UserJobRequest {
  JobSource getSource();
  JobDestination getDestination();

  // Default method to automatically supply standard delimiters if the user leaves them empty
  default String getDelimiters() {
    return ","; // Reasonable default delimiter
  }
}