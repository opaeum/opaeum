package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.ClearVariableAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class VariableClearer extends SimpleNodeBuilder<ClearVariableAction>{
	public VariableClearer(OpaeumLibrary oclEngine,ClearVariableAction action, AbstractObjectNodeExpressor expressor){
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
