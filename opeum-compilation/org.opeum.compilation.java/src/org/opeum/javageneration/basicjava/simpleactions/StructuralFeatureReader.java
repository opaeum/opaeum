package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.ActionMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.actions.INakedReadStructuralFeatureAction;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class StructuralFeatureReader extends SimpleNodeBuilder<INakedReadStructuralFeatureAction> {
	public StructuralFeatureReader(OpeumLibrary oclEngine, INakedReadStructuralFeatureAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		INakedOutputPin result = node.getResult();
		NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(result.getActivity(), result);
		expressor.buildResultVariable(operation, block, resultMap);
		ActionMap actionMap = new ActionMap(node);
		OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
		String call = actionMap.targetName() + "." + new NakedStructuralFeatureMap(node.getFeature()).getter() + "()";
		NakedStructuralFeatureMap featureMap = OJUtil.buildStructuralFeatureMap(node.getFeature());
		call=expressor.storeResults(resultMap, call, featureMap.isMany());
		fs.addToStatements(call);
	}
}