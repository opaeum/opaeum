package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class StructuralFeatureValueAdder extends SimpleNodeBuilder<AddStructuralFeatureValueAction>{
	public StructuralFeatureValueAdder(OpaeumLibrary oclEngine,AddStructuralFeatureValueAction action, AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper,OJBlock block){

		String valuePinField = readPin(oper, block, node.getValue());
		ActionMap actionMap = new ActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(oper, block, actionMap);
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap((Property) node.getStructuralFeature());
		String modifier = null;
		if(map.isOne()){
			modifier = map.setter();
		}else{
			if(node.isReplaceAll()){
				block.addToStatements(map.clearer() + "()");
			}	
			if(node.getValue().isMultivalued()==false){
				modifier = map.adder();
			}else{
				modifier = map.allAdder();
			}
		}
		forEach.addToStatements(actionMap.targetName() + "." + modifier + "(" + valuePinField + ")");
	}
}
