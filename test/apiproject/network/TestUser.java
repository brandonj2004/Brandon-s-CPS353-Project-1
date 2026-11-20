package apiproject.network;

import apiproject.conceptual.ComputeEngine;
import apiproject.conceptual.ComputationResult;
import apiproject.conceptual.OutputStatus;
import apiproject.conceptual.OutputValue;
import apiproject.processes.Datastore;
import apiproject.processes.InputForCompute;
import apiproject.processes.Job;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TestUser {

  @Test
  public void testUserCanBeCreated() {
    Datastore datastore = mock(Datastore.class);
    ComputeEngine computeEngine = mock(ComputeEngine.class);
    UserImpl user = new UserImpl(datastore, computeEngine);
    assertNotNull(user);
  }

  @Test
  public void testSubmitJobCoordinatesDatastoreAndComputeEngine() {
    Datastore datastore = mock(Datastore.class);
    ComputeEngine computeEngine = mock(ComputeEngine.class);
    UserImpl user = new UserImpl(datastore, computeEngine);
    Job job = new Job(
        new InputSource("input.txt"),
        new OutputSource("output.txt"),
        new Delimiters(","));
    InputForCompute input =
        new InputForCompute(Arrays.asList(1, 2, 3));
    OutputValue output = mock(OutputValue.class);

    ComputationResult solveResponse = mock(ComputationResult.class);
    when(solveResponse.getStatus()).thenReturn(JobResponseStatus.COMPLETED);
    when(solveResponse.getOutput()).thenReturn(output);

    OutputStatus sendResponse = mock(OutputStatus.class);
    when(sendResponse.getStatus()).thenReturn(JobResponseStatus.COMPLETED);

    when(datastore.extractInputFromJob(job)).thenReturn(input);
    when(computeEngine.solve(input)).thenReturn(solveResponse);
    when(computeEngine.sendOutput(output)).thenReturn(sendResponse);

    user.submitJob(job);

    verify(datastore).extractInputFromJob(job);
    verify(computeEngine).solve(input);
    verify(computeEngine).sendOutput(output);
    verify(datastore).writeOutput(job, output);
  }

  @Test
  public void testConfigJobRequestBlocksMissingInput() {
    UserImpl user = new UserImpl(null, null);
    ConfigJobResponse response =
        user.configJobRequest(null, new OutputSource(), null);

    assertEquals(
        ConfigJobResponseCode.Blocked,
        response.getResponseCode());
  }
}
