package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.maps.NakedClassifierMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ObjectCreator extends SimpleNodeBuilder<INakedCreateObjectAction>{
	public ObjectCreator(NakedUmlLibrary oclEngine,INakedCreateObjectAction action, AbstractObjectNodeExpressor expressor){
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
