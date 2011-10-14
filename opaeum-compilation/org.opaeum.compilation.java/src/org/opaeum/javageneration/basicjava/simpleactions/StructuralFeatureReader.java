package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedReadStructuralFeatureAction;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class StructuralFeatureReader extends SimpleNodeBuilder<INakedReadStructuralFeatureAction> {
	public StructuralFeatureReader(OpaeumLibrary oclEngine, INakedReadStructuralFeatureAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		INakedOutputPin result = node.getResult();
		NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(result.getActivity(), result,true);
		expressor.buildResultVariable(operation, block, resultMap);
		ActionMap actionMap = new ActionMap(node);
		OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
		String call = actionMap.targetName() + "." + new NakedStructuralFeatureMap(node.getFeature()).getter() + "()";
		NakedStructuralFeatureMap featureMap = OJUtil.buildStructuralFeatureMap(node.getFeature());
		call=expressor.storeResults(resultMap, call, featureMap.isMany());
		fs.addToStatements(call);
	}
}