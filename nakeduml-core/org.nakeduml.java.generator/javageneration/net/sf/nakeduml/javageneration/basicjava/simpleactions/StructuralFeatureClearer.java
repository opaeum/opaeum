package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.maps.ActionMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.actions.INakedClearStructuralFeatureAction;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class StructuralFeatureClearer extends SimpleNodeBuilder<INakedClearStructuralFeatureAction> {
	public StructuralFeatureClearer(NakedUmlLibrary oclEngine, INakedClearStructuralFeatureAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		ActionMap actionMap = new ActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(operation, block, actionMap);
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(node.getFeature());
		if (map.isOne()) {
			forEach.addToStatements(actionMap.targetName() + "." + map.setter() + "(null)");
		} else {
			forEach.addToStatements(actionMap.targetName() + "." + map.clearer() + "()");
		}
	}
}
