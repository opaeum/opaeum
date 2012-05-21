package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
import org.nakeduml.tinker.activity.maps.TinkerActivityNodeMapFactory;
import org.nakeduml.tinker.activity.maps.TinkerStructuralFeatureMap;
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
		addAddToInputPinVariable(ojClass, oa);
	}
	
	private void implementStructuralFeatureAction(OJAnnotatedClass actionClass, INakedStructuralFeatureAction oa) {
		OJAnnotatedOperation getObject = new OJAnnotatedOperation("getObject");
		TinkerGenerationUtil.addOverrideAnnotation(getObject);
		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(oa.getNameSpace(), oa);
		TinkerStructuralFeatureMap map =TinkerActivityNodeMapFactory.getPinVariableInActionAssociationMap(concreteEmulatedClassifier, (oa.getObject()));
		getObject.setReturnType(map.javaBaseTypePath());
		getObject.getBody().addToStatements("return this." + map.getter() + "()");
		actionClass.addToOperations(getObject);
	}
	
}
