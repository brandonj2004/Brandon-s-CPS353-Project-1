package apiproject.network;

import java.util.ArrayList;
import java.util.List;

public class InMemoryInputSource extends InputSource {
    private final List<Integer> inputValues;

    public InMemoryInputSource(List<Integer> inputValues) {
        this.inputValues = new ArrayList<>(inputValues);
    }

    public List<Integer> getInputValues() {
        return new ArrayList<>(inputValues);
    }
}