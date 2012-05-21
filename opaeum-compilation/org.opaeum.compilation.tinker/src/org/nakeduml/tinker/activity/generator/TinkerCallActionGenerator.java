package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.opaeum.feature.StepDependency;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerInvocationActionGenerator.class } , after = { TinkerInvocationActionGenerator.class })
public class TinkerCallActionGenerator extends AbstractTinkerActivityNodeGenerator {

//	@VisitBefore(matchSubclasses = false, match = { INakedCallAction.class })
//	public void visitCallAction(INakedCallAction oa) {
//		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa);
//	}


}
