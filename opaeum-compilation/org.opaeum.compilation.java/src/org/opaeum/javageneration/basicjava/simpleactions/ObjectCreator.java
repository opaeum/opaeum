package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ObjectCreator extends SimpleNodeBuilder<CreateObjectAction>{
	public ObjectCreator(OpaeumLibrary oclEngine,CreateObjectAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedClassifierMap map = OJUtil.buildClassifierMap(node.getClassifier(),(CollectionKind)null);
		NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(getContainingActivity(), node.getResult(), true);
		expressor.buildResultVariable(operation, block, resultMap);
		// TODO create for loop to create enough until the minimum multiplicity has been satisfied
		String call = "new " + map.javaType() + "()";
		if(resultMap.isCollection()){
			int lower = node.getResult().getLower();
			if(lower > 1){
				OJForStatement forLower = new OJForStatement("", "", "i", "new int[" + lower + "]");
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
