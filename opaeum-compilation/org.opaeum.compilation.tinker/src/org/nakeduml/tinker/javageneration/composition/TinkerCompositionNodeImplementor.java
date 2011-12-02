package org.nakeduml.tinker.javageneration.composition;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.composition.CompositionNodeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.AttributeExpressionGenerator;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedProperty;

@StepDependency(phase = JavaTransformationPhase.class, requires = { CompositionEmulator.class, OperationAnnotator.class }, after = { OperationAnnotator.class,
		AttributeExpressionGenerator.class }, replaces = CompositionNodeImplementor.class)
public class TinkerCompositionNodeImplementor extends CompositionNodeImplementor {

	@Override
	public void addMarkDeleted(OJAnnotatedClass ojClass, INakedClassifier sc) {
		OJAnnotatedOperation markDeleted = new OJAnnotatedOperation("markDeleted");
		ojClass.addToOperations(markDeleted);
		if (sc.hasSupertype()) {
			markDeleted.getBody().addToStatements("super.markDeleted()");
		} else if (AbstractJavaProducingVisitor.isPersistent(sc)) {
		}
		markChildrenForDeletion(sc, ojClass, markDeleted);
		invokeOperationRecursively(sc, markDeleted, "markDeleted()");
		deleteEmbeddedManies(sc, markDeleted);
		markDeleted.getBody().addToStatements(TinkerGenerationUtil.graphDbAccess + ".removeVertex(this.vertex)");
	}

	private void deleteEmbeddedManies(INakedClassifier sc, OJAnnotatedOperation markDeleted) {
		for(INakedProperty np:sc.getEffectiveAttributes()){
			if(np.getOtherEnd() == null && !np.isDerived() && !np.getMultiplicity().isSingleObject()){
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
