package apiproject.network;

import apiproject.annotations.NetworkAPIPrototype;
import apiproject.processes.Job;

public class UserAPIPrototype {

  @NetworkAPIPrototype
  public void prototypeUser(User api) {
    InputSource in = new InputSource();
    OutputSource out = new OutputSource();
    Delimiters delim = new Delimiters();

    ConfigJobResponse res = api.configJobRequest(in, out, delim);
    if (res.getResponseCode().success()) {
      Job job = api.configJob(in, out, delim);
      api.submitJob(job);
    }
  }
}