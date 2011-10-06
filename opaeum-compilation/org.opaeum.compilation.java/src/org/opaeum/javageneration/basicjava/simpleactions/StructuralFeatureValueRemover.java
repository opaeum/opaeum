package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.ActionMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.metamodel.actions.INakedRemoveStructuralFeatureValueAction;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class StructuralFeatureValueRemover extends SimpleNodeBuilder<INakedRemoveStructuralFeatureValueAction> {
	public StructuralFeatureValueRemover(OpeumLibrary oclEngine, INakedRemoveStructuralFeatureValueAction action,
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