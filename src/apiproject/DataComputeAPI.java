package apiproject;

/**
 * API interface responsible for reading and writing data between 
 * storage locations and the calculation engine.
 */
public interface DataComputeAPI {
  DataStreamWrapper readData(JobSource source);
  void writeData(JobDestination destination, DataStreamWrapper processedData);
}
