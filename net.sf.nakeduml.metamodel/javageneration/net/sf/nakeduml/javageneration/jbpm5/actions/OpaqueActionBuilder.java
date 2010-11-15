package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.OpaqueActionCaller;
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
		if(node.isTask()){
			//build task variable
		}else{
			delegate.implementActionOn(operation, operation.getBody());
		}
	}
}
