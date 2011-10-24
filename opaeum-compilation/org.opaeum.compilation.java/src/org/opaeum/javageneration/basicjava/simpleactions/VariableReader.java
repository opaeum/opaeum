package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedReadVariableAction;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class VariableReader extends SimpleNodeBuilder<INakedReadVariableAction> {
	public VariableReader(OpaeumLibrary oclEngine, INakedReadVariableAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		INakedOutputPin result = node.getResult();
		NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(result.getActivity(), result,true);
		expressor.buildResultVariable(operation, block, resultMap);
		NakedStructuralFeatureMap variableMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getVariable());
		String call=expressor.storeResults(resultMap, variableMap.fieldname(), variableMap.isMany());
		block.addToStatements(call);
	}
}