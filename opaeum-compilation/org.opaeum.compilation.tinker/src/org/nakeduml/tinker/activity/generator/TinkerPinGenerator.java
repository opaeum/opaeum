package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.ActionBridge;
import org.nakeduml.tinker.activity.ConcreteEmulatedClassifier;
import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.core.INakedMultiplicity;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerObjectNodeGenerator.class }, after = { TinkerObjectNodeGenerator.class })
public class TinkerPinGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedPin.class })
	public void visitPins(INakedPin oa) {
		OJAnnotatedClass inputPinClass = findJavaClassForActivityNode(oa);
		inputPinClass.addToImports(OJUtil.classifierPathname(oa.getNakedBaseType()));
		implementGetAction(inputPinClass, oa);
		implementMultiplicity(inputPinClass, oa);

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
		ConcreteEmulatedClassifier actionClassifier = new ConcreteEmulatedClassifier(pin.getNameSpace(), pin.getAction());
		OJUtil.unlock();
		NakedStructuralFeatureMap actionMap = new NakedStructuralFeatureMap(new ActionBridge(actionClassifier, pin.getAction()));
		OJUtil.lock();
	
		OJAnnotatedField actionField = new OJAnnotatedField(actionMap.fieldname(), TinkerBehaviorUtil.activityNodePathName(pin.getAction()));
		inputPinClass.addToFields(actionField);
	
		OJAnnotatedOperation getAction = new OJAnnotatedOperation("getAction");
		TinkerGenerationUtil.addOverrideAnnotation(getAction);
		getAction.setReturnType(TinkerBehaviorUtil.activityNodePathName(pin.getAction()));
	
		OJIfStatement ifActionNull = new OJIfStatement("this." + actionMap.fieldname() + " == null");
		ifActionNull.addToThenPart("this." + actionMap.fieldname() + " = new " + TinkerBehaviorUtil.activityNodePathName(pin.getAction()).getLast() + "(this.vertex.getInEdges(\""
				+ TinkerBehaviorUtil.pinActionEdgeName(pin) + "\").iterator().next().getOutVertex(), this.contextObject)");
	
		getAction.getBody().addToStatements(ifActionNull);
		getAction.getBody().addToStatements("return this." + actionMap.fieldname());
		inputPinClass.addToOperations(getAction);
	}

}
