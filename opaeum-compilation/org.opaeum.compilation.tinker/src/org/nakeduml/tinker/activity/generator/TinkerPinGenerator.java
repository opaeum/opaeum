package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.activity.maps.ConcretePinEmulatedClassifier;
import org.nakeduml.tinker.activity.maps.TinkerActivityNodeMapFactory;
import org.nakeduml.tinker.activity.maps.TinkerStructuralFeatureMap;
import org.nakeduml.tinker.generator.TinkerAttributeImplementor;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerObjectNodeGenerator.class }, after = { TinkerObjectNodeGenerator.class })
public class TinkerPinGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedPin.class })
	public void visitPins(INakedPin oa) {
		OJAnnotatedClass inputPinClass = findJavaClassForActivityNode(oa);
		inputPinClass.addToImports(OJUtil.classifierPathname(oa.getNakedBaseType()));
		implementGetAction(inputPinClass, oa);
		implementMultiplicity(inputPinClass, oa);
		implementGetContextObject(inputPinClass, oa.getActivity().getContext());
	}

	@Override
	protected void implementGetContextObject(OJAnnotatedClass ojClass, INakedBehavioredClassifier context) {
		OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject");
		TinkerGenerationUtil.addOverrideAnnotation(getContextObject);
		getContextObject.setReturnType(OJUtil.classifierPathname(context));
		getContextObject.getBody().addToStatements("return getAction().getActivity().getContextObject()");
		ojClass.addToOperations(getContextObject);
	}

	private void implementMultiplicity(OJAnnotatedClass inputPinClass, INakedObjectNode oa) {
		implementMultiplicity(inputPinClass, oa.getNakedMultiplicity());
	}

	private void implementMultiplicity(OJAnnotatedClass inputPinClass, INakedMultiplicity oa) {
		OJAnnotatedOperation getUpperMultiplicity = new OJAnnotatedOperation("getUpperMultiplicity", new OJPathName("int"));
		TinkerGenerationUtil.addOverrideAnnotation(getUpperMultiplicity);
		getUpperMultiplicity.getBody().addToStatements("return " + oa.getUpper());
		inputPinClass.addToOperations(getUpperMultiplicity);
	
		OJAnnotatedOperation getLowerMultiplicity = new OJAnnotatedOperation("getLowerMultiplicity", new OJPathName("int"));
		TinkerGenerationUtil.addOverrideAnnotation(getLowerMultiplicity);
		getLowerMultiplicity.getBody().addToStatements("return " + oa.getLower());
		inputPinClass.addToOperations(getLowerMultiplicity);
	}

	private void implementGetAction(OJAnnotatedClass inputPinClass, INakedPin pin) {
		ConcretePinEmulatedClassifier pinClassifier = new ConcretePinEmulatedClassifier(pin.getNameSpace(), pin);
		TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getActionToPinAssociationMap(pinClassifier, pin);
		TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
		tinkerAttributeImplementor.setJavaModel(this.javaModel);
		tinkerAttributeImplementor.implementAttributeFully(pinClassifier, map);
		
		OJAnnotatedOperation getAction = new OJAnnotatedOperation("getAction");
		TinkerGenerationUtil.addOverrideAnnotation(getAction);
		getAction.setReturnType(map.javaBaseTypePath());
		getAction.getBody().addToStatements("return this." + map.getter() + "()");
		inputPinClass.addToOperations(getAction);
	}

}
