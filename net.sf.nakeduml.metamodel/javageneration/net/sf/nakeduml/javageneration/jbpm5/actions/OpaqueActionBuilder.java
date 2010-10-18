package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class OpaqueActionBuilder extends PotentialTaskActionBuilder<INakedOpaqueAction>{

	public OpaqueActionBuilder(IOclEngine oclEngine,INakedOpaqueAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJOperation operation){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node,getOclEngine().getOclLibrary());
		OJAnnotatedField field = new OJAnnotatedField();
		field.setName(node.getMappingInfo().getJavaName().getDecapped().getAsIs());
		field.setType(OJUtil.classifierPathname(node.getMessageStructure()));
		field.setInitExp("new " + field.getType().getLast() + "()");
		operation.getBody().addToLocals(field);
		String taskVarName = field.getName();
		if(map.isCollection()){
			operation.getBody().addToStatements(map.adder() + "(" + taskVarName + ")");
		}else{
			operation.getBody().addToStatements(map.setter() + "(" + taskVarName + ")");
		}
		StringBuilder arguments = populateArguments(operation, node.getArguments());
		operation.getBody().addToStatements(taskVarName + ".execute(" + arguments + ")");
	}
}
