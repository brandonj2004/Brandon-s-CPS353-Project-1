
package apiproject.processes;

import java.util.ArrayList;
import java.util.List;

public class InputForCompute {
    private final List<Integer> values;

    public InputForCompute() {
        values = new ArrayList<>();
    }

    public InputForCompute(List<Integer> values) {
        this.values = new ArrayList<>(values);
    }

    public List<Integer> getValues() {
        return new ArrayList<>(values);
    }
}
