package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ObjectCreator extends SimpleActionBuilder<INakedCreateObjectAction>{
	public ObjectCreator(IOclEngine oclEngine,INakedCreateObjectAction action, ObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedClassifierMap map = new NakedClassifierMap(node.getClassifier());
		NakedStructuralFeatureMap resultMap = expressor.maybeBuildResultVariable(operation, block, node.getResult());
		// TODO create for loop to create enough until the minimum multiplicity has been satisfied
		String call = "new " + map.javaType() + "()";
		if(resultMap.isCollection()){
			int lower = node.getResult().getNakedMultiplicity().getLower();
			if(lower > 1){
				OJForStatement forLower = new OJForStatement("", "", "i", "new int[" + lower + "]");
				forLower.setElemType(new OJPathName("int"));
				forLower.setBody(new OJBlock());
				forLower.getBody().addToStatements(expressor.resultGetter(resultMap)  + ".add(" + call + ")");
				block.addToStatements(forLower);
			}else{
				block.addToStatements(expressor.resultGetter(resultMap)+ ".add(" + call + ")");
			}
		}else{
			block.addToStatements(expressor.resultSetter(resultMap,call));
		}
	}
}
