package org.opeum.javageneration.jbpm5.actions;

import org.opeum.javageneration.basicjava.simpleactions.ParameterNodeImplementor;
import org.opeum.metamodel.activities.INakedParameterNode;
import org.opeum.metamodel.workspace.OpeumLibrary;
import nl.klasse.octopus.model.ParameterDirectionKind;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class ParameterNodeBuilder extends Jbpm5ActionBuilder<INakedParameterNode>{
	private ParameterNodeImplementor delegate;
	public ParameterNodeBuilder(OpeumLibrary oclEngine,INakedParameterNode node){
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