package apiproject;

/**
 * General interface for where the input data lives.
 * This could be a local file, a networked database, or a custom storage system.
 */
public interface JobSource {
  String getDescription();
}
