package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.AddVariableValueAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;

public class VariableValueAdder extends SimpleNodeBuilder<AddVariableValueAction>{
	public VariableValueAdder(AddVariableValueAction action,AbstractObjectNodeExpressor expressor){
		super(action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper,OJBlock block){
		String valuePinField = readPin(oper, block, node.getValue());
		PropertyMap map = ojUtil.buildStructuralFeatureMap(node.getVariable());
		if(node.isReplaceAll() && map.isMany()){
			block.addToStatements(expressor.clear(map));
		}
		PropertyMap valueMap=ojUtil.buildStructuralFeatureMap(node.getValue());

		block.addToStatements(expressor.pathToVariableContext(node) +  expressor.storeResults(map, valuePinField, valueMap.isMany()));
	}
}
