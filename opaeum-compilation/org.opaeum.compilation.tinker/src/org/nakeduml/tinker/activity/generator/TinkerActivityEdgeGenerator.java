package org.nakeduml.tinker.activity.generator;

import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.activity.maps.ActivityNodeBridge;
import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = TinkerActivityPhase.class, requires = {TinkerActivityNodeGenerator.class}, after = {TinkerActivityNodeGenerator.class})
public class TinkerActivityEdgeGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedActivityEdge.class })
	public void visitActivityEdge(INakedActivityEdge edge) {
		createControlFlowEdgeClass(edge);
	}

	private void createControlFlowEdgeClass(INakedActivityEdge edge) {
		OJAnnotatedClass controlFlowEdge = new OJAnnotatedClass(TinkerBehaviorUtil.activityEdgePathName(edge).getLast());
		OJPathName path = OJUtil.packagePathname(edge.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		controlFlowEdge.setMyPackage(pack);
		addContextObjectField(controlFlowEdge, edge.getActivity().getContext());
		OJConstructor edgeConstructor = new OJConstructor();
		edgeConstructor.addParam("edge", TinkerGenerationUtil.edgePathName);
		edgeConstructor.getBody().addToStatements("super(edge)");
		edgeConstructor.addParam("contextObject", OJUtil.classifierPathname(edge.getActivity().getContext()));
		edgeConstructor.getBody().addToStatements("this.contextObject = contextObject");

		controlFlowEdge.addToConstructors(edgeConstructor);

		if (edge instanceof INakedObjectFlow) {
			INakedObjectFlow objectFlow = (INakedObjectFlow) edge;
			INakedClassifier objectFlowClassifier = objectFlow.getOriginatingObjectNodeClassifier();

			if (objectFlowClassifier == null) {
				addEvaluateGuardCondition(controlFlowEdge, (INakedObjectFlow) edge, new OJPathName("java.lang.Object"));
				if (objectFlow.getTinkerOriginatingMultiplicity().isOne()) {
					controlFlowEdge.setSuperclass(TinkerBehaviorUtil.tinkerOneObjectFlowUnknownPathName.getCopy());
				} else {
					controlFlowEdge.setSuperclass(TinkerBehaviorUtil.tinkerManyObjectFlowUnknownPathName.getCopy());
				}
			} else {
				OJPathName superClass;
				if (objectFlow.getTinkerOriginatingMultiplicity().isOne()) {
					superClass = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
				} else {
					superClass = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
				}
				OJPathName objectTokenPathName = OJUtil.classifierPathname(objectFlowClassifier);
				superClass.addToGenerics(objectTokenPathName);
				controlFlowEdge.setSuperclass(superClass);
				addEvaluateGuardCondition(controlFlowEdge, (INakedObjectFlow) edge, objectTokenPathName);
				controlFlowEdge.addToImports(objectTokenPathName);
			}
		} else {
			controlFlowEdge.setSuperclass(TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy());
			addEvaluateControlFlowGuardCondition(controlFlowEdge, edge);
		}

		super.createTextPath(controlFlowEdge, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addGetWeight(controlFlowEdge, edge);
		addTarget(controlFlowEdge, edge);
		addSource(controlFlowEdge, edge);
		addName(controlFlowEdge, edge);

		OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject");
		getContextObject.setReturnType(OJUtil.classifierPathname(edge.getActivity().getContext()));
		getContextObject.getBody().addToStatements("return this.contextObject");
		controlFlowEdge.addToOperations(getContextObject);

	}
	
	private void addEvaluateGuardCondition(OJAnnotatedClass controlFlowEdge, INakedObjectFlow edge, OJPathName tokenValuePathName) {
		OJAnnotatedOperation evaluateGuardConditions = new OJAnnotatedOperation("evaluateGuardConditions");
		TinkerGenerationUtil.addOverrideAnnotation(evaluateGuardConditions);
		evaluateGuardConditions.setReturnType(new OJPathName("boolean"));
		controlFlowEdge.addToOperations(evaluateGuardConditions);
		String guardEvaluation;
		if (edge.getTinkerOriginatingMultiplicity().isOne()) {
			evaluateGuardConditions.addParam(NameConverter.decapitalize(edge.getName()), tokenValuePathName);
		} else {
			OJPathName collection = new OJPathName("java.util.Collection");
			controlFlowEdge.addToImports(collection);
			collection.addToGenerics(tokenValuePathName);
			evaluateGuardConditions.addParam(NameConverter.decapitalize(edge.getName()), collection);
		}
		guardEvaluation = ValueSpecificationUtil.expressValue(evaluateGuardConditions, edge.getGuard(), edge.getActivity(), new StdlibPrimitiveType("Boolean"));
		evaluateGuardConditions.getBody().addToStatements("return " + guardEvaluation);
		evaluateGuardConditions.setVisibility(OJVisibilityKind.PROTECTED);
		evaluateGuardConditions.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
	}

	private void addEvaluateControlFlowGuardCondition(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation evaluateGuardConditions = new OJAnnotatedOperation("evaluateGuardConditions");
		TinkerGenerationUtil.addOverrideAnnotation(evaluateGuardConditions);
		evaluateGuardConditions.setReturnType(new OJPathName("boolean"));
		controlFlowEdge.addToOperations(evaluateGuardConditions);
		String guardEvaluation;
		guardEvaluation = ValueSpecificationUtil.expressValue(controlFlowEdge, edge.getGuard(), new StdlibPrimitiveType("Boolean"), false);
		evaluateGuardConditions.getBody().addToStatements("return " + guardEvaluation);
		evaluateGuardConditions.setVisibility(OJVisibilityKind.PROTECTED);
		evaluateGuardConditions.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
	}

	private void addGetWeight(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation getWeight = new OJAnnotatedOperation("getWeigth");
		getWeight.setReturnType(new OJPathName("int"));
		getWeight.getBody().addToStatements("return " + edge.getWeight().intValue());
		getWeight.setVisibility(OJVisibilityKind.PROTECTED);
		getWeight.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(getWeight);
	}

	private void addTarget(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		ConcreteEmulatedClassifier targetClassifier = new ConcreteEmulatedClassifier(edge.getTarget().getNameSpace(), edge.getTarget());
		NakedStructuralFeatureMap targetMap = new NakedStructuralFeatureMap(new ActivityNodeBridge(targetClassifier, edge.getTarget(), true));

		OJAnnotatedField targetField = new OJAnnotatedField(targetMap.fieldname(), TinkerBehaviorUtil.activityNodePathName(edge.getTarget()));
		controlFlowEdge.addToFields(targetField);

		OJAnnotatedOperation getTargetAction = new OJAnnotatedOperation(TinkerBehaviorUtil.actionTargetGetter(edge));
		OJPathName actionPathName = TinkerBehaviorUtil.activityNodePathName(edge.getTarget());
		getTargetAction.setReturnType(actionPathName);

		OJIfStatement ifTargetNull = new OJIfStatement("this." + targetMap.fieldname() + " == null");
		ifTargetNull.addToThenPart("this." + targetMap.fieldname() + " = new " + actionPathName.getLast() + "(this.edge.getInVertex())");
		getTargetAction.getBody().addToStatements(ifTargetNull);
		getTargetAction.getBody().addToStatements("return this." + targetMap.fieldname());

		controlFlowEdge.addToOperations(getTargetAction);

		OJAnnotatedOperation getTarget = new OJAnnotatedOperation("getTarget");
		OJPathName tokenPathName;
		if (edge instanceof INakedObjectFlow) {
			tokenPathName = TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy();
			INakedObjectNode originatingObjectNode = ((INakedObjectFlow) edge).getOriginatingObjectNode();
			if (originatingObjectNode != null) {
				tokenPathName.addToGenerics(OJUtil.classifierPathname(originatingObjectNode.getNakedBaseType()));
			} else {
				tokenPathName.addToGenerics(new OJPathName("java.lang.Object"));
			}
			controlFlowEdge.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
		} else {
			tokenPathName = TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy();
			controlFlowEdge.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		}
		OJPathName returnType = TinkerBehaviorUtil.activityNodePathName(edge.getTarget());
		getTarget.setReturnType(returnType);
		getTarget.getBody().addToStatements("return " + TinkerBehaviorUtil.actionTargetGetter(edge) + "()");
		getTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(getTarget);
	}

	private void addSource(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {

		ConcreteEmulatedClassifier sourceClassifier = new ConcreteEmulatedClassifier(edge.getSource().getNameSpace(), edge.getSource());
		NakedStructuralFeatureMap sourceMap = new NakedStructuralFeatureMap(new ActivityNodeBridge(sourceClassifier, edge.getSource(), false));

		OJAnnotatedField sourceField = new OJAnnotatedField(sourceMap.fieldname(), TinkerBehaviorUtil.activityNodePathName(edge.getSource()));
		controlFlowEdge.addToFields(sourceField);

		OJAnnotatedOperation getSourceAction = new OJAnnotatedOperation(TinkerBehaviorUtil.actionSourceGetter(edge));
		OJPathName actionPathName = TinkerBehaviorUtil.activityNodePathName(edge.getSource());
		getSourceAction.setReturnType(actionPathName);

		OJIfStatement ifSourceNull = new OJIfStatement("this." + sourceMap.fieldname() + " == null");
		ifSourceNull.addToThenPart("this." + sourceMap.fieldname() + " = new " + actionPathName.getLast() + "(this.edge.getOutVertex())");
		getSourceAction.getBody().addToStatements(ifSourceNull);
		getSourceAction.getBody().addToStatements("return this." + sourceMap.fieldname());

		controlFlowEdge.addToOperations(getSourceAction);

		OJAnnotatedOperation getSource = new OJAnnotatedOperation("getSource");

		OJPathName tokenPathName;
		if (edge instanceof INakedObjectFlow) {
			tokenPathName = TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy();
			INakedObjectNode originatingObjectNode = ((INakedObjectFlow) edge).getOriginatingObjectNode();
			if (originatingObjectNode != null) {
				tokenPathName.addToGenerics(OJUtil.classifierPathname(originatingObjectNode.getNakedBaseType()));
			} else {
				tokenPathName.addToGenerics(new OJPathName("java.lang.Object"));
			}
			controlFlowEdge.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
		} else {
			tokenPathName = TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy();
			controlFlowEdge.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		}
		// OJPathName returnType =
		// TinkerBehaviorUtil.tinkerActivityNodePathName.getCopy();
		// returnType.addToGenerics(tokenPathName);
		OJPathName returnType = TinkerBehaviorUtil.activityNodePathName(edge.getSource());
		getSource.setReturnType(returnType);

		getSource.getBody().addToStatements("return " + TinkerBehaviorUtil.actionSourceGetter(edge) + "()");
		getSource.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(getSource);
	}

	private void addName(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation getName = new OJAnnotatedOperation("getName");
		getName.setReturnType(new OJPathName("String"));
		getName.getBody().addToStatements("return \"" + edge.getName() + "\"");
		controlFlowEdge.addToOperations(getName);
	}

}
