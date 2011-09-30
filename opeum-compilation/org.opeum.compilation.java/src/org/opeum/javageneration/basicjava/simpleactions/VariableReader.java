package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.actions.INakedReadVariableAction;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class VariableReader extends SimpleNodeBuilder<INakedReadVariableAction> {
	public VariableReader(OpeumLibrary oclEngine, INakedReadVariableAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		INakedOutputPin result = node.getResult();
		NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(result.getActivity(), result);
		expressor.buildResultVariable(operation, block, resultMap);
		NakedStructuralFeatureMap variableMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getVariable());
		String call=expressor.storeResults(resultMap, variableMap.umlName(), variableMap.isMany());
		block.addToStatements(call);
	}
}