package net.sf.nakeduml.javageneration.jbpm5.actions;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.OperationCaller;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import nl.klasse.octopus.oclengine.IOclEngine;

public class CallOperationBuilder extends PotentialTaskActionBuilder<INakedCallOperationAction>{
	OperationCaller delegate;
	@Override
	public boolean isTask(){
		return node instanceof INakedResponsibility;
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation oper){
		delegate.implementActionOn(oper, oper.getBody());
		
	}
	public CallOperationBuilder(IOclEngine oclEngine,INakedCallOperationAction node){
		super(oclEngine, node);
		delegate=new OperationCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine));
	}
}
