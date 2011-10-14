package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedCreateObjectAction;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ObjectCreator extends SimpleNodeBuilder<INakedCreateObjectAction>{
	public ObjectCreator(OpaeumLibrary oclEngine,INakedCreateObjectAction action, AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedClassifierMap map = new NakedClassifierMap(node.getClassifier());
		NakedStructuralFeatureMap resultMap= OJUtil.buildStructuralFeatureMap(node.getResult().getActivity(), node.getResult(),true);
		expressor.buildResultVariable(operation, block, resultMap);
		// TODO create for loop to create enough until the minimum multiplicity has been satisfied
		String call = "new " + map.javaType() + "()";
		if(resultMap.isCollection()){
			int lower = node.getResult().getNakedMultiplicity().getLower();
			if(lower > 1){
				OJForStatement forLower = new OJForStatement("", "", "i", "new int[" + lower + "]");
				forLower.setElemType(new OJPathName("int"));
				forLower.setBody(new OJBlock());
				forLower.getBody().addToStatements(expressor.getterForStructuredResults(resultMap)  + ".add(" + call + ")");
				block.addToStatements(forLower);
			}else{
				block.addToStatements(expressor.getterForStructuredResults(resultMap)+ ".add(" + call + ")");
			}
		}else{
			block.addToStatements(expressor.setterForSingleResult(resultMap,call));
		}
	}
}
