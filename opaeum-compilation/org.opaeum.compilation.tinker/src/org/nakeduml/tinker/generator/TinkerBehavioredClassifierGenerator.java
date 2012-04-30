package org.nakeduml.tinker.generator;

import java.util.Collection;

import org.nakeduml.tinker.activity.ConcreteEmulatedClassifier;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJAnnonymousInnerClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedReception;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.internal.NonInverseArtificialProperty;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerImplementNodeStep.class }, after = { TinkerImplementNodeStep.class })
public class TinkerBehavioredClassifierGenerator extends StereotypeAnnotator {

	@VisitAfter(matchSubclasses = true)
	public void visitBehavioredClassifier(INakedBehavioredClassifier c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType) && !c.getOwnedBehaviors().isEmpty()) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			implementReceiveSignal(ojClass, c);
			implementReception(ojClass, c);
			implementGetAllActivities(ojClass, c);
		}
	}
	
	private void implementGetAllActivities(OJAnnotatedClass ojClass, INakedBehavioredClassifier c) {
		OJAnnotatedOperation getAllActivities = new OJAnnotatedOperation("getAllActivities");
		TinkerGenerationUtil.addOverrideAnnotation(getAllActivities);
		OJField result = new OJField();
		result.setName("result");
		result.setType(new OJPathName("java.util.List"));
		result.getType().addToGenerics(TinkerBehaviorUtil.tinkerAbstractActivityPathName.getCopy());
		ojClass.addToImports(TinkerBehaviorUtil.tinkerAbstractActivityPathName.getCopy());
		getAllActivities.setReturnType(result.getType());
		result.setInitExp("new ArrayList<"+TinkerBehaviorUtil.tinkerAbstractActivityPathName.getLast()+">()");
		getAllActivities.getBody().addToLocals(result);
		
		for (INakedProperty p : c.getOwnedAttributes()) {
			if (p.getBaseType() instanceof INakedBehavior) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
				if (p.getBaseType().equals(c.getClassifierBehavior())) {
					getAllActivities.getBody().addToStatements("result.add(this." + map.fieldname() + ")");
				} else {
					getAllActivities.getBody().addToStatements("result.addAll(this." + map.fieldname() + ")");
				}
			}
		}
		
		getAllActivities.getBody().addToStatements("return result");
		ojClass.addToOperations(getAllActivities);
	}	

	private void implementReception(OJAnnotatedClass ojClass, INakedBehavioredClassifier c) {
		Collection<INakedReception> receptions = c.getOwnedReceptions();
		for (INakedReception reception : receptions) {
			OJAnnotatedOperation receptionOper = new OJAnnotatedOperation(NameConverter.decapitalize(reception.getName()));
			OJParameter parameter = new OJParameter("event", TinkerBehaviorUtil.tinkerIEventPathName.getCopy());
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
			tryS.getTryPart().addToStatements("removeFromEventPool(event)");

			INakedBehavior method = reception.getMethods().iterator().next();
			if (method == c.getClassifierBehavior()) {
				tryS.getTryPart().addToStatements(
						"Set<" + TinkerBehaviorUtil.tinkerIActivityNodePathName.getLast()
								+ "<? extends Token, ? extends Token>> nodesToTrigger = getClassifierBehavior().getEnabledNodesWithMatchingTrigger(event)");
			} else {
				tryS.getTryPart().addToStatements(OJUtil.classifierPathname(method).getLast() + " m = new " + OJUtil.classifierPathname(method).getLast() + "(" + "this)");
				tryS.getTryPart().addToStatements(
						"Set<" + TinkerBehaviorUtil.tinkerIActivityNodePathName.getLast()
								+ "<? extends Token, ? extends Token>> nodesToTrigger = m.getEnabledNodesWithMatchingTrigger(event)");
			}

			OJIfStatement ifNodesToTrigger = new OJIfStatement("!nodesToTrigger.isEmpty()");
			ojClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
			String tokenType = "ControlToken";
			ifNodesToTrigger.addToThenPart(TinkerBehaviorUtil.tinkerAcceptEventAction.getLast() + " acceptEventAction = (" + TinkerBehaviorUtil.tinkerAcceptEventAction.getLast()
					+ ")nodesToTrigger.iterator().next()");
			ojClass.addToImports(TinkerBehaviorUtil.tinkerAcceptEventAction);
			if (!reception.getSignal().getOwnedAttributes().isEmpty()) {
				ifNodesToTrigger.addToThenPart("acceptEventAction.setTrigger(event)");
			}
			ifNodesToTrigger.addToThenPart("acceptEventAction.setStarts(new SingleIterator<" + tokenType + ">(new " + tokenType + "(acceptEventAction.getName())))");
			ifNodesToTrigger.addToThenPart("acceptEventAction.next()");
			tryS.getTryPart().addToStatements(ifNodesToTrigger);
			ojClass.addToImports(TinkerBehaviorUtil.tinkerIActivityNodePathName);
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

			receptionOper.getBody().addToStatements(TinkerBehaviorUtil.tinkerClassifierBehaviorExecutorService.getLast() + ".INSTANCE.submit(classifierSignalEvent)");
			ojClass.addToImports(TinkerBehaviorUtil.tinkerClassifierBehaviorExecutorService);
		}
	}

	private void implementReceiveSignal(OJAnnotatedClass ojClass, INakedBehavioredClassifier c) {
		OJAnnotatedOperation resolveReception = new OJAnnotatedOperation("resolveReception");
		resolveReception.addParam("event", TinkerBehaviorUtil.tinkerISignalEventPathName);

		OJAnnotatedOperation receiveSignal = new OJAnnotatedOperation("receiveSignal");
		receiveSignal.addParam("signal", TinkerBehaviorUtil.signalPathName);
		Collection<INakedReception> receptions = c.getOwnedReceptions();
		for (INakedReception reception : receptions) {
			INakedSignal signal = reception.getSignal();

			// Find events for this signal
			INakedSignalEvent event = null;
			INakedNameSpace x = c.getParent();
			for (INakedElement e : x.getOwnedElements()) {
				if (e instanceof INakedSignalEvent) {
					event = (INakedSignalEvent) e;
					break;
				}
			}
			if (event == null) {
				throw new IllegalStateException("A signal must have a corresponding event in the owning package");
			}
			ojClass.addToImports(TinkerBehaviorUtil.tinkerSignalEventPathName.getCopy());
			OJIfStatement ifHasReception = new OJIfStatement("signal instanceof " + signal.getMappingInfo().getJavaName());

			ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(event.getNameSpace(), event);
			TypedElementPropertyBridge bridge = new TypedElementPropertyBridge(concreteEmulatedClassifier, new NonInverseArtificialProperty(NameConverter.decapitalize(signal
					.getName()), signal, false));
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(bridge);

			ifHasReception.addToThenPart(event.getMappingInfo().getJavaName() + " " + event.getMappingInfo().getJavaName().getDecapped() + " = new "
					+ event.getMappingInfo().getJavaName() + "(true)");
			ifHasReception.addToThenPart(event.getMappingInfo().getJavaName().getDecapped() + "." + map.setter() + "((" + map.javaBaseTypePath().getLast() + ")signal)");
			ifHasReception.addToThenPart("addToEventPool(" + event.getMappingInfo().getJavaName().getDecapped() + ")");
			ifHasReception.addToThenPart(NameConverter.decapitalize(reception.getName()) + "(" + event.getMappingInfo().getJavaName().getDecapped() + ")");
			receiveSignal.getBody().addToStatements(ifHasReception);

			OJIfStatement ifEventOfType = new OJIfStatement("event.getSignal() instanceof " + signal.getMappingInfo().getJavaName());
			ifEventOfType.addToThenPart(NameConverter.decapitalize(reception.getName()) + "(event)");
			resolveReception.getBody().addToStatements(ifEventOfType);
		}
		ojClass.addToOperations(receiveSignal);
		ojClass.addToOperations(resolveReception);
	}

}
