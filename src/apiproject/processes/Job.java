package apiproject.processes;

import apiproject.network.InputSource;
import apiproject.network.OutputSource;
import apiproject.network.Delimiters;

public class Job {

  private InputSource inputSource;
  private OutputSource outputSource;
  private Delimiters delimiters;

  public Job() {
  }

  public Job(
      InputSource inputSource,
      OutputSource outputSource,
      Delimiters delimiters) {
    this.inputSource = inputSource;
    this.outputSource = outputSource;
    this.delimiters = delimiters;
  }

  public InputSource getInputSource() {
    return inputSource;
  }

  public OutputSource getOutputSource() {
    return outputSource;
  }

  public Delimiters getDelimiters() {
    return delimiters;
  }
}
