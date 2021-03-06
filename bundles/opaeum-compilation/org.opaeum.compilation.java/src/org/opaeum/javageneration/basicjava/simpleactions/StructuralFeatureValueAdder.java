package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;

public class StructuralFeatureValueAdder extends SimpleNodeBuilder<AddStructuralFeatureValueAction>{
	public StructuralFeatureValueAdder(AddStructuralFeatureValueAction action, AbstractObjectNodeExpressor expressor){
		super(action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper,OJBlock block){

		String valuePinField = readPin(oper, block, node.getValue());
		ActionMap actionMap = ojUtil.buildActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(oper, block, actionMap);
		PropertyMap map = ojUtil.buildStructuralFeatureMap((Property) node.getStructuralFeature());
		String modifier = null;
		if(map.isOne()){
			modifier = map.setter();
		}else{
			if(node.isReplaceAll()){
				block.addToStatements(map.clearer() + "()");
			}	
			PropertyMap valueMap=ojUtil.buildStructuralFeatureMap(node.getValue());
			if(valueMap.isOne()){
				modifier = map.adder();
			}else{
				modifier = map.allAdder();
			}
		}
		forEach.addToStatements(actionMap.targetName() + "." + modifier + "(" + valuePinField + ")");
	}
}
