package apiproject;

import project.annotations.NetworkAPIPrototype;

public class UserComputePrototype {

  @NetworkAPIPrototype
  public void prototypeUserCompute(UserComputeAPI api) {
    UserJobRequest request = new UserJobRequest() {
      @Override
      public JobSource getSource() {
        return () -> "input_source.csv";
      }

      @Override
      public JobDestination getDestination() {
        return () -> "output_destination.txt";
      }
    };

    api.submitJob(request);
  }
}