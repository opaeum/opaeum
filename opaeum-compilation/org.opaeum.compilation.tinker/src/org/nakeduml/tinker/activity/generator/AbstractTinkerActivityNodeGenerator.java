package org.nakeduml.tinker.activity.generator;

import java.util.Collections;

import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
import org.nakeduml.tinker.activity.maps.TinkerActivityNodeMapFactory;
import org.nakeduml.tinker.activity.maps.TinkerStructuralFeatureMap;
import org.nakeduml.tinker.activity.maps.TriggerBridge;
import org.nakeduml.tinker.generator.TinkerAttributeImplementor;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.javageneration.composition.TinkerComponentInitializer;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedOclAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.actions.INakedVariableAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.name.NameConverter;

public abstract class AbstractTinkerActivityNodeGenerator extends StereotypeAnnotator {

	protected void implementGetContextObject(OJAnnotatedClass ojClass, INakedBehavioredClassifier context) {
		OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject");
		TinkerGenerationUtil.addOverrideAnnotation(getContextObject);
		getContextObject.setReturnType(OJUtil.classifierPathname(context));
		getContextObject.getBody().addToStatements("return getActivity().getContextObject()");
		ojClass.addToOperations(getContextObject);
	}

	protected void addContextObjectField(OJAnnotatedClass actionClass, INakedBehavioredClassifier context) {
		OJField contextObjectField = new OJField();
		contextObjectField.setType(OJUtil.classifierPathname(context));
		contextObjectField.setName("contextObject");
		actionClass.addToFields(contextObjectField);
	}

	protected void implementGetActivity(OJClass actionClass, INakedActivityNode node) {
		ConcreteEmulatedClassifier nodeClassifier = new ConcreteEmulatedClassifier(node.getNameSpace(), node);
		TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getActivityToNodeAssociationMap(nodeClassifier, node);
		TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
		tinkerAttributeImplementor.setJavaModel(this.javaModel);
		tinkerAttributeImplementor.implementAttributeFully(nodeClassifier, map);
		
		OJAnnotatedOperation getActivity = new OJAnnotatedOperation("getActivity");
		getActivity.setReturnType(map.javaBaseTypePath());
		TinkerGenerationUtil.addOverrideAnnotation(getActivity);
		getActivity.getBody().addToStatements("return " + map.getter() + "()");
		actionClass.addToOperations(getActivity);
	}
	
	protected void addSetStatusEnabledInContructor(OJAnnotatedClass initControlNode) {
		OJConstructor constructor = initControlNode.findConstructor(new OJPathName("java.lang.Boolean"));
		OJSimpleStatement instantiateNode = new OJSimpleStatement();
		instantiateNode.setExpression(NameConverter.decapitalize("setNodeStatus(NodeStatus.ENABLED)"));
		initControlNode.addToImports(TinkerBehaviorUtil.tinkerNodeStatusPathName);
		constructor.getBody().addToStatements(instantiateNode);
	}
	
	protected void removeGetIncoming(OJAnnotatedClass ojClass) {
		ojClass.removeFromOperations(ojClass.findOperation("getIncoming", Collections.emptyList()));
	}

	protected void removeGetOutgoing(OJAnnotatedClass ojClass) {
		ojClass.removeFromOperations(ojClass.findOperation("getOutgoing", Collections.emptyList()));
	}

	protected void addGetOutFlow(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getOutFlow = new OJAnnotatedOperation("getOutFlow");

		INakedActivityEdge outEdge = oa.getOutgoing().iterator().next();
		OJPathName path = OJUtil.packagePathname(outEdge.getNameSpace()).getCopy();
		path.addToNames(NameConverter.capitalize(outEdge.getName()));
		getOutFlow.setReturnType(path);

		for (INakedActivityEdge edge : oa.getOutgoing()) {
			getOutFlow.getBody().addToStatements("return " + TinkerBehaviorUtil.edgeGetter(edge) + "()");
		}
		actionClass.addToOperations(getOutFlow);
	}

	protected OJAnnotatedClass findJavaClassForActivityNode(INakedActivityNode node) {
		OJPathName path = OJUtil.packagePathname(node.getNameSpace());
		OJPathName copy = path.getCopy();
		copy.addToNames(TinkerBehaviorUtil.activityNodePathName(node).getLast());
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findClass(copy);
		if (owner == null) {
			owner = (OJAnnotatedClass) this.javaModel.findClass(copy);
		}
		return owner;
	}

	protected void initVertexInConstructor(OJAnnotatedClass controlNodeClass, INakedActivityNode controlNode, OJConstructor defaultConstructor) {
		defaultConstructor.addParam(TinkerGenerationUtil.PERSISTENT_CONSTRUCTOR_PARAM_NAME, new OJPathName("java.lang.Boolean"));
		defaultConstructor.getBody().addToStatements("super(true, \"" + controlNode.getName() + "\")");
		defaultConstructor.getBody().addToStatements("setNodeStatus(NodeStatus.INACTIVE)");
		controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerNodeStatusPathName);
		controlNodeClass.addToConstructors(defaultConstructor);
	}

	protected void implementGetResult(OJAnnotatedClass actionClass, INakedOutputPin oa) {
		OJAnnotatedOperation getResult = new OJAnnotatedOperation("getResult");
		TinkerGenerationUtil.addOverrideAnnotation(getResult);
		TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.get(oa);
		getResult.setReturnType(map.javaBaseTypePath());
		getResult.getBody().addToStatements("return this." + map.getter() + "()");
		actionClass.addToOperations(getResult);
	}

	protected void implementGetValue(OJAnnotatedClass actionClass, INakedInputPin value) {
		OJAnnotatedOperation getValue = new OJAnnotatedOperation("getValue");
		TinkerGenerationUtil.addOverrideAnnotation(getValue);
		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(value.getAction().getNameSpace(), value.getAction());
		TinkerStructuralFeatureMap map =TinkerActivityNodeMapFactory.getPinVariableInActionAssociationMap(concreteEmulatedClassifier, (value));
		getValue.setReturnType(map.javaBaseTypePath());
		getValue.getBody().addToStatements("return this." + map.getter() + "()");
		actionClass.addToOperations(getValue);
	}

	protected void addCreateComponentConstructor(OJConstructor constructor, OJClass actionClass, INakedAcceptEventAction oa) {
		constructor.getBody().addToStatements("createComponents()");
	}

	protected void addCopyEventToOutputPin(OJAnnotatedClass actionClass, INakedAcceptEventAction oa) {
		OJAnnotatedOperation copyEventToOutputPin = new OJAnnotatedOperation("copyEventToOutputPin");
		TinkerGenerationUtil.addOverrideAnnotation(copyEventToOutputPin);
		copyEventToOutputPin.addParam("event", TinkerBehaviorUtil.tinkerIEventPathName.getCopy());
		// TODO think about many triggers here, for now kinda assuming one
		for (INakedTrigger trigger : oa.getTriggers()) {

			if (trigger.getEvent() instanceof INakedSignalEvent) {
				OJIfStatement ifSignal = new OJIfStatement("event instanceof " + TinkerBehaviorUtil.tinkerISignalEventPathName.getLast());
				ifSignal.addToThenPart(TinkerBehaviorUtil.tinkerISignalEventPathName.getLast() + " signalEvent = (" + TinkerBehaviorUtil.tinkerISignalEventPathName.getLast()
						+ ")event");
				actionClass.addToImports(TinkerBehaviorUtil.tinkerISignalEventPathName);
				INakedSignalEvent signalEvent = (INakedSignalEvent) trigger.getEvent();
				INakedSignal signal = signalEvent.getSignal();
				String signalClassName = OJUtil.classifierPathname(signal).getLast();
				actionClass.addToImports(OJUtil.classifierPathname(signal));
				OJIfStatement ifInstanceOf = new OJIfStatement("signalEvent.getSignal() instanceof " + signalClassName);
				ifInstanceOf.addToThenPart(signalClassName + " tmp  = (" + signalClassName + ")signalEvent.getSignal()");

				// Correlate signal attributes with output pins
				int i = 0;
				for (INakedOutputPin outputPin : oa.getOutput()) {
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(signal.getOwnedAttributes().get(i++));
					TinkerStructuralFeatureMap pinMap = TinkerActivityNodeMapFactory.get(outputPin);
					ifInstanceOf.addToThenPart(pinMap.getter()
							+ "().addOutgoingToken(new "
							+ (outputPin.getNakedMultiplicity().isOne() ? TinkerBehaviorUtil.tinkerSingleObjectToken.getLast() : TinkerBehaviorUtil.tinkerCollectionObjectToken
									.getLast()) + "<" + map.javaBaseTypePath().getLast() + ">(" + pinMap.getter() + "().getName(), tmp." + map.getter() + "()))");
					actionClass.addToImports(map.javaBaseTypePath());
					if (outputPin.getNakedMultiplicity().isOne()) {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerSingleObjectToken);
					} else {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerCollectionObjectToken);
					}
				}

				ifSignal.addToThenPart(ifInstanceOf);
				copyEventToOutputPin.getBody().addToStatements(ifSignal);
				copyEventToOutputPin.getBody().addToStatements(ifSignal);
			} else {
				if (!(oa instanceof INakedAcceptCallAction)) {
					throw new IllegalStateException("Excepting a INakedAcceptCallAction found " + oa.getClass().getSimpleName());
				}
				if (!(trigger.getEvent() instanceof INakedCallEvent)) {
					throw new IllegalStateException("Excepting a INakedCallEvent");
				}
				INakedAcceptCallAction callAction = (INakedAcceptCallAction) oa;

				INakedCallEvent callEvent = (INakedCallEvent) trigger.getEvent();
				INakedOperation operation = callEvent.getOperation();

				ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(callEvent.getNameSpace(), callEvent);
				OJPathName eventClassifierPathname = OJUtil.classifierPathname(concreteEmulatedClassifier);
				actionClass.addToImports(eventClassifierPathname);
				copyEventToOutputPin.getBody().addToStatements(eventClassifierPathname.getLast() + " callEvent = (" + eventClassifierPathname.getLast() + ")event");

				// Correlate signal attributes with output pins
				int i = 0;
				for (INakedOutputPin outputPin : oa.getOutput()) {
					if (callAction.getReturnInfo().equals(outputPin)) {
						continue;
					}
					TypedElementPropertyBridge bridge = new TypedElementPropertyBridge(concreteEmulatedClassifier, operation.getArgumentParameters().get(i++));
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(bridge);
					TinkerStructuralFeatureMap pinMap = TinkerActivityNodeMapFactory.get(outputPin);
					copyEventToOutputPin.getBody().addToStatements(
							pinMap.getter()
									+ "().addOutgoingToken(new "
									+ (outputPin.getNakedMultiplicity().isOne() ? TinkerBehaviorUtil.tinkerSingleObjectToken.getLast()
											: TinkerBehaviorUtil.tinkerCollectionObjectToken.getLast()) + "<" + map.javaBaseTypePath().getLast() + ">(" + pinMap.getter()
									+ "().getName(), callEvent." + map.getter() + "()))");

					if (outputPin.getNakedMultiplicity().isOne()) {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerSingleObjectToken);
					} else {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerCollectionObjectToken);
					}

					actionClass.addToImports(map.javaBaseTypePath());
				}

			}

		}
		actionClass.addToOperations(copyEventToOutputPin);

	}

	protected void implementGetTriggers(OJAnnotatedClass actionClass, INakedAcceptEventAction oa) {
		TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
		tinkerAttributeImplementor.setJavaModel(this.javaModel);
		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(oa.getNameSpace(), oa);

		StringBuilder sb = new StringBuilder();
		sb.append("return Arrays.asList(");
		boolean first = true;
		for (INakedTrigger trigger : oa.getTriggers()) {
			TypedElementPropertyBridge bridge = new TypedElementPropertyBridge(concreteEmulatedClassifier, new TriggerBridge(concreteEmulatedClassifier, trigger));
			bridge.setComposite(true);
			bridge.isComposite();
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(bridge);
			tinkerAttributeImplementor.implementAttributeFully(concreteEmulatedClassifier, map);
			sb.append(map.getter());
			sb.append("()");
			if (!first) {
				sb.append(", ");
			}
			first = false;
			
			// Add to createComponent to constructor
			TinkerComponentInitializer tinkerComponentInitializer = new TinkerComponentInitializer();
			tinkerComponentInitializer.setJavaModel(this.javaModel);
			
			OJAnnotatedOperation createComponents = (OJAnnotatedOperation) actionClass.findOperation("createComponents", Collections.emptyList());
			if (createComponents==null) {
				createComponents = new OJAnnotatedOperation("createComponents");
				createComponents.setBody(new OJBlock());
				actionClass.addToOperations(createComponents);
			}
			tinkerComponentInitializer.initProperty(createComponents, bridge);

		}
		OJAnnotatedOperation getTrigger = new OJAnnotatedOperation("getTrigger");
		TinkerGenerationUtil.addOverrideAnnotation(getTrigger);
		getTrigger.setReturnType(new OJPathName("List").addToGenerics(new OJPathName("? extends " + TinkerBehaviorUtil.tinkerITriggerPathName.getCopy().getLast())));
		actionClass.addToImports(new OJPathName("java.util.List"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerITriggerPathName.getCopy());
		getTrigger.getBody().addToStatements(sb.toString().substring(0, sb.length()) + ")");
		actionClass.addToOperations(getTrigger);
	}

	protected void addAddToInputPinVariable(OJClass actionClass, INakedAction oa) {
		OJAnnotatedOperation addToInputPinVariable = new OJAnnotatedOperation("addToInputPinVariable");
		TinkerGenerationUtil.addOverrideAnnotation(addToInputPinVariable);

		if (oa instanceof INakedOclAction && ((INakedOclAction) oa).getReturnPin() != null) {
			addToInputPinVariable.addParam("inputPin", TinkerBehaviorUtil.tinkerIInputPinPathName.getCopy().addToGenerics(new OJPathName("?")).addToGenerics(new OJPathName("?")));
			addToInputPinVariable.addParam("elements", new OJPathName("Collection<?>"));
		} else {
			addToInputPinVariable.addParam("inputPin", TinkerBehaviorUtil.tinkerIInputPinPathName.getCopy());
			addToInputPinVariable.addParam("elements", new OJPathName("Collection"));
		}
		actionClass.addToImports(new OJPathName("java.util.Collection"));
		for (INakedInputPin inputPin : oa.getInput()) {
			if ((oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
				continue;
			}

			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
			OJIfStatement ifInputPinIsType = new OJIfStatement("inputPin instanceof " + TinkerBehaviorUtil.activityNodePathName(inputPin).getLast());
			addToInputPinVariable.getBody().addToStatements(ifInputPinIsType);
			if (map.isOne()) {
				ifInputPinIsType.addToThenPart(map.setter() + "((" + map.javaBaseTypePath().getLast() + ")(!elements.isEmpty() ? elements.iterator().next() : null))");
			} else {
				ifInputPinIsType.addToThenPart(map.allAdder() + "(new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseTypePath().getLast() + ">((Collection<"
						+ map.javaBaseTypePath().getLast() + ">)elements))");
			}
		}

		actionClass.addToOperations(addToInputPinVariable);
	}

	protected void implementGenerics(OJAnnotatedClass actionClass, INakedVariableAction oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getVariable());
		actionClass.getSuperclass().addToGenerics(map.javaBaseTypePath());
	}

//	protected void addGetContextObject(OJClass actionClass, INakedBehavioredClassifier context) {
//		OJAnnotatedOperation getContext = new OJAnnotatedOperation("getContext");
//		getContext.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
//		getContext.setReturnType(OJUtil.classifierPathname(context));
//		getContext.getBody().addToStatements("return this.contextObject");
//		actionClass.addToOperations(getContext);
//
//		OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject");
//		getContextObject.setReturnType(OJUtil.classifierPathname(context));
//		getContextObject.getBody().addToStatements("return this.getContext()");
//		actionClass.addToOperations(getContextObject);
//	}

}
