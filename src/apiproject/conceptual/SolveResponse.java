package apiproject.conceptual;

import apiproject.network.ResponseCode;

public interface SolveResponse {

  ResponseCode getResponseCode();

  OutputValue getOutput();
}
