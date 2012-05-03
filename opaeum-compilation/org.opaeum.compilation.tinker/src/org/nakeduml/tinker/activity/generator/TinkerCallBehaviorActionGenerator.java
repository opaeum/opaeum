package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.opaeum.feature.StepDependency;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerCallActionGenerator.class } , before = { TinkerCallActionGenerator.class })
public class TinkerCallBehaviorActionGenerator extends AbstractTinkerActivityNodeGenerator {

}
