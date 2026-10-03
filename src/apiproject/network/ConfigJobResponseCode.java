package apiproject.network;

public enum ConfigJobResponseCode {
  Passed(true),
  Blocked(false);

  private boolean pass;

  private ConfigJobResponseCode(boolean pass) {
    this.pass = pass;
  }

  public boolean pass() {
    return pass;
  }

  boolean success() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'success'");
  }
}