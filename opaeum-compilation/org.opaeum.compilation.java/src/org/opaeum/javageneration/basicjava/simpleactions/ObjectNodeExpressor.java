package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.VariableAction;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.util.OJUtil;

public class ObjectNodeExpressor extends AbstractObjectNodeExpressor{
	public ObjectNodeExpressor(OJUtil ojUtil){
		super(ojUtil);
	}
	public boolean pinsAvailableAsVariables(){
		return false;
	}
	public String expressFeedingNodeForObjectFlowGuard(OJBlock block,ObjectFlow flow){
		ObjectNode feedingNode = (ObjectNode) EmfActivityUtil.getOriginatingObjectNode( flow);
		StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(feedingNode);
		String call = map.fieldname();// ActivityParameterNode or top level output
		return surroundWithSelectionAndTransformation(call, flow);
	}
	public final String expressInputPinOrOutParamOrExpansionNode(OJBlock block,ObjectNode pin){
		// Either an outputpin or parameterNode
		ObjectFlow edge = (ObjectFlow) pin.getIncomings().iterator().next();
		ObjectNode feedingNode = EmfActivityUtil.getFeedingNode( pin);
		StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(feedingNode);
		String call = map.fieldname();// ActivityParameterNode or top level output
										// pin or expansion node
		return surroundWithSelectionAndTransformation(call, edge);
	}
	public OJAnnotatedField buildResultVariable(OJAnnotatedOperation operation,OJBlock block,StructuralFeatureMap map){
		OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
		field.setInitExp(map.javaDefaultValue());
		block.addToLocals(field);
		operation.getOwner().addToImports(map.javaBaseTypePath());
		operation.getOwner().addToImports(map.javaDefaultTypePath());
		return field;
	}
	public String storeResults(StructuralFeatureMap resultMap,String call,boolean isMany){
		if(resultMap.isCollection()){
			if(isMany){
				call = resultMap.fieldname() + ".addAll(" + call + ")";
			}else{
				call = resultMap.fieldname() + ".add(" + call + ")";
			}
		}else{
			call = resultMap.fieldname() + "=" + call;
		}
		return call;
	}
	@Override
	public String clear(StructuralFeatureMap map){
		return map.fieldname() + ".clear()";
	}
	@Override
	protected String surroundWithBehaviorCall(String expression,Behavior b,ObjectFlow flow){
		// TODO Auto-generated method stub
		return ojUtil.buildOperationMap(b).javaOperName() + "(" + expression + ")";
	}
	@Override
	public String pathToVariableContext(VariableAction action){
		return "";
	}
}
