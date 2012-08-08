package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.RemoveVariableValueAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;

public class VariableValueRemover extends SimpleNodeBuilder<RemoveVariableValueAction>{
	public VariableValueRemover(RemoveVariableValueAction action, AbstractObjectNodeExpressor expressor){
		super(action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		String valuePinField = readPin(operation, block, node.getValue());
		StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(node.getVariable());
		if(map.isOne()){
			// TODO what if node.getValue().isMany()?
			block.addToStatements(node.getVariable().getName() + "=" + valuePinField);
		}else if(node.getValue().getUpper()==1){
			block.addToStatements(node.getVariable().getName() + ".remove(" + valuePinField + ")");
		}else{
			block.addToStatements(node.getVariable().getName() + ".removeAll(" + valuePinField + ")");
		}
	}
}
