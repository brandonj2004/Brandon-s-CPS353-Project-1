package apiproject.conceptual;

import apiproject.processes.InputForCompute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestComputeEngine {

  @Test
  public void testComputeEngineCanBeCreated() {
    ComputeEngineImpl engine = new ComputeEngineImpl();

    assertNotNull(engine);
  }

  @Test
  public void testCollatzSequenceForOne() {
    ComputeEngineImpl engine = new ComputeEngineImpl();

    InputForCompute input =
        new InputForCompute(Arrays.asList(1));

    OutputValue result = engine.solve(input);

    assertEquals(Arrays.asList("1"), result.getValues());
  }

  @Test
  public void testCollatzSequenceForTwo() {
    ComputeEngineImpl engine = new ComputeEngineImpl();

    InputForCompute input =
        new InputForCompute(Arrays.asList(2));

    OutputValue result = engine.solve(input);

    assertEquals(Arrays.asList("2 -> 1"), result.getValues());
  }

  @Test
  public void testCollatzSequenceForThree() {
    ComputeEngineImpl engine = new ComputeEngineImpl();

    InputForCompute input =
        new InputForCompute(Arrays.asList(3));

    OutputValue result = engine.solve(input);

    assertEquals(
        Arrays.asList("3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"),
        result.getValues());
  }

  @Test
  public void testMultipleInputs() {
    ComputeEngineImpl engine = new ComputeEngineImpl();

    InputForCompute input =
        new InputForCompute(Arrays.asList(1, 2, 3));

    OutputValue result = engine.solve(input);

    assertEquals(
        Arrays.asList(
            "1",
            "2 -> 1",
            "3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"),
        result.getValues());
  }

  @Test
  public void testZeroInputThrowsException() {
    ComputeEngineImpl engine = new ComputeEngineImpl();

    InputForCompute input =
        new InputForCompute(Arrays.asList(0));

    assertThrows(
        IllegalArgumentException.class,
        () -> engine.solve(input));
  }

  @Test
  public void testNegativeInputThrowsException() {
    ComputeEngineImpl engine = new ComputeEngineImpl();

    InputForCompute input =
        new InputForCompute(Arrays.asList(-5));

    assertThrows(
        IllegalArgumentException.class,
        () -> engine.solve(input));
  }

  @Test
  public void testNullInputValueThrowsException() {
    ComputeEngineImpl engine = new ComputeEngineImpl();

    List<Integer> values = new ArrayList<>();
    values.add(null);

    InputForCompute input = new InputForCompute(values);

    assertThrows(
        IllegalArgumentException.class,
        () -> engine.solve(input));
  }
}
