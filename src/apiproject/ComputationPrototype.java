package apiproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Prototype component implementing step-by-step Collatz sequence mathematical rules.
 */
public class ComputationPrototype implements ComputationEngineAPI {

    @Override
    public DataStreamWrapper performCollatzComputation(DataStreamWrapper inputData) {
        List<Integer> inputs = inputData.getDataAsList();
        if (inputs.isEmpty()) {
            throw new IllegalArgumentException("Input streaming collection cannot be empty.");
        }

        // Pull the single positive integer seed value out
        int current = inputs.get(0);
        if (current <= 0) {
            throw new IllegalArgumentException("Collatz input must be a positive integer.");
        }

        List<Integer> sequence = new ArrayList<>();
        sequence.add(current);

        // Run step-by-step math rules until reaching 1
        while (current != 1) {
            if (current % 2 == 0) {
                current = current / 2;       // Rule 1: Even -> divide by 2
            } else {
                current = (current * 3) + 1; // Rule 2: Odd -> multiply by 3 and add 1
            }
            sequence.add(current);
        }

        System.out.println("Prototype ComputationEngine: Computed Collatz sequence step-by-step.");
        return () -> sequence;
    }
}
