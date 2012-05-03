package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.metamodel.actions.INakedReadVariableAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerVariableActionGenerator.class } , after = { TinkerVariableActionGenerator.class })
public class TinkerReadVariableActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = false, match = { INakedReadVariableAction.class })
	public void visitReadVariableAction(INakedReadVariableAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 
		if (oa.getVariable().getNakedMultiplicity().isOne()) {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerOneReadVariableAction.getCopy());
		} else {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerManyReadVariableAction.getCopy());
		}
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		implementGenerics(actionClass, oa);
		implementGetResult(actionClass, oa.getResult());
	}
	

}
