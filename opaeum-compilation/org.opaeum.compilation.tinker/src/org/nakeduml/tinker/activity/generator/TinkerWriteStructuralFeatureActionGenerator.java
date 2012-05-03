package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.metamodel.actions.INakedWriteStructuralFeatureAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerStructuralFeatureActionGenerator.class } , after = { TinkerStructuralFeatureActionGenerator.class })
public class TinkerWriteStructuralFeatureActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedWriteStructuralFeatureAction.class })
	public void visitWriteStructuralFeatureAction(INakedWriteStructuralFeatureAction oa) {
		OJAnnotatedClass ojClass = findJavaClassForActivityNode(oa);
		implementWriteStructuralFeatureAction(ojClass, oa);
	}

	private void implementWriteStructuralFeatureAction(OJAnnotatedClass actionClass, INakedWriteStructuralFeatureAction oa) {
		implementGetValue(actionClass, oa.getValue());
		implementGetResult(actionClass, oa.getResult());
	}

}
