package apiproject.processes;

public class JobIdentifier {

  private final int id;

  public JobIdentifier() {
    this(0);
  }

  public JobIdentifier(int id) {
    this.id = id;
  }

  public int getID() {
    return id;
  }
}
