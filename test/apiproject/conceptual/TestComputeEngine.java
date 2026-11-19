package apiproject.conceptual;

import apiproject.network.JobResponseStatus;
import apiproject.processes.InputForCompute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestComputeEngine {

  @Test
  public void testComputeEngineCanBeCreated() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    assertNotNull(engine);
  }

  @Test
  public void testCollatzSequenceForOne() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    ComputationResult response =
        engine.solve(new InputForCompute(Arrays.asList(1)));

    assertEquals(JobResponseStatus.COMPLETED, response.getStatus());
    assertEquals(Arrays.asList("1"), response.getOutput().getValues());
  }

  @Test
  public void testCollatzSequenceForTwo() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    ComputationResult response =
        engine.solve(new InputForCompute(Arrays.asList(2)));

    assertEquals(JobResponseStatus.COMPLETED, response.getStatus());
    assertEquals(Arrays.asList("2 -> 1"), response.getOutput().getValues());
  }

  @Test
  public void testCollatzSequenceForThree() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    ComputationResult response =
        engine.solve(new InputForCompute(Arrays.asList(3)));

    assertEquals(JobResponseStatus.COMPLETED, response.getStatus());
    assertEquals(
        Arrays.asList("3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"),
        response.getOutput().getValues());
  }

  @Test
  public void testMultipleInputs() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    ComputationResult response =
        engine.solve(new InputForCompute(Arrays.asList(1, 2, 3)));

    assertEquals(JobResponseStatus.COMPLETED, response.getStatus());
    assertEquals(
        Arrays.asList(
            "1",
            "2 -> 1",
            "3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"),
        response.getOutput().getValues());
  }

  @Test
  public void testZeroInputReturnsInvalidInput() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    ComputationResult response =
        engine.solve(new InputForCompute(Arrays.asList(0)));

    assertEquals(JobResponseStatus.INVALID_INPUT, response.getStatus());
    assertNull(response.getOutput());
  }

  @Test
  public void testNegativeInputReturnsInvalidInput() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    ComputationResult response =
        engine.solve(new InputForCompute(Arrays.asList(-5)));

    assertEquals(JobResponseStatus.INVALID_INPUT, response.getStatus());
    assertNull(response.getOutput());
  }

  @Test
  public void testNullInputValueReturnsInvalidInput() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    List<Integer> values = new ArrayList<>();
    values.add(null);

    ComputationResult response =
        engine.solve(new InputForCompute(values));

    assertEquals(JobResponseStatus.INVALID_INPUT, response.getStatus());
    assertNull(response.getOutput());
  }

  @Test
  public void testNullInputReturnsInvalidInput() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    ComputationResult response = engine.solve(null);

    assertEquals(JobResponseStatus.INVALID_INPUT, response.getStatus());
    assertNull(response.getOutput());
  }

  @Test
  public void testEmptyInputReturnsInvalidInput() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    ComputationResult response =
        engine.solve(new InputForCompute());

    assertEquals(JobResponseStatus.INVALID_INPUT, response.getStatus());
    assertNull(response.getOutput());
  }

  @Test
  public void testNullOutputReturnsInvalidInput() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    OutputStatus response = engine.sendOutput(null);

    assertEquals(JobResponseStatus.INVALID_INPUT, response.getStatus());
  }
}
