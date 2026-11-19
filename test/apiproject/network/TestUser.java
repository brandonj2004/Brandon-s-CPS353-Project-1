package apiproject.network;

import apiproject.conceptual.ComputeEngine;
import apiproject.conceptual.OutputValue;
import apiproject.conceptual.SolveResponse;
import apiproject.processes.Datastore;
import apiproject.processes.InputForCompute;
import apiproject.processes.Job;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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
    SolveResponse response = mock(SolveResponse.class);

    when(datastore.extractInputFromJob(job)).thenReturn(input);
    when(computeEngine.solve(input)).thenReturn(response);
    when(response.getResponseCode()).thenReturn(ResponseCode.SUCCESS);
    when(response.getOutput()).thenReturn(output);

    user.submitJob(job);

    verify(datastore).extractInputFromJob(job);
    verify(computeEngine).solve(input);
    verify(computeEngine).sendOutput(output);
    verify(datastore).writeOutput(job, output);
  }

  @Test
  public void testSubmitJobDoesNotWriteFailedComputation() {
    Datastore datastore = mock(Datastore.class);
    ComputeEngine computeEngine = mock(ComputeEngine.class);
    UserImpl user = new UserImpl(datastore, computeEngine);
    Job job = new Job(new InputSource("input.txt"),
        new OutputSource("output.txt"), new Delimiters(","));
    InputForCompute input = new InputForCompute(Arrays.asList(0));
    SolveResponse response = mock(SolveResponse.class);

    when(datastore.extractInputFromJob(job)).thenReturn(input);
    when(computeEngine.solve(input)).thenReturn(response);
    when(response.getResponseCode()).thenReturn(ResponseCode.INVALID_ARGUMENT);

    assertThrows(IllegalArgumentException.class, () -> user.submitJob(job));
    verify(computeEngine, never()).sendOutput(org.mockito.ArgumentMatchers.any());
    verify(datastore, never()).writeOutput(
        org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any());
  }
}
