package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAddVariableValueAction;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class VariableValueAdder extends SimpleNodeBuilder<INakedAddVariableValueAction> {
	public VariableValueAdder(OpaeumLibrary oclEngine, INakedAddVariableValueAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation oper, OJBlock block) {
		String valuePinField = readPin(oper, block, node.getValue());
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
		block.addToStatements(expressor.storeResults(map, valuePinField, map.isMany()));
	}
}
