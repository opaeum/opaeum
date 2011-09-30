package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.ActionMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.actions.INakedAddStructuralFeatureValueAction;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class StructuralFeatureValueAdder extends SimpleNodeBuilder<INakedAddStructuralFeatureValueAction>{
	public StructuralFeatureValueAdder(OpeumLibrary oclEngine,INakedAddStructuralFeatureValueAction action, AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper,OJBlock block){
		String valuePinField = readPin(oper, block, node.getValue());
		ActionMap actionMap = new ActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(oper, block, actionMap);
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getFeature());
		String modifier = null;
		if(map.isOne()){
			modifier = map.setter();
		}else{
			if(node.isReplaceAll()){
				block.addToStatements(map.clearer() + "()");
			}	
			if(node.getValue().getNakedMultiplicity().isSingleObject()){
				modifier = map.adder();
			}else{
				modifier = map.allAdder();
			}
		}
		forEach.addToStatements(actionMap.targetName() + "." + modifier + "(" + valuePinField + ")");
	}
}
