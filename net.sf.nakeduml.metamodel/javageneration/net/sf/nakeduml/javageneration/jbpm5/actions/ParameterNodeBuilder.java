package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ParameterNodeImplementor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.util.ExceptionHolder;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.ParameterDirectionKind;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ParameterNodeBuilder extends Jbpm5ActionBuilder<INakedParameterNode>{
	private ParameterNodeImplementor delegate;
	public ParameterNodeBuilder(IOclEngine oclEngine,INakedParameterNode node){
		super(oclEngine, node);
		this.delegate=new ParameterNodeImplementor(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		delegate.implementActionOn(operation, operation.getBody());
	}
	@Override
	public void implementConditionalFlows(OJOperation operationContext,OJBlock parentBlock, boolean getToken){
		if(node.getParameter().getDirection().equals(ParameterDirectionKind.IN)){
			super.implementConditionalFlows(operationContext, parentBlock,getToken);
		}
	}
}