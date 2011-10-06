package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.metamodel.actions.INakedClearStructuralFeatureAction;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class StructuralFeatureClearer extends SimpleNodeBuilder<INakedClearStructuralFeatureAction> {
	public StructuralFeatureClearer(OpaeumLibrary oclEngine, INakedClearStructuralFeatureAction action, AbstractObjectNodeExpressor expressor) {
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
