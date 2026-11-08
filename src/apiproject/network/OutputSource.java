package apiproject.network;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OutputSource {

  private final String filePath;
  private final Delimiters delimiters;

  public OutputSource() {
    this.filePath = null;
    this.delimiters = new Delimiters();
  }

  public OutputSource(String filePath) {
    this(filePath, new Delimiters());
  }

  public OutputSource(String filePath, Delimiters delimiters) {
    this.filePath = filePath;
    this.delimiters = delimiters;
  }

  public void writeValues(List<String> values) {
    if (filePath == null) {
      return;
    }

    String output = String.join(delimiters.getSeparator(), values);

    try {
      Files.write(Paths.get(filePath), output.getBytes());
    } catch (IOException e) {
      throw new RuntimeException("Unable to write output file.", e);
    }
  }
}
