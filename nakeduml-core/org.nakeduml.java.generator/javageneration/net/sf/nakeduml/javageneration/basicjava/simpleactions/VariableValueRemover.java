package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedRemoveVariableValueAction;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class VariableValueRemover extends SimpleNodeBuilder<INakedRemoveVariableValueAction>{
	public VariableValueRemover(IOclEngine oclEngine,INakedRemoveVariableValueAction action, AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		String valuePinField = buildPinExpression(operation, block, node.getValue());
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
