package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;

public class ObjectCreator extends SimpleNodeBuilder<CreateObjectAction>{
	public ObjectCreator(CreateObjectAction action,AbstractObjectNodeExpressor expressor){
		super(action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		ClassifierMap map = ojUtil.buildClassifierMap(node.getClassifier(),(CollectionKind)null);
		PropertyMap resultMap = ojUtil.buildStructuralFeatureMap(node.getResult());
		expressor.buildResultVariable(operation, block, resultMap);
		// TODO create for loop to create enough until the minimum multiplicity has been satisfied
		String call = "new " + map.javaType() + "()";
		if(resultMap.isCollection()){
			int lower = node.getResult().getLower();
			if(lower > 1){
				OJForStatement forLower = new OJForStatement( "i",new OJPathName("int"),  "new int[" + lower + "]");
				forLower.setElemType(new OJPathName("int"));
				forLower.setBody(new OJBlock());
				forLower.getBody().addToStatements(expressor.storeResults(resultMap, call, false));
				block.addToStatements(forLower);
			}else{
				block.addToStatements(expressor.storeResults(resultMap, call, false));
			}
		}else{
			block.addToStatements(expressor.storeResults(resultMap, call, false));
		}
	}
}
