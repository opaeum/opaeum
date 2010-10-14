package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedClearVariableAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class VariableClearer extends SimpleActionBuilder<INakedClearVariableAction>{
	public VariableClearer(IOclEngine oclEngine,INakedClearVariableAction action){
		super(oclEngine, action);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
		if(map.isOne()){
			block.addToStatements(map.umlName() + "=null");
		}else{
			block.addToStatements(map.umlName() + ".clear()");
		}
	}
}
