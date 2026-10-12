
package apiproject.network;

import apiproject.processes.InputForCompute;
import apiproject.conceptual.ComputeEngine;
import apiproject.conceptual.OutputValue;
import apiproject.processes.Datastore;
import apiproject.processes.Job;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements User {

  private final Datastore datastore;
  private final ComputeEngine computeEngine;
  private OutputSource outputSource;

  public UserImpl(Datastore datastore, ComputeEngine computeEngine) {
    this.datastore = datastore;
    this.computeEngine = computeEngine;
  }

  @Override
    public ConfigJobResponse configJobRequest(
            InputSource in, OutputSource out, Delimiters delim) {
    this.outputSource = out;
    return null;
  }

  @Override
    public Job configJob(
            InputSource in, OutputSource out, Delimiters delim) {
    this.outputSource = out;
    return null;
  }

  @Override
    public JobSubmissionResponse submitJob(Job job) {
    InputForCompute input = datastore.extractInputFromJob(job);
    OutputValue result = computeEngine.solve(input);
    computeEngine.sendOutput(result);

    if (outputSource != null) {
      List<String> formattedValues = new ArrayList<>();

      for (Integer value : result.getValues()) {
        formattedValues.add(String.valueOf(value));
      }

      outputSource.writeValues(formattedValues);
    }

    return null;
  }

  @Override
    public FormattedOutputResponse loadFormattedOutput(OutputSource src) {
    return null;
  }
}
