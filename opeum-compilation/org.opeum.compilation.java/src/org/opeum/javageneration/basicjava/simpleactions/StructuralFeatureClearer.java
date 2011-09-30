package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.ActionMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.metamodel.actions.INakedClearStructuralFeatureAction;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class StructuralFeatureClearer extends SimpleNodeBuilder<INakedClearStructuralFeatureAction> {
	public StructuralFeatureClearer(OpeumLibrary oclEngine, INakedClearStructuralFeatureAction action, AbstractObjectNodeExpressor expressor) {
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
