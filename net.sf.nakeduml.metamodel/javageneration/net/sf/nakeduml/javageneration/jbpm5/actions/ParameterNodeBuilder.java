package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.util.ExceptionHolder;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.ParameterDirectionKind;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ParameterNodeBuilder extends JbpmActionBuilder<INakedParameterNode>{
	public ParameterNodeBuilder(IOclEngine oclEngine,INakedParameterNode node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJOperation operation){
		if(!node.getParameter().getDirection().equals(ParameterDirectionKind.IN)){
			NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node);
			if(node.getIncoming().size() == 1){
				// consume input token where necessary
				INakedObjectNode feedingNode = node.getFeedingNode();
				StructuralFeatureMap sourceMap = OJUtil.buildStructuralFeatureMap(feedingNode.getActivity(), feedingNode);
				retrieveOutParamFromBehaviorInstance(operation.getBody(), feedingNode, sourceMap);
				operation.getBody().addToStatements(resultMap.setter() + "(" + sourceMap.getter() + "())");
			}
			if(node.getParameter().isException()){
				if(node.getActivity().isProcess()){
					// TODO JBPM exception handling
					// oper.getBody().addToStatements("processInstance.getRootToken().end()");
				}else{
					OJPathName pathName = OJUtil.classifierPathname(node.getNakedBaseType());
					operation.getOwner().addToImports(pathName);
					operation.getOwner().addToImports(new OJPathName(ExceptionHolder.class.getName()));
					operation.getBody().addToStatements("throw new ExceptionHolder(this,getContextObject()," + resultMap.getter() + "())");
				}
			}
		}
	}
	@Override
	public void implementConditionalFlows(OJOperation operationContext,OJBlock parentBlock, boolean getToken){
		if(node.getParameter().getDirection().equals(ParameterDirectionKind.IN)){
			super.implementConditionalFlows(operationContext, parentBlock,getToken);
		}
	}
}