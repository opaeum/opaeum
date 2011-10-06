package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.actions.INakedRemoveVariableValueAction;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class VariableValueRemover extends SimpleNodeBuilder<INakedRemoveVariableValueAction>{
	public VariableValueRemover(OpeumLibrary oclEngine,INakedRemoveVariableValueAction action, AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		String valuePinField = readPin(operation, block, node.getValue());
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
		if(map.isOne()){
			// TODO what if node.getValue().isMany()?
			block.addToStatements(node.getVariable().getName() + "=" + valuePinField);
		}else if(node.getValue().getNakedMultiplicity().isOne()){
			block.addToStatements(node.getVariable().getName() + ".remove(" + valuePinField + ")");
		}else{
			block.addToStatements(node.getVariable().getName() + ".removeAll(" + valuePinField + ")");
		}
	}
}
