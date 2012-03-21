package org.nakeduml.tinker.activity;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJAnnonymousInnerClass;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.name.NameConverter;

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
			if (param.getDirection() == ParameterDirectionKind.IN || param.getDirection() == ParameterDirectionKind.INOUT) {
				OJParameter p = new OJParameter();
				NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(owner, param);
				p.setName(pMap.fieldname());
				p.setType(pMap.javaTypePath());
				operation.addToParameters(p);
				operation.getOwner().addToImports(pMap.javaTypePath());
			}
		}

		if (!oper.getMethods().isEmpty()) {
			addMethodForOperation(oper, owner, operation);
		} else {
			addInvokeAcceptCallAction(oper, operation);
			addInvokeAcceptCallActionInternal(ownerClass, oper);
		}

	}

	private void addInvokeAcceptCallActionInternal(OJAnnotatedClass ojClass, INakedOperation oper) {
		OJAnnotatedOperation invokeAcceptCallAction = new OJAnnotatedOperation("invokeAcceptCallAction");
		OJParameter eventParam = new OJParameter("event", TinkerBehaviorUtil.tinkerIEventPathName);
		eventParam.setFinal(true);
		invokeAcceptCallAction.addToParameters(eventParam);
		OJAnnonymousInnerClass classifierSignalEvent = new OJAnnonymousInnerClass(ojClass.getPathName(), "classifierSignalEvent",
				TinkerBehaviorUtil.tinkerClassifierSignalEvent);
		invokeAcceptCallAction.getBody().addToLocals(classifierSignalEvent);
		
		OJAnnotatedOperation call = new OJAnnotatedOperation("call");
		call.setReturnType(new OJPathName("java.lang.Boolean"));
		call.addToThrows(new OJPathName("java.lang.Exception"));
		call.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		call.getBody().addToStatements("GraphDb.getDb().startTransaction()");
		OJTryStatement tryS = new OJTryStatement();
		tryS.getTryPart().addToStatements("removeFromEventPool(event)");

		ojClass.addToImports(TinkerBehaviorUtil.tinkerAbstractActivityPathName);
		tryS.getTryPart().addToStatements(TinkerBehaviorUtil.tinkerAbstractActivityPathName.getLast() + " m = getFirstActivityForCallEvent(event)");
		tryS.getTryPart().addToStatements(
				"Set<" + TinkerBehaviorUtil.tinkerActivityNodePathName.getLast()
						+ "<? extends Token, ? extends Token>> nodesToTrigger = m.getEnabledNodesWithMatchingTrigger(event)");

		OJIfStatement ifNodesToTrigger = new OJIfStatement("!nodesToTrigger.isEmpty()");
		String tokenType;
		ojClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		tokenType = "ControlToken";
		ifNodesToTrigger.addToThenPart(TinkerBehaviorUtil.tinkerAcceptEventAction.getLast() + " acceptEventAction = (" + TinkerBehaviorUtil.tinkerAcceptEventAction.getLast()
				+ ")nodesToTrigger.iterator().next()");
		ojClass.addToImports(TinkerBehaviorUtil.tinkerAcceptEventAction);
		if (!oper.getArgumentParameters().isEmpty()) {
			ifNodesToTrigger.addToThenPart("acceptEventAction.setTrigger(event)");
		}
		ifNodesToTrigger.addToThenPart("acceptEventAction.setStarts(new SingleIterator<" + tokenType + ">(new " + tokenType + "(acceptEventAction.getName())))");
		ifNodesToTrigger.addToThenPart("acceptEventAction.next()");
		tryS.getTryPart().addToStatements(ifNodesToTrigger);
		ojClass.addToImports(TinkerBehaviorUtil.tinkerActivityNodePathName);
		ojClass.addToImports(new OJPathName("java.util.Set"));
		ojClass.addToImports(TinkerBehaviorUtil.tinkerSingleIteratorPathName);
		ojClass.addToImports(TinkerBehaviorUtil.tinkerTokenPathName);

		tryS.getTryPart().addToStatements("GraphDb.getDb().stopTransaction(Conclusion.SUCCESS)");
		ojClass.addToImports(TinkerGenerationUtil.tinkerConclusionPathName);
		tryS.setCatchParam(new OJParameter("e", new OJPathName("java.loang.Exception")));
		tryS.getCatchPart().addToStatements("GraphDb.getDb().stopTransaction(Conclusion.FAILURE)");
		tryS.getCatchPart().addToStatements("throw e");
		call.getBody().addToStatements(tryS);
		call.getBody().addToStatements("return false; //TODO");
		classifierSignalEvent.getClassDeclaration().addToOperations(call);
		
		ojClass.addToOperations(invokeAcceptCallAction);
		
		invokeAcceptCallAction.getBody().addToStatements(TinkerBehaviorUtil.tinkerClassifierBehaviorExecutorService.getLast() + ".INSTANCE.submit(classifierSignalEvent)");

	}

	private void addInvokeAcceptCallAction(INakedOperation oper, OJAnnotatedOperation operation) {
		// Find events for this call
		INakedCallEvent event = null;
		INakedNameSpace x = oper.getContext().getParent();
		for (INakedElement e : x.getOwnedElements()) {
			if (e instanceof INakedCallEvent) {
				event = (INakedCallEvent) e;
				break;
			}
		}
		if (event == null) {
			throw new IllegalStateException("A call without a method must have a corresponding event in the owning package");
		}
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

		operation.getBody().addToStatements("addToEventPool(event)");
		operation.getBody().addToStatements("invokeAcceptCallAction(event)");
		
	}

	private void addMethodForOperation(INakedOperation oper, INakedClassifier owner, OJAnnotatedOperation operation) {
		for (INakedBehavior method : oper.getMethods()) {
			OJPathName activityPathName = OJUtil.classifierPathname(method);
			operation.getBody()
					.addToStatements(activityPathName.getLast() + " " + NameConverter.decapitalize(method.getName()) + " = new " + activityPathName.getLast() + "(this)");

			if (method.getArgumentParameters().size() != oper.getOwnedParameters().size()) {
				throw new IllegalStateException("Operation parameters and behavior parameters do not match up");
			}
			String executeParams = "";
			boolean first = true;
			for (INakedParameter param : oper.getArgumentParameters()) {
				NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(owner, param);
				if (!first) {
					executeParams += ", ";
					first = false;
				}
				executeParams += pMap.fieldname();
			}
			operation.getBody().addToStatements(NameConverter.decapitalize(method.getName()) + ".execute(" + executeParams + ")");
			OJIfStatement ifFinished = new OJIfStatement(NameConverter.decapitalize(method.getName()) + ".isFinished()");
			ifFinished.addToThenPart(NameConverter.decapitalize(method.getName()) + ".markDeleted()");
			operation.getBody().addToStatements(ifFinished);
		}
	}

}
