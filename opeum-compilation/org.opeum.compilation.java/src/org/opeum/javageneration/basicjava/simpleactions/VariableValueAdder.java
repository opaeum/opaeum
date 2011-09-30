package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.actions.INakedAddVariableValueAction;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class VariableValueAdder extends SimpleNodeBuilder<INakedAddVariableValueAction> {
	public VariableValueAdder(OpeumLibrary oclEngine, INakedAddVariableValueAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation oper, OJBlock block) {
		String valuePinField = readPin(oper, block, node.getValue());
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
		block.addToStatements(expressor.storeResults(map, valuePinField, map.isMany()));
	}
}
