
package apiproject.network;

import apiproject.conceptual.ComputeEngine;
import apiproject.processes.Datastore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class TestUser {

  @Test
    public void testUserCanBeCreated() {
    Datastore datastore = mock(Datastore.class);
    ComputeEngine computeEngine = mock(ComputeEngine.class);

    UserImpl user = new UserImpl(datastore, computeEngine);

    assertNotNull(user);
  }
}
