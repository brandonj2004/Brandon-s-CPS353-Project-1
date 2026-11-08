package apiproject.network;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputSource {

  private final String filePath;

  public InputSource() {
    this.filePath = null;
  }

  public InputSource(String filePath) {
    this.filePath = filePath;
  }

  public List<Integer> readValues() throws IOException {
    List<Integer> values = new ArrayList<>();

    List<String> lines = Files.readAllLines(Paths.get(filePath));

    for (String line : lines) {
      String[] numbers = line.trim().split("[,\\s]+");

      for (String number : numbers) {
        if (!number.isEmpty()) {
          values.add(Integer.parseInt(number));
        }
      }
    }

    return values;
  }
}
