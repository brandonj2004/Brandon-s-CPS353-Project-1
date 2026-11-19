package apiproject.network;

public class InputSource {

  private final String filePath;

  public InputSource() {
    this.filePath = null;
  }

  public InputSource(String filePath) {
    this.filePath = filePath;
  }

  public String getFilePath() {
    return filePath;
  }
}
