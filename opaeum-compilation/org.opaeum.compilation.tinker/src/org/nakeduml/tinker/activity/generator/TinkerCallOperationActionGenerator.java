package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.metamodel.actions.INakedCallOperationAction;
import org.opaeum.metamodel.activities.INakedInputPin;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerCallActionGenerator.class } , after = { TinkerCallActionGenerator.class })
public class TinkerCallOperationActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = false, match = { INakedCallOperationAction.class })
	public void visitCallOperationAction(INakedCallOperationAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerCallOperationActionPathName.getCopy());
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addCallOperationActionGetTarget(actionClass, oa);
	}
	
	private void addCallOperationActionGetTarget(OJAnnotatedClass actionClass, INakedCallOperationAction oa) {
		OJAnnotatedOperation getTarget = new OJAnnotatedOperation("getTarget");
		TinkerGenerationUtil.addOverrideAnnotation(getTarget);
		INakedInputPin target = oa.getTarget();
		getTarget.setReturnType(TinkerBehaviorUtil.activityNodePathName(oa.getTarget()));
		getTarget.getBody().addToStatements("return " + TinkerBehaviorUtil.inputPinGetter(target) + "()");
		actionClass.addToOperations(getTarget);
	}

}
