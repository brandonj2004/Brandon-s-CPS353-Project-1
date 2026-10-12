
package apiproject.network;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOutputSource extends OutputSource {

    public final List<String> outputValues;

    public InMemoryOutputSource() {
        outputValues = new ArrayList<>();
    }

    @Override
    public void writeValues(List<String> values) {
        outputValues.clear();
        outputValues.addAll(values);
    }
}
