
package apiproject;

import apiproject.conceptual.ComputeEngineImpl;
import apiproject.network.InMemoryInputSource;
import apiproject.network.InMemoryOutputSource;
import apiproject.network.UserImpl;
import apiproject.processes.InMemoryDatastore;
import apiproject.processes.Job;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputeEngineIntegrationTest {

  @Test
  public void testComputeEngineWithInMemoryDataStore() {
    InMemoryInputSource input =
        new InMemoryInputSource(Arrays.asList(1, 10, 25));

    InMemoryOutputSource output = new InMemoryOutputSource();

    ComputeEngineImpl computeEngine = new ComputeEngineImpl();

    InMemoryDatastore datastore =
        new InMemoryDatastore(input, output);

    UserImpl user = new UserImpl(datastore, computeEngine);

    // Configure the output destination before submitting the job.
    user.configJobRequest(input, output, null);

    Job job = new Job();
    user.submitJob(job);

    List<String> expectedOutput = Arrays.asList(
        "1",
        "10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1",
        "25 -> 76 -> 38 -> 19 -> 58 -> 29 -> 88 -> 44 -> 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"
    );

    assertEquals(expectedOutput, output.outputValues);
  }
}
