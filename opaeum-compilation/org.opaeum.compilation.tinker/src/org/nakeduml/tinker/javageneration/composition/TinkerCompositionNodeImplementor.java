package org.nakeduml.tinker.javageneration.composition;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.composition.CompositionNodeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.AttributeExpressionGenerator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.OperationMessageStructureImpl;

@StepDependency(phase = JavaTransformationPhase.class, requires = { CompositionEmulator.class, OperationAnnotator.class }, after = { OperationAnnotator.class,
		AttributeExpressionGenerator.class }, replaces = CompositionNodeImplementor.class)
public class TinkerCompositionNodeImplementor extends CompositionNodeImplementor {

	@Override
	protected void visitClass(ICompositionParticipant c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof OperationMessageStructureImpl)) {
			OJPathName path = OJUtil.classifierPathname(c);
			OJClassifier ojClassifier = this.javaModel.findClass(path);
			if (ojClassifier instanceof OJAnnotatedClass) {
				OJAnnotatedClass ojClass = (OJAnnotatedClass) ojClassifier;
				ojClass.addToImplementedInterfaces(COMPOSITION_NODE);
				addGetOwningObject(c, ojClass);
				addRemoveFromOwner(ojClass);
				addMarkDeleted(ojClass, c);
				addAddToOwningObject(ojClass, c);
				addStartClassifierBehavior(ojClass, c);
				addInit(c, ojClass);
				addConstructorForTests(c, ojClass);
			}
		}
	}

	private void addStartClassifierBehavior(OJAnnotatedClass ojClass, ICompositionParticipant c) {
		if (c instanceof INakedBehavioredClassifier && ((INakedBehavioredClassifier) c).getClassifierBehavior() != null) {
			OJAnnotatedOperation startClassifierBehavior = new OJAnnotatedOperation("startClassifierBehavior");
			startClassifierBehavior.setVisibility(OJVisibilityKind.PRIVATE);
			startClassifierBehavior.getBody().addToStatements("getClassifierBehavior().execute()");
			ojClass.addToOperations(startClassifierBehavior);
		}
	}

	@Override
	public void addMarkDeleted(OJAnnotatedClass ojClass, INakedClassifier sc) {
		OJAnnotatedOperation markDeleted = new OJAnnotatedOperation("markDeleted");
		ojClass.addToOperations(markDeleted);
		markChildrenForDeletion(sc, ojClass, markDeleted);
		invokeOperationRecursively(sc, markDeleted, "markDeleted()");
		deleteEmbeddedManies(sc, markDeleted);
		markDeleted.getBody().addToStatements(TinkerGenerationUtil.graphDbAccess + ".removeVertex(this.vertex)");
	}

	// This is same as super only one to one does not check for inverse
	protected void markChildrenForDeletion(INakedClassifier sc, OJClass ojClass, OJAnnotatedOperation markDeleted) {
		for (INakedProperty np : sc.getEffectiveAttributes()) {
			if (np.getOtherEnd() != null && np.getOtherEnd().isNavigable() && !np.isDerived() && !np.getOtherEnd().isDerived()
					&& (isPersistent(np.getNakedBaseType()) || np.getNakedBaseType() instanceof INakedInterface)) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(np.getOtherEnd());
				if (map.isManyToMany()) {
					markDeleted.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
				} else if (map.isManyToOne()) {
					OJIfStatement ifNotNull;
					if (isMap(np.getOtherEnd())) {
						ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.internalRemover() + "("
								+ OJUtil.addQualifierArguments(np.getOtherEnd().getQualifiers(), "this") + "this)");
					} else {
						ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.internalRemover() + "(this)");
					}
					markDeleted.getBody().addToStatements(ifNotNull);
				} else if (map.isOneToOne()) {
					// TODO this may have unwanted results such as removing the
					// owner from "this" too
					OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + otherMap.internalRemover() + "(this)");
					markDeleted.getBody().addToStatements(ifNotNull);
				}
			}
		}
	}

	private void deleteEmbeddedManies(INakedClassifier sc, OJAnnotatedOperation markDeleted) {
		for (INakedProperty np : sc.getEffectiveAttributes()) {
			if (np.getOtherEnd() == null && !np.isDerived() && !np.getMultiplicity().isSingleObject()) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
				markDeleted.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
			}
		}

	}

	protected void addInit(ICompositionParticipant c, OJClass ojClass) {
		OJOperation initVariables = new OJAnnotatedOperation("initVariables");
		initVariables.setBody(ojClass.getDefaultConstructor().getBody());
		if (c.hasSupertype()) {
			OJSimpleStatement simpleStatement = new OJSimpleStatement("super.initVariables()");
			if (initVariables.getBody().getStatements().isEmpty()) {
				initVariables.getBody().addToStatements(simpleStatement);
			} else {
				initVariables.getBody().getStatements().set(0, simpleStatement);
			}
		}
		ojClass.addToOperations(initVariables);
		ojClass.getDefaultConstructor().setBody(new OJBlock());

		OJOperation init = new OJAnnotatedOperation("init");
		init.addParam("owner", COMPOSITION_NODE);
		init.getBody().addToStatements("this.hasInitBeenCalled = true");
		init.getBody().addToStatements("initVariables()");
		int start = 0;
		if (c.hasComposite() && !c.getEndToComposite().isDerived()) {
			StructuralFeatureMap compositeFeatureMap = new NakedStructuralFeatureMap(c.getEndToComposite());
			ojClass.addToImports(compositeFeatureMap.javaBaseTypePath());
			init.getBody().getStatements().add(start, new OJSimpleStatement("this." + compositeFeatureMap.internalAdder() + "((" + compositeFeatureMap.javaBaseType() + ")owner)"));
		}
		ojClass.addToOperations(init);
	}

}
