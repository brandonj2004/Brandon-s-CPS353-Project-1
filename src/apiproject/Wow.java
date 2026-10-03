package apiproject;

import apiproject.network.Delimiters;
import apiproject.network.InputSource;
import apiproject.network.OutputSource;

public class Wow {
  private InputSource in;
  private OutputSource out;
  private Delimiters delim;

  public Wow(InputSource in, OutputSource out, Delimiters delim) {
    this.in = in;
    this.out = out;
    this.delim = delim;
  }

  public InputSource getIn() {
    return in;
  }

  public OutputSource getOut() {
    return out;
  }

  public Delimiters getDelim() {
    return delim;
  }
}