package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.bpm.actions.ActionMap;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedRemoveStructuralFeatureValueAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class StructuralFeatureValueRemover extends SimpleActionBuilder<INakedRemoveStructuralFeatureValueAction>{
	public StructuralFeatureValueRemover(IOclEngine oclEngine,INakedRemoveStructuralFeatureValueAction action){
		super(oclEngine, action);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		String valuePinField = buildPinExpression(operation, block, node.getValue());
		ActionMap actionMap = new ActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(operation, block, actionMap);
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(node.getFeature());
		if(map.isOne()){
			forEach.addToStatements(actionMap.targetName() + "." + map.setter() + "(" + valuePinField + ")");
		}else if(node.getValue().getNakedMultiplicity().isSingleObject()){
			forEach.addToStatements(actionMap.targetName() + "." + map.remover() + "(" + valuePinField + ")");
		}else{
			forEach.addToStatements(actionMap.targetName() + "." + map.removeAll() + "(" + valuePinField + ")");
		}
	}
}