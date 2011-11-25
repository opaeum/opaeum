package org.opaeum.javageneration.jbpm5.actions;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.simpleactions.ParameterNodeImplementor;
import org.opaeum.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ParameterNodeBuilder extends Jbpm5ActionBuilder<INakedParameterNode>{
	private SimpleNodeBuilder<INakedParameterNode> delegate;
	public ParameterNodeBuilder(OpaeumLibrary oclEngine,INakedParameterNode node){
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