package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.opaeum.feature.StepDependency;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActionGenerator.class } , after = { TinkerActionGenerator.class })
public class TinkerInvocationActionGenerator extends AbstractTinkerActivityNodeGenerator {

}
