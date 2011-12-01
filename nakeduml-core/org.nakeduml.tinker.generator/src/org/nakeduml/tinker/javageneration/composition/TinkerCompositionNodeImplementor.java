package org.nakeduml.tinker.javageneration.composition;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.OperationAnnotator;
import net.sf.nakeduml.javageneration.composition.CompositionNodeImplementor;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.oclexpressions.AttributeExpressionGenerator;
import net.sf.nakeduml.linkage.CompositionEmulator;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;

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
