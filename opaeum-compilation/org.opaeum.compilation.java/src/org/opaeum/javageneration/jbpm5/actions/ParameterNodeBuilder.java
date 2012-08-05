package org.opaeum.javageneration.jbpm5.actions;

import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.simpleactions.ParameterNodeImplementor;
import org.opaeum.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import org.opaeum.javageneration.util.OJUtil;

public class ParameterNodeBuilder extends Jbpm5ActionBuilder<ActivityParameterNode>{
	private SimpleNodeBuilder<ActivityParameterNode> delegate;
	public ParameterNodeBuilder(OJUtil ojUtil,ActivityParameterNode node){
		super(ojUtil, node);
		this.delegate = new ParameterNodeImplementor(node, new Jbpm5ObjectNodeExpressor(ojUtil));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		delegate.implementActionOn(operation, operation.getBody());
	}
	@Override
	public void implementConditionalFlows(OJOperation operationContext,OJBlock block){
		if(node.getParameter().getDirection().equals(ParameterDirectionKind.IN_LITERAL)){
			super.implementConditionalFlows(operationContext, block);
		}
		
	}
}