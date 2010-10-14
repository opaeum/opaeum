package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.bpm.actions.ActionMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedAddStructuralFeatureValueAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class StructuralFeatureValueAdder extends SimpleActionBuilder<INakedAddStructuralFeatureValueAction>{
	public StructuralFeatureValueAdder(IOclEngine oclEngine,INakedAddStructuralFeatureValueAction action){
		super(oclEngine, action);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper,OJBlock block){
		String valuePinField = buildPinExpression(oper, block, node.getValue());
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