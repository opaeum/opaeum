package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.OpaqueActionCaller;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class OpaqueActionBuilder extends PotentialTaskActionBuilder<INakedOpaqueAction>{
	OpaqueActionCaller delegate;
	public OpaqueActionBuilder(IOclEngine oclEngine,INakedOpaqueAction node){
		super(oclEngine, node);
		delegate=new OpaqueActionCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		if(node.isTask() && node.isSynchronous()){
			OJBlock fs = buildLoopThroughTarget(operation, operation.getBody(), new ActionMap(node));
			
			NakedClassifierMap map = new NakedClassifierMap(node.getMessageStructure());
			OJAnnotatedField taskVar = new OJAnnotatedField("task", map.javaTypePath());
			taskVar.setInitExp("new " + map.javaType() + "(this, context)");
			fs.addToLocals(taskVar);
			NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary());
			fs.addToStatements(expressor.storeResults(resultMap, "task", false));
			fs.addToStatements("task.execute()");
			//build task variable
		}else{
			delegate.implementActionOn(operation, operation.getBody());
		}
	}
}
