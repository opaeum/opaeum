package org.nakeduml.tinker.activity.generator;

import java.util.Collection;
import java.util.Collections;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
import org.nakeduml.tinker.activity.maps.TinkerActivityNodeMapFactory;
import org.nakeduml.tinker.activity.maps.TinkerStructuralFeatureMap;
import org.nakeduml.tinker.generator.TinkerAttributeImplementor;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerCollectionStep;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.javageneration.composition.TinkerComponentInitializer;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedOclAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedValuePin;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActivityNodeGenerator.class }, after = { TinkerActivityNodeGenerator.class })
public class TinkerActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedAction.class })
	public void visitAction(INakedAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa);
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addHasPostConditionPassed(actionClass);
		addHasPreConditionPassed(actionClass);
		addOutputPinGetters(actionClass, oa);
		addInputPinGetters(actionClass, oa);
		addInputPinVariables(actionClass, oa);
		addGetInputPinVariables(actionClass, oa);
		implementGetActivity(actionClass, oa);
		implementGetContextObject(actionClass, oa.getActivity().getContext());
		implementClearInputPinVariables(actionClass, oa);
		if (!oa.getInput().isEmpty() || !oa.getOutput().isEmpty()) {
			actionClass.findConstructor(new OJPathName("java.lang.Boolean")).getBody().addToStatements("createComponents()");
		}
	}

	private void implementClearInputPinVariables(OJAnnotatedClass actionClass, INakedAction oa) {
		OJAnnotatedOperation clearInputPinVariables = new OJAnnotatedOperation("clearInputPinVariables");
		TinkerGenerationUtil.addOverrideAnnotation(clearInputPinVariables);
		actionClass.addToOperations(clearInputPinVariables);
		
		for (INakedInputPin inputPin : oa.getInput()) {
			ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(oa.getNameSpace(), oa);
			TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getPinVariableInActionAssociationMap(concreteEmulatedClassifier, inputPin);
			if (map.isMany()) {
				clearInputPinVariables.getBody().addToStatements(map.clearer() + "()");
			} else {
				clearInputPinVariables.getBody().addToStatements(map.setter() + "(null)");
			}
		}
		
	}

	private void addHasPreConditionPassed(OJClass actionClass) {
		OJAnnotatedOperation hasPreConditionPassed = new OJAnnotatedOperation("hasPreConditionPassed");
		hasPreConditionPassed.setReturnType(new OJPathName("boolean"));
		hasPreConditionPassed.getBody().addToStatements("return true");
		hasPreConditionPassed.setVisibility(OJVisibilityKind.PROTECTED);
		hasPreConditionPassed.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		actionClass.addToOperations(hasPreConditionPassed);
	}

	private void addHasPostConditionPassed(OJClass actionClass) {
		OJAnnotatedOperation hasPostConditionPassed = new OJAnnotatedOperation("hasPostConditionPassed");
		hasPostConditionPassed.setReturnType(new OJPathName("boolean"));
		hasPostConditionPassed.getBody().addToStatements("return true");
		hasPostConditionPassed.setVisibility(OJVisibilityKind.PROTECTED);
		hasPostConditionPassed.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		actionClass.addToOperations(hasPostConditionPassed);
	}

	private void addOutputPinGetters(OJClass actionClass, INakedAction a) {

		StringBuilder sb = new StringBuilder("return Arrays.asList(");
		Collection<INakedOutputPin> outputPins = a.getOutput();
		int count = 0;
		for (INakedOutputPin outputPin : outputPins) {
			count++;
			TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
			tinkerAttributeImplementor.setJavaModel(this.javaModel);
			ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(a.getNameSpace(), a);
			TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getPinToActionAssociationMap(concreteEmulatedClassifier, outputPin);
			tinkerAttributeImplementor.implementAttributeFully(concreteEmulatedClassifier, map);
			sb.append(map.getter());
			sb.append("()");
			if (count < outputPins.size()) {
				sb.append(", ");
			}

			// Add to createComponent to constructor
			TinkerComponentInitializer tinkerComponentInitializer = new TinkerComponentInitializer();
			tinkerComponentInitializer.setJavaModel(this.javaModel);
			OJAnnotatedOperation createComponents = (OJAnnotatedOperation) actionClass.findOperation("createComponents", Collections.emptyList());
			if (createComponents == null) {
				createComponents = new OJAnnotatedOperation("createComponents");
				createComponents.setBody(new OJBlock());
				actionClass.addToOperations(createComponents);
			}
			tinkerComponentInitializer.initProperty(createComponents, map.getProperty());

		}
		sb.append(")");
		if (a instanceof INakedOclAction) {
			OJAnnotatedOperation getResultPin = new OJAnnotatedOperation("getResultPin");
			actionClass.addToOperations(getResultPin);
			INakedOutputPin returnPin = ((INakedOclAction) a).getReturnPin();
			if (returnPin == null) {
				getResultPin.setReturnType(TinkerBehaviorUtil.tinkerOutputPinPathName.getCopy());
				getResultPin.getReturnType().addToGenerics(new OJPathName("?, ?"));
				getResultPin.getBody().addToStatements("return null");
			} else if (returnPin.getNakedMultiplicity().isOne()) {
				getResultPin.setReturnType(TinkerBehaviorUtil.tinkerOneOutputPinPathName.getCopy());
				actionClass.addToImports(TinkerBehaviorUtil.tinkerOneOutputPinPathName.getCopy());
			} else {
				getResultPin.setReturnType(TinkerBehaviorUtil.tinkerManyOutputPinPathName.getCopy());
				actionClass.addToImports(TinkerBehaviorUtil.tinkerManyOutputPinPathName.getCopy());
			}
			if (returnPin != null) {
				TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
				tinkerAttributeImplementor.setJavaModel(this.javaModel);
				ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(a.getNameSpace(), a);
				TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getPinToActionAssociationMap(concreteEmulatedClassifier, returnPin);
				getResultPin.getReturnType().addToGenerics(OJUtil.classifierPathname(returnPin.getNakedBaseType()));
				getResultPin.getBody().addToStatements("return " + map.getter() + "()");
			}
		} else {
			OJAnnotatedOperation getOutputPins = new OJAnnotatedOperation("getOutput");
			TinkerGenerationUtil.addOverrideAnnotation(getOutputPins);
			OJPathName returnType = new OJPathName("java.util.List");
			returnType.addToElementTypes(new OJPathName("? extends OutputPin<?, ?>"));
			actionClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
			getOutputPins.setReturnType(returnType);
			String statement = sb.toString();
			getOutputPins.getBody().addToStatements(statement);
			actionClass.addToOperations(getOutputPins);
			actionClass.addToImports(TinkerBehaviorUtil.tinkerOutputPinPathName);
		}
	}

	// TODO remove this for AcceptEventAction
	private void addInputPinGetters(OJClass actionClass, INakedAction a) {
		Collection<INakedInputPin> inputPins = a.getInput();
		boolean hasInputPins = false;

		StringBuilder sb = new StringBuilder();
		sb.append("return Arrays.asList(");
		int count = 0;
		for (INakedInputPin inputPin : inputPins) {
			count++;
			TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
			tinkerAttributeImplementor.setJavaModel(this.javaModel);
			ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(a.getNameSpace(), a);
			TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getPinToActionAssociationMap(concreteEmulatedClassifier, inputPin);
			tinkerAttributeImplementor.implementAttributeFully(concreteEmulatedClassifier, map);
			sb.append(map.getter());
			sb.append("()");
			if (count < inputPins.size()) {
				sb.append(", ");
			}

			// Add to createComponent to constructor
			TinkerComponentInitializer tinkerComponentInitializer = new TinkerComponentInitializer();
			tinkerComponentInitializer.setJavaModel(this.javaModel);
			OJAnnotatedOperation createComponents = (OJAnnotatedOperation) actionClass.findOperation("createComponents", Collections.emptyList());
			if (createComponents == null) {
				createComponents = new OJAnnotatedOperation("createComponents");
				createComponents.setBody(new OJBlock());
				actionClass.addToOperations(createComponents);
			}
			tinkerComponentInitializer.initProperty(createComponents, map.getProperty());

		}
		OJAnnotatedOperation getInputPins = new OJAnnotatedOperation("getInput");
		TinkerGenerationUtil.addOverrideAnnotation(getInputPins);
		OJPathName returnType = new OJPathName("java.util.List");

		returnType.addToElementTypes(new OJPathName("? extends InputPin<?, ?>"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
		getInputPins.setReturnType(returnType);
		String statement = sb.toString();
		if (hasInputPins) {
			statement = statement.substring(0, sb.toString().length() - 1);
		}
		statement += ")";
		getInputPins.getBody().addToStatements(statement);
		actionClass.addToOperations(getInputPins);
		actionClass.addToImports(TinkerBehaviorUtil.tinkerInputPinPathName);
	}

	private void addInputPinVariables(OJClass actionClass, INakedAction oa) {
		for (INakedInputPin inputPin : oa.getInput()) {
//			if ((oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
//				continue;
//			}

			TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
			tinkerAttributeImplementor.setJavaModel(this.javaModel);
			ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(oa.getNameSpace(), oa);
			TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getPinVariableInActionAssociationMap(concreteEmulatedClassifier, inputPin);
			tinkerAttributeImplementor.implementAttributeFully(concreteEmulatedClassifier, map);

			if (!map.getProperty().isDerived() && map.isMany()) {
				TinkerCollectionStep tinkerCollectionStep = new TinkerCollectionStep();
				tinkerCollectionStep.setJavaModel(this.javaModel);
				tinkerCollectionStep.visitManyProperty((OJAnnotatedClass) actionClass, map);
			}
		}
	}

	private void addGetInputPinVariables(OJClass actionClass, INakedAction oa) {
		OJAnnotatedOperation getInputPinVariables = new OJAnnotatedOperation("getInputPinVariables");
		getInputPinVariables.setComment("This only gets called in the toString method");
		OJPathName returnType = new OJPathName("java.util.List");
		returnType.addToElementTypes(new OJPathName("? extends Object"));
		getInputPinVariables.setReturnType(returnType);

		OJField result = new OJField();
		result.setName("result");
		result.setType(new OJPathName("java.util.List").addToGenerics(new OJPathName("Object")));
		result.setInitExp("new ArrayList<Object>()");
		actionClass.addToImports(new OJPathName("java.util.ArrayList"));
		getInputPinVariables.getBody().addToLocals(result);

		for (INakedInputPin inputPin : oa.getInput()) {
//			if (inputPin instanceof INakedValuePin || (oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
//				continue;
//			}
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
			OJIfStatement ifStatement = new OJIfStatement("this." + map.fieldname() + " != null", "result.add(this." + map.fieldname() + ")");
			getInputPinVariables.getBody().addToStatements(ifStatement);
		}
		getInputPinVariables.getBody().addToStatements("return result");
		actionClass.addToOperations(getInputPinVariables);
	}

}
