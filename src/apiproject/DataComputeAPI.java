package apiproject;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataComputeAPI {
  DataStreamWrapper readData(JobSource source);
  void writeData(JobDestination destination, DataStreamWrapper processedData);
}