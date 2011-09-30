package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedClassifierMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.actions.INakedCreateObjectAction;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class ObjectCreator extends SimpleNodeBuilder<INakedCreateObjectAction>{
	public ObjectCreator(OpeumLibrary oclEngine,INakedCreateObjectAction action, AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedClassifierMap map = new NakedClassifierMap(node.getClassifier());
		NakedStructuralFeatureMap resultMap= OJUtil.buildStructuralFeatureMap(node.getResult().getActivity(), node.getResult());
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
