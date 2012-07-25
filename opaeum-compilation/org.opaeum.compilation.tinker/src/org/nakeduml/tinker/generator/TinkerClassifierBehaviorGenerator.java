package org.nakeduml.tinker.generator;

import java.util.Collection;

import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedBehavioredClassifier;
import org.eclipse.uml2.uml.INakedReception;
import org.eclipse.uml2.uml.INakedSignal;
import org.eclipse.uml2.uml.INakedSimpleType;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJAnnonymousInnerClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerImplementNodeStep.class }, after = { TinkerImplementNodeStep.class })
public class TinkerClassifierBehaviorGenerator extends StereotypeAnnotator {

	@VisitAfter(matchSubclasses = true)
	public void visitSignal(INakedBehavioredClassifier c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType) && c.getClassifierBehavior() != null) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			implementReceiveSignal(ojClass, c);
			implementReception(ojClass, c);
		}
	}

	private void implementReception(OJAnnotatedClass ojClass, INakedBehavioredClassifier c) {
		Collection<INakedReception> receptions = c.getOwnedReceptions();
		for (INakedReception reception : receptions) {
			OJAnnotatedOperation receptionOper = new OJAnnotatedOperation(NameConverter.decapitalize(reception.getName()));
			OJParameter parameter = new OJParameter("signal", TinkerBehaviorUtil.signalPathName);
			parameter.setFinal(true);
			receptionOper.addToParameters(parameter);
			OJAnnonymousInnerClass classifierSignalEvent = new OJAnnonymousInnerClass(ojClass.getPathName(), "classifierSignalEvent",
					TinkerBehaviorUtil.tinkerClassifierSignalEvent);
			receptionOper.getBody().addToLocals(classifierSignalEvent);

			OJAnnotatedOperation call = new OJAnnotatedOperation("call");
			call.setReturnType(new OJPathName("java.lang.Boolean"));
			call.addToThrows(new OJPathName("java.lang.Exception"));
			call.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
			call.getBody().addToStatements("GraphDb.getDb().startTransaction()");
			OJTryStatement tryS = new OJTryStatement();
			tryS.getTryPart().addToStatements("removeFromEventPool(signal)");

			INakedBehavior method = reception.getMethods().iterator().next();
			if (method == c.getClassifierBehavior()) {
				tryS.getTryPart().addToStatements(
						"Set<" + TinkerBehaviorUtil.tinkerActivityNodePathName.getLast()
								+ "<? extends Token>> nodesToTrigger = getClassifierBehavior().getEnabledNodesWithMatchingTrigger(signal)");
			} else {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(method.getEndToComposite().getOtherEnd());
				tryS.getTryPart().addToStatements(OJUtil.classifierPathname(method).getLast() + " m = new " + OJUtil.classifierPathname(method).getLast() + "(" + "this)");
				tryS.getTryPart().addToStatements(
						"Set<" + TinkerBehaviorUtil.tinkerActivityNodePathName.getLast() + "<? extends Token>> nodesToTrigger = m.getEnabledNodesWithMatchingTrigger(signal)");
			}

			OJIfStatement ifNodesToTrigger = new OJIfStatement("!nodesToTrigger.isEmpty()");
			String tokenType;
			if (reception.getSignal().getEffectiveAttributes().isEmpty()) {
				ojClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
				tokenType = "ControlToken";
			} else {
				ojClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
				tokenType = "ObjectToken";
			}
			ifNodesToTrigger.addToThenPart(TinkerBehaviorUtil.tinkerAcceptEventAction.getLast() + " acceptEventAction = (" + TinkerBehaviorUtil.tinkerAcceptEventAction.getLast()
					+ ")nodesToTrigger.iterator().next()");
			ojClass.addToImports(TinkerBehaviorUtil.tinkerAcceptEventAction);
			if (!reception.getSignal().getOwnedAttributes().isEmpty()) {
				ifNodesToTrigger.addToThenPart("acceptEventAction.setTrigger(signal)");
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

			ojClass.addToOperations(receptionOper);

			receptionOper.getBody().addToStatements("addToEventPool(signal)");
			receptionOper.getBody().addToStatements(TinkerBehaviorUtil.tinkerClassifierBehaviorExecutorService.getLast() + ".INSTANCE.submit(classifierSignalEvent)");
			ojClass.addToImports(TinkerBehaviorUtil.tinkerClassifierBehaviorExecutorService);
		}
	}

	private void implementReceiveSignal(OJAnnotatedClass ojClass, INakedBehavioredClassifier c) {
		OJAnnotatedOperation receiveSignal = new OJAnnotatedOperation("receiveSignal");
		receiveSignal.addParam("signal", TinkerBehaviorUtil.signalPathName);
		Collection<INakedReception> receptions = c.getOwnedReceptions();
		for (INakedReception reception : receptions) {
			INakedSignal signal = reception.getSignal();
			OJIfStatement ifHasReception = new OJIfStatement("signal instanceof " + signal.getMappingInfo().getJavaName());
			ifHasReception.addToThenPart(NameConverter.decapitalize(reception.getName()) + "((" + signal.getMappingInfo().getJavaName() + ")signal)");
			receiveSignal.getBody().addToStatements(ifHasReception);
		}
		ojClass.addToOperations(receiveSignal);
	}

}
