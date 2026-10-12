
package apiproject.conceptual;

import apiproject.processes.InputForCompute;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class TestComputeEngine {

  @Test
    public void testComputeEngineCanBeCreated() {
    ComputeEngineImpl engine = new ComputeEngineImpl();

    assertNotNull(engine);
  }

  @Test
    public void testInputCanBeMocked() {
    InputForCompute input = mock(InputForCompute.class);

    assertNotNull(input);
  }
}
