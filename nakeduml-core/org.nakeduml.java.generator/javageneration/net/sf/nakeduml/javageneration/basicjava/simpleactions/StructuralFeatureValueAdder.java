package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.maps.ActionMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedAddStructuralFeatureValueAction;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class StructuralFeatureValueAdder extends SimpleNodeBuilder<INakedAddStructuralFeatureValueAction>{
	public StructuralFeatureValueAdder(NakedUmlLibrary oclEngine,INakedAddStructuralFeatureValueAction action, AbstractObjectNodeExpressor expressor){
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
