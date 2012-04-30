package org.nakeduml.tinker.activity;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActivityNodeGenerator.class }, after = { TinkerActivityNodeGenerator.class })
public class TinkerOperationGenerator extends StereotypeAnnotator {

	@VisitBefore(matchSubclasses = true, match = { INakedOperation.class })
	public void visitOperation(INakedOperation oper) {
		NakedOperationMap map = OJUtil.buildOperationMap(oper);
		INakedClassifier owner = oper.getOwner();
		OJAnnotatedClass ownerClass = findJavaClass(owner);
		OJAnnotatedOperation operation = new OJAnnotatedOperation(oper.getName());
		operation.setReturnType(map.javaReturnTypePath());
		ownerClass.addToOperations(operation);

		for (INakedParameter param : oper.getOwnedParameters()) {
			if (!param.isReturn()) {
				OJParameter p = new OJParameter();
				NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(owner, param);
				p.setName(pMap.fieldname());
				if (pMap.isOne() && (param.getNakedBaseType() instanceof INakedSimpleType
						&& (param.getDirection() == ParameterDirectionKind.OUT || param.getDirection() == ParameterDirectionKind.INOUT))) {
					p.setType(TinkerGenerationUtil.convertToMutable(pMap.javaTypePath()));
				} else {
					p.setType(pMap.javaTypePath());
				}
				operation.addToParameters(p);
				operation.getOwner().addToImports(pMap.javaTypePath());
			}
		}

		if (!oper.getMethods().isEmpty()) {
			addMethodForOperation(oper, owner, operation);
		} else {
			addInvokeAcceptCallAction(oper, operation);
			addInvokeAcceptCallActionSync(ownerClass, oper);
		}

		removeClassForOperationIfExist(oper);

	}

	private void removeClassForOperationIfExist(INakedOperation oper) {
		OJClass c = findJavaClass(oper.getMessageStructure());
		if (c != null) {
			super.deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, c.getPathName());
		}
	}

	private void addInvokeAcceptCallActionSync(OJAnnotatedClass ownerClass, INakedOperation oper) {
		INakedCallEvent event = null;
		INakedNameSpace x = oper.getContext().getParent();
		for (INakedElement e : x.getOwnedElements()) {
			if (e instanceof INakedCallEvent && ((INakedCallEvent) e).getOperation().equals(oper)) {
				event = (INakedCallEvent) e;
				break;
			}
		}
		if (event == null) {
			throw new IllegalStateException("A call without a method must have a corresponding event in the owning package");
		}
		INakedAcceptCallAction callAction = TinkerBehaviorUtil.findCallActionForEventAndClassifier(event, (INakedBehavioredClassifier) oper.getOwner());
		OJAnnotatedOperation invokeAcceptCallAction = new OJAnnotatedOperation("invoke"
				+ NameConverter.capitalize(callAction.getActivity().getName() + NameConverter.capitalize(callAction.getName())));
		OJParameter eventParam = new OJParameter("event", TinkerBehaviorUtil.tinkerIEventPathName);
		eventParam.setFinal(true);
		invokeAcceptCallAction.addToParameters(eventParam);

		NakedOperationMap operMap = OJUtil.buildOperationMap(oper);
		if (oper.getReturnParameter() != null) {
			invokeAcceptCallAction.setReturnType(operMap.javaReturnTypePath());
		}

		OJAnnotatedField acceptCallActionField = new OJAnnotatedField("acceptCallAction", TinkerBehaviorUtil.activityNodePathName(callAction));
		acceptCallActionField.setInitExp("null");
		invokeAcceptCallAction.getBody().addToLocals(acceptCallActionField);

		invokeAcceptCallAction.getBody().addToStatements(TinkerBehaviorUtil.tinkerAbstractActivityPathName.getLast() + " m = getFirstActivityForCallEvent(event)");
		ownerClass.addToImports(TinkerBehaviorUtil.tinkerAbstractActivityPathName);
		invokeAcceptCallAction.getBody().addToStatements(
				"Set<" + TinkerBehaviorUtil.tinkerIActivityNodePathName.getLast()
						+ "<? extends Token, ? extends Token>> nodesToTrigger = m.getEnabledNodesWithMatchingTrigger(event)");

		OJIfStatement ifNodesToTrigger = new OJIfStatement("!nodesToTrigger.isEmpty()");
		ifNodesToTrigger.addToThenPart("acceptCallAction = (" + TinkerBehaviorUtil.activityNodePathName(callAction).toJavaString() + ")nodesToTrigger.iterator().next()");

		ownerClass.addToImports(TinkerBehaviorUtil.tinkerAcceptCallAction);
		if (!oper.getArgumentParameters().isEmpty()) {
			ifNodesToTrigger.addToThenPart("acceptCallAction.setTrigger(event)");
		}
		String tokenType = "ControlToken";
		ifNodesToTrigger.addToThenPart("acceptCallAction.setStarts(new SingleIterator<" + tokenType + ">(new " + tokenType + "(acceptCallAction.getName())))");
		ifNodesToTrigger.addToThenPart("acceptCallAction.next()");
		invokeAcceptCallAction.getBody().addToStatements(ifNodesToTrigger);
		if (oper.getReturnParameter() != null) {
			invokeAcceptCallAction.getBody().addToStatements("return (" + operMap.javaReturnTypePath().getLast() + ")acceptCallAction.getReplyAction().getReply()");
		} else {
			invokeAcceptCallAction.getBody().addToStatements("acceptCallAction.getReplyAction().getReply()");
		}

		ownerClass.addToOperations(invokeAcceptCallAction);
	}

	private void addInvokeAcceptCallAction(INakedOperation oper, OJAnnotatedOperation operation) {
		// Find events for this call
		INakedCallEvent event = null;
		INakedNameSpace x = oper.getContext().getParent();
		for (INakedElement e : x.getOwnedElements()) {
			if (e instanceof INakedCallEvent && ((INakedCallEvent) e).getOperation().equals(oper)) {
				event = (INakedCallEvent) e;
				break;
			}
		}
		if (event == null) {
			throw new IllegalStateException("A call without a method must have a corresponding event in the owning package");
		}
		INakedAcceptCallAction callAction = TinkerBehaviorUtil.findCallActionForEventAndClassifier(event, (INakedBehavioredClassifier) oper.getOwner());

		OJPathName path = OJUtil.packagePathname(event.getNameSpace());
		OJPackage eventPackage = findOrCreatePackage(path);
		OJAnnotatedClass eventClass = null;
		for (OJClass ojClass : eventPackage.getClasses()) {
			if (ojClass.getName().equals(TinkerBehaviorUtil.eventName(event))) {
				eventClass = (OJAnnotatedClass) ojClass;
				break;
			}
		}
		OJPathName eventPathName = eventClass.getPathName();
		operation.getBody().addToStatements(eventPathName.getLast() + " event = new " + eventPathName.getLast() + "(true)");
		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(event.getNameSpace(), event);
		for (INakedParameter p : event.getOperation().getArgumentParameters()) {
			TypedElementPropertyBridge bridge = new TypedElementPropertyBridge(concreteEmulatedClassifier, p);
			NakedStructuralFeatureMap paramMap = new NakedStructuralFeatureMap(bridge);
			operation.getBody().addToStatements("event." + paramMap.setter() + "(" + paramMap.fieldname() + ")");
		}

		// operation.getBody().addToStatements("addToEventPool(event)");
		if (oper.getReturnParameter() != null) {
			operation.getBody().addToStatements(
					"return invoke" + NameConverter.capitalize(callAction.getActivity().getName()) + NameConverter.capitalize(callAction.getName()) + "(event)");
		} else {
			operation.getBody().addToStatements(
					"invoke" + NameConverter.capitalize(callAction.getActivity().getName()) + NameConverter.capitalize(callAction.getName()) + "(event)");
		}

	}

	private void addMethodForOperation(INakedOperation oper, INakedClassifier owner, OJAnnotatedOperation operation) {
		for (INakedBehavior method : oper.getMethods()) {
			OJPathName activityPathName = OJUtil.classifierPathname(method);

			// Add validation for out parameters, may not have a vertex
			for (INakedParameter param : oper.getOwnedParameters()) {
				if (param.getDirection() == ParameterDirectionKind.OUT && !param.isReturn()) {
					NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(owner, param);

					if (param.getNakedBaseType() instanceof INakedSimpleType) {
						OJIfStatement ifHasVertex = new OJIfStatement(TinkerGenerationUtil.validateMutableCondition(pMap));
						ifHasVertex.addToThenPart("throw new IllegalStateException(\"Out parameter, " + param.getName() + " may not have a value!\")");
						operation.getBody().addToStatements(ifHasVertex);
					} else {
						if (pMap.isOne()) {
							OJIfStatement ifHasVertex = new OJIfStatement(pMap.fieldname() + ".getVertex() != null");
							ifHasVertex.addToThenPart("throw new IllegalStateException(\"Out parameter, " + param.getName() + " may not have a vertex!\")");
							operation.getBody().addToStatements(ifHasVertex);
						} else {
							OJIfStatement ifHasVertex = new OJIfStatement("!" + pMap.fieldname() + ".isEmpty()");
							ifHasVertex.addToThenPart("throw new IllegalStateException(\"Out parameter, " + param.getName() + " may not have a vertex!\")");
							operation.getBody().addToStatements(ifHasVertex);
						}
					}

				}
			}
			// Add validation for in & inout parameters, must have a vertex
			for (INakedParameter param : oper.getOwnedParameters()) {
				if (param.getDirection() == ParameterDirectionKind.INOUT || param.getDirection() == ParameterDirectionKind.IN) {
					NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(owner, param);
					INakedProperty prop = pMap.getProperty();
					if (!(prop.getBaseType() instanceof INakedSimpleType || prop.getBaseType() instanceof INakedEnumeration)) {
						OJIfStatement ifHasVertex = new OJIfStatement(pMap.fieldname() + ".getVertex() == null");
						ifHasVertex.addToThenPart("throw new IllegalStateException(\" " + (param.getDirection() == ParameterDirectionKind.INOUT ? "INOUT" : "IN") + " parameter, "
								+ param.getName() + " must have a vertex!\")");
						operation.getBody().addToStatements(ifHasVertex);
					}

				}
			}

			operation.getBody()
					.addToStatements(activityPathName.getLast() + " " + NameConverter.decapitalize(method.getName()) + " = new " + activityPathName.getLast() + "(this)");

			if (method.getArgumentParameters().size() != oper.getArgumentParameters().size()) {
				throw new IllegalStateException("Operation parameters and behavior parameters do not match up");
			}
			String executeParams = "";
			boolean first = true;
			for (INakedParameter param : oper.getOwnedParameters()) {
				if (!param.isReturn()) {
					NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(owner, param);
					if (!first) {
						executeParams += ", ";
					}
					first = false;
					executeParams += pMap.fieldname();
				}
			}

			if (oper.getReturnParameter() != null && oper.getReturnParameter().isReturn()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oper.getOwner(), oper.getReturnParameter());
				operation.getBody().addToStatements(
						map.javaBaseTypePath().getLast() + " result = " + NameConverter.decapitalize(method.getName()) + ".execute(" + executeParams + ")");
			} else {
				operation.getBody().addToStatements(NameConverter.decapitalize(method.getName()) + ".execute(" + executeParams + ")");
			}

			for (INakedParameter param : oper.getArgumentParameters()) {
				NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(owner, param);
				if (!(pMap.getProperty().getBaseType() instanceof INakedSimpleType)) {
					operation.getBody().addToStatements(pMap.fieldname() + ".clearCache()");
				}
			}

			// TODO this complicates testing for now
			// OJIfStatement ifFinished = new
			// OJIfStatement(NameConverter.decapitalize(method.getName()) +
			// ".isFinished()");
			// ifFinished.addToThenPart(NameConverter.decapitalize(method.getName())
			// + ".markDeleted()");
			// operation.getBody().addToStatements(ifFinished);

			if (oper.getReturnParameter() != null && oper.getReturnParameter().isReturn()) {
				operation.getBody().addToStatements("return result");
			}
		}
	}

}
