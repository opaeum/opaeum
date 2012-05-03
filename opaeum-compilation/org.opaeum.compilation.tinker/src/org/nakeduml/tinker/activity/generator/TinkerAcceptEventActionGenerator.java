package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActionGenerator.class } , after = { TinkerActionGenerator.class })
public class TinkerAcceptEventActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = false, match = { INakedAcceptEventAction.class })
	public void visitAcceptEventAction(INakedAcceptEventAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerAcceptEventAction);
		OJConstructor constructor1 = actionClass.findConstructor(OJUtil.classifierPathname(oa.getActivity().getContext()));
		addCreateComponentConstructor(constructor1, actionClass, oa);
		addCopyEventToOutputPin(actionClass, oa);
		implementGetTriggers(actionClass, oa);
	}

}
