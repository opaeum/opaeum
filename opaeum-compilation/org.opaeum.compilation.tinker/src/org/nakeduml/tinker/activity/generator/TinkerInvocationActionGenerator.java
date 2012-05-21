package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.metamodel.actions.INakedInvocationAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActionGenerator.class } , after = { TinkerActionGenerator.class })
public class TinkerInvocationActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedInvocationAction.class })
	public void visitInvocationAction(INakedInvocationAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa);
		addAddToInputPinVariable(actionClass, oa);
	}
	
}
