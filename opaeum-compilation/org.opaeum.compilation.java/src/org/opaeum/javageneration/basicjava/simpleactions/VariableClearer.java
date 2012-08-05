package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.ClearVariableAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;

public class VariableClearer extends SimpleNodeBuilder<ClearVariableAction>{
	public VariableClearer(ClearVariableAction action, AbstractObjectNodeExpressor expressor){
		super(action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(node.getVariable());
		if(map.isOne()){
			block.addToStatements(map.fieldname() + "=null");
		}else{
			block.addToStatements(map.fieldname() + ".clear()");
		}
	}
}
