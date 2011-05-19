package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.ParameterNodeImplementor;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import nl.klasse.octopus.model.ParameterDirectionKind;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ParameterNodeBuilder extends Jbpm5ActionBuilder<INakedParameterNode>{
	private ParameterNodeImplementor delegate;
	public ParameterNodeBuilder(IOclEngine oclEngine,INakedParameterNode node){
		super(oclEngine, node);
		this.delegate = new ParameterNodeImplementor(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		delegate.implementActionOn(operation, operation.getBody());
	}
	@Override
	public void implementConditionalFlows(OJOperation operationContext,OJBlock block){
		if(node.getParameter().getDirection().equals(ParameterDirectionKind.IN)){
			super.implementConditionalFlows(operationContext, block);
		}
	}
}