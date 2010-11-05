package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedAddVariableValueAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class VariableValueAdder extends SimpleActionBuilder<INakedAddVariableValueAction>{
	public VariableValueAdder(IOclEngine oclEngine,INakedAddVariableValueAction action, ObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper,OJBlock block){
		String valuePinField = buildPinExpression(oper, block, node.getValue());
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
		String variable = map.umlName();
		if(map.isOne()){
			block.addToStatements(variable + "=" + valuePinField);
		}else{
			if(node.isReplaceAll()){
				block.addToStatements(variable + ".clear()");
			}
			if(node.getValue().getNakedMultiplicity().isSingleObject()){
				block.addToStatements(variable + ".add(" + valuePinField + ")");
			}else{
				block.addToStatements(variable + ".addAll(" + valuePinField + ")");
			}
		}
	}
}
