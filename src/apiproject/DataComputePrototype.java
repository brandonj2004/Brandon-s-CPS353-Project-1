package apiproject;
import java.util.List;

/**
 * Prototype implementation of the Data Storage ↔ Compute Engine API.
 */
public class DataComputePrototype implements DataComputeAPI {
  @Override
  public DataStreamWrapper readData(JobSource source) {
    System.out.println("Prototype DataComputeAPI: Reading input from " + source.getDescription());
    // Simple mock returning an initial user input number (e.g., 6) wrapped as a collection
    return () -> List.of(6);
  }

  @Override
  public void writeData(JobDestination destination, DataStreamWrapper processedData) {
    System.out.println("Prototype DataComputeAPI: Writing output stream to " + destination.getTarget());
    System.out.println("Data values written: " + processedData.getDataAsList());
  }
}
