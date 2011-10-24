package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedClearVariableAction;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class VariableClearer extends SimpleNodeBuilder<INakedClearVariableAction>{
	public VariableClearer(OpaeumLibrary oclEngine,INakedClearVariableAction action, AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
		if(map.isOne()){
			block.addToStatements(map.fieldname() + "=null");
		}else{
			block.addToStatements(map.fieldname() + ".clear()");
		}
	}
}
