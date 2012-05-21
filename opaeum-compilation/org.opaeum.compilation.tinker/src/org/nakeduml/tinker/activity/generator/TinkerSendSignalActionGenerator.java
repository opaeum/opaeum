package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.activity.maps.TinkerActivityNodeMapFactory;
import org.nakeduml.tinker.activity.maps.TinkerStructuralFeatureMap;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedValuePin;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.name.NameConverter;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerInvocationActionGenerator.class } , after = { TinkerInvocationActionGenerator.class })
public class TinkerSendSignalActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedSendSignalAction.class })
	public void visitSendSignalAction(INakedSendSignalAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa); 
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerSendSignalAction);
		implementGetSignal(actionClass, oa);
		addResolveTarget(actionClass, oa);
		implementGetTarget(actionClass, oa);
	}
	
	private void implementGetSignal(OJAnnotatedClass actionClass, INakedSendSignalAction oa) {
		OJAnnotatedOperation constructSignal = new OJAnnotatedOperation("getSignal");
		constructSignal.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		constructSignal.setReturnType(TinkerBehaviorUtil.tinkerSignalPathName);
		INakedSignal signal = oa.getSignal();
		OJPathName signalPathname = OJUtil.classifierPathname(signal);
		OJField signalField = new OJField();
		signalField.setName(NameConverter.decapitalize(signal.getName()));
		signalField.setType(signalPathname);
		signalField.setInitExp("new " + signalPathname.getLast() + "(true)");
		constructSignal.getBody().addToLocals(signalField);
		actionClass.addToImports(signalPathname);
		actionClass.addToOperations(constructSignal);

		// Match up input pins with signal attributes
		int i = 0;
		for (INakedInputPin inputPin : oa.getInput()) {
			if (inputPin != oa.getTarget()) {
				INakedProperty attribute = signal.getOwnedAttributes().get(i++);
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(attribute);
				if (inputPin instanceof INakedValuePin) {
					INakedValuePin valuePin = (INakedValuePin) inputPin;
					constructSignal.getBody().addToStatements(
							signalField.getName() + "." + map.setter() + "("
									+ ValueSpecificationUtil.expressValue(constructSignal, valuePin.getValue(), oa.getContext(), valuePin.getType()) + ")");
				} else {
					// TODO
				}
			}
		}
		constructSignal.getBody().addToStatements("return " + signalField.getName());
	}
	
	private void addResolveTarget(OJAnnotatedClass actionClass, INakedSendSignalAction oa) {
		OJAnnotatedOperation resolveTarget = new OJAnnotatedOperation("resolveTarget");
		resolveTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		resolveTarget.setReturnType(TinkerBehaviorUtil.tinkerBaseTinkerBehavioredClassifier);
		// TODO this will only work for ValuePin, do regular inputpin. value
		// sits on the token or maybe copied to action variable
		resolveTarget.getBody().addToStatements("return getTarget().getValue()");
		actionClass.addToOperations(resolveTarget);
	}
	
	private void implementGetTarget(OJAnnotatedClass actionClass, INakedSendSignalAction oa) {
		OJAnnotatedOperation getTarget = new OJAnnotatedOperation("getTarget");
		getTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		TinkerStructuralFeatureMap map =TinkerActivityNodeMapFactory.get(oa.getTarget());
		getTarget.setReturnType(map.javaBaseTypePath());
		getTarget.getBody().addToStatements("return " + map.getter() + "()");
		actionClass.addToOperations(getTarget);
	}

}
