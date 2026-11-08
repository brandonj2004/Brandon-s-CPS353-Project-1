package apiproject.checkpointtests;

import apiproject.conceptual.ComputeEngine;
import apiproject.conceptual.ComputeEngineImpl;
import apiproject.network.ConfigJobResponse;
import apiproject.network.Delimiters;
import apiproject.network.InputSource;
import apiproject.network.OutputSource;
import apiproject.network.User;
import apiproject.network.UserImpl;
import apiproject.processes.Datastore;
import apiproject.processes.DatastoreImpl;
import apiproject.processes.Job;

public class ManualTestingFramework {

  public static final String INPUT = "manualTestInput.txt";
  public static final String OUTPUT = "manualTestOutput.txt";

  public static void main(String[] args) {

    // Instantiate the three API implementations
    Datastore datastore = new DatastoreImpl();
    ComputeEngine computeEngine = new ComputeEngineImpl();
    User user = new UserImpl(datastore, computeEngine);

    // Configure input, output, and delimiter
    InputSource input = new InputSource(INPUT);
    Delimiters delimiters = new Delimiters(",");
    OutputSource output = new OutputSource(OUTPUT, delimiters);

    // Check whether the job configuration succeeds
    ConfigJobResponse response =
        user.configJobRequest(input, output, delimiters);

    if (response.getResponseCode().pass()) {
      Job job = user.configJob(input, output, delimiters);
      user.submitJob(job);
    }
  }
}
