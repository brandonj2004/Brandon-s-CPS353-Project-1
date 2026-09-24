package apiproject;
/**
 * Prototype implementation of the User ↔ Compute Engine API.
 */
public class UserComputePrototype implements UserComputeAPI {
  @Override
  public void submitJob(UserJobRequest request) {
    System.out.println("Prototype UserComputeAPI: Job submission received.");
    System.out.println("Source: " + request.getSource().getDescription());
    System.out.println("Destination: " + request.getDestination().getTarget());
    System.out.println("Delimiters to use: " + request.getDelimiters());
  }
}