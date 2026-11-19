package apiproject.network;

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

  public String getFilePath() {
    return filePath;
  }

  public Delimiters getDelimiters() {
    return delimiters;
  }
}
