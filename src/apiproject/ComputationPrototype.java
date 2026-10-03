package apiproject;

import project.annotations.ConceptualAPIPrototype;
import java.util.Collections;

public class ComputationPrototype {

  @ConceptualAPIPrototype
  public void prototypeComputation(ComputationEngineAPI api) {
    DataStreamWrapper input = () -> Collections.singletonList(6);
    api.performComputation(input);
  }
}