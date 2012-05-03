package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.metamodel.actions.INakedAddVariableValueAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerWriteVariableActionGenerator.class } , after = { TinkerWriteVariableActionGenerator.class })
public class TinkerAddVariableValueAction extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = false, match = { INakedAddVariableValueAction.class })
	public void visitAddVariableValueAction(INakedAddVariableValueAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 
		if (oa.getVariable().getNakedMultiplicity().isOne()) {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerOneAddVariableValueAction.getCopy());
		} else {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerManyAddVariableValueAction.getCopy());
		}
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		implementGetValue(actionClass, oa.getValue());
		implementGenerics(actionClass, oa);
	}	
	
}
