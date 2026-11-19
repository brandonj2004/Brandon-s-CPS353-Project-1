package apiproject.conceptual;

import apiproject.network.ResponseCode;
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
    SolveResponse response =
        engine.solve(new InputForCompute(Arrays.asList(1)));

    assertEquals(ResponseCode.SUCCESS, response.getResponseCode());
    assertEquals(Arrays.asList("1"), response.getOutput().getValues());
  }

  @Test
  public void testCollatzSequenceForTwo() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    SolveResponse response =
        engine.solve(new InputForCompute(Arrays.asList(2)));

    assertEquals(ResponseCode.SUCCESS, response.getResponseCode());
    assertEquals(Arrays.asList("2 -> 1"), response.getOutput().getValues());
  }

  @Test
  public void testCollatzSequenceForThree() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    SolveResponse response =
        engine.solve(new InputForCompute(Arrays.asList(3)));

    assertEquals(ResponseCode.SUCCESS, response.getResponseCode());
    assertEquals(
        Arrays.asList("3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"),
        response.getOutput().getValues());
  }

  @Test
  public void testMultipleInputs() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    SolveResponse response =
        engine.solve(new InputForCompute(Arrays.asList(1, 2, 3)));

    assertEquals(ResponseCode.SUCCESS, response.getResponseCode());
    assertEquals(
        Arrays.asList(
            "1",
            "2 -> 1",
            "3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"),
        response.getOutput().getValues());
  }

  @Test
  public void testZeroInputReturnsInvalidArgument() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    SolveResponse response =
        engine.solve(new InputForCompute(Arrays.asList(0)));

    assertEquals(ResponseCode.INVALID_ARGUMENT, response.getResponseCode());
    assertNull(response.getOutput());
  }

  @Test
  public void testNegativeInputReturnsInvalidArgument() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    SolveResponse response =
        engine.solve(new InputForCompute(Arrays.asList(-5)));

    assertEquals(ResponseCode.INVALID_ARGUMENT, response.getResponseCode());
    assertNull(response.getOutput());
  }

  @Test
  public void testNullInputValueReturnsInvalidArgument() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    List<Integer> values = new ArrayList<>();
    values.add(null);

    SolveResponse response =
        engine.solve(new InputForCompute(values));

    assertEquals(ResponseCode.INVALID_ARGUMENT, response.getResponseCode());
    assertNull(response.getOutput());
  }

  @Test
  public void testNullInputReturnsInvalidArgument() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    SolveResponse response = engine.solve(null);

    assertEquals(ResponseCode.INVALID_ARGUMENT, response.getResponseCode());
    assertNull(response.getOutput());
  }

  @Test
  public void testEmptyInputReturnsInvalidArgument() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    SolveResponse response = engine.solve(new InputForCompute());

    assertEquals(ResponseCode.INVALID_ARGUMENT, response.getResponseCode());
    assertNull(response.getOutput());
  }

  @Test
  public void testNullOutputReturnsInvalidArgument() {
    ComputeEngineImpl engine = new ComputeEngineImpl();
    SendOutputResponse response = engine.sendOutput(null);

    assertEquals(ResponseCode.INVALID_ARGUMENT, response.getResponseCode());
  }
}
