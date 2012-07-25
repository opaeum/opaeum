package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.RemoveVariableValueAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class VariableValueRemover extends SimpleNodeBuilder<RemoveVariableValueAction>{
	public VariableValueRemover(OpaeumLibrary oclEngine,RemoveVariableValueAction action, AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		String valuePinField = readPin(operation, block, node.getValue());
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
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
