package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.metamodel.actions.INakedRemoveStructuralFeatureValueAction;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class StructuralFeatureValueRemover extends SimpleNodeBuilder<INakedRemoveStructuralFeatureValueAction> {
	public StructuralFeatureValueRemover(OpaeumLibrary oclEngine, INakedRemoveStructuralFeatureValueAction action,
			AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		String valuePinField = readPin(operation, block, node.getValue());
		ActionMap actionMap = new ActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(operation, block, actionMap);
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(node.getFeature());
		if (map.isOne()) {
			forEach.addToStatements(actionMap.targetName() + "." + map.setter() + "(" + valuePinField + ")");
		} else if (node.getValue().getNakedMultiplicity().isSingleObject()) {
			forEach.addToStatements(actionMap.targetName() + "." + map.remover() + "(" + valuePinField + ")");
		} else {
			forEach.addToStatements(actionMap.targetName() + "." + map.removeAll() + "(" + valuePinField + ")");
		}
	}
}