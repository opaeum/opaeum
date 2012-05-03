package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.metamodel.actions.INakedStructuralFeatureAction;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActionGenerator.class } , after = { TinkerActionGenerator.class })
public class TinkerStructuralFeatureActionGenerator extends AbstractTinkerActivityNodeGenerator  {

	@VisitBefore(matchSubclasses = true, match = { INakedStructuralFeatureAction.class })
	public void visitStructuralFeatureAction(INakedStructuralFeatureAction oa) {
		OJAnnotatedClass ojClass = findJavaClassForActivityNode(oa); 
		implementStructuralFeatureAction(ojClass, oa);
	}
	
	private void implementStructuralFeatureAction(OJAnnotatedClass actionClass, INakedStructuralFeatureAction oa) {
		OJAnnotatedOperation getObject = new OJAnnotatedOperation("getObject");
		TinkerGenerationUtil.addOverrideAnnotation(getObject);
		getObject.setReturnType(TinkerBehaviorUtil.activityNodePathName(oa.getObject()));
		getObject.getBody().addToStatements("return this." + TinkerBehaviorUtil.inputPinGetter(oa.getObject()) + "()");
		actionClass.addToOperations(getObject);
	}
	
}
