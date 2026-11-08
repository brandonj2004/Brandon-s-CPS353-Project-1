package apiproject.network;

public class Delimiters {

  private final String separator;

  public Delimiters() {
    this.separator = ",";
  }

  public Delimiters(String separator) {
    this.separator = separator;
  }

  public String getSeparator() {
    return separator;
  }
}
