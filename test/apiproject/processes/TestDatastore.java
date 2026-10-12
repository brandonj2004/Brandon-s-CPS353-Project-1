
package apiproject.processes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class TestDatastore {

  @Test
  public void testDatastoreCanBeCreated() {
    DatastoreImpl datastore = new DatastoreImpl();

    assertNotNull(datastore);
  }

  @Test
  public void testJobCanBeMocked() {
    Job job = mock(Job.class);

    assertNotNull(job);
  }
}

