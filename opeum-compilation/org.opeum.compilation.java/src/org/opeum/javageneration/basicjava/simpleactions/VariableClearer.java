package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.actions.INakedClearVariableAction;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class VariableClearer extends SimpleNodeBuilder<INakedClearVariableAction>{
	public VariableClearer(OpeumLibrary oclEngine,INakedClearVariableAction action, AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
		if(map.isOne()){
			block.addToStatements(map.umlName() + "=null");
		}else{
			block.addToStatements(map.umlName() + ".clear()");
		}
	}
}
