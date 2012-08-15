package org.opaeum.javageneration.bpm.actions;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.VariableAction;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.bpm.activity.ActivityUtil;
import org.opaeum.javageneration.util.OJUtil;

public final class Jbpm5ObjectNodeExpressor extends AbstractObjectNodeExpressor{
	// oyoyoyoyoy Jbpm
	public static final String EXCEPTION_FIELD = "currentException";
	@Override
	public boolean pinsAvailableAsVariables(){
		return true;
	}
	public Jbpm5ObjectNodeExpressor(OJUtil ojUtil){
		super(ojUtil);
	}
	@Override
	public String pathToVariableContext(VariableAction action){
		if(EmfElementFinder.getContainer(action.getVariable()) instanceof Activity){
			if(EmfElementFinder.getContainer(action) instanceof Activity){
				return "this.";
			}else{
				return "getContainingActivity().";
			}
		}else{
			StringBuilder sb = new StringBuilder();
			ActivityNode node = action;
			while(EmfElementFinder.getContainer(node) != EmfElementFinder.getContainer(action)){
				node = (ActivityNode) EmfElementFinder.getContainer(node);
				sb.append("getNodeContainer().");
			}
			return sb.toString();
		}
	}
	public String storeResults(PropertyMap resultMap,String call,boolean isMany){
		if(resultMap.isCollection()){
			if(isMany){
				call = resultMap.allAdder() + "(" + call + ")";
			}else{
				call = resultMap.adder() + "(" + call + ")";
			}
		}else{
			call = resultMap.setter() + "(" + call + ")";
		}
		return call;
	}
	@Override
	public OJAnnotatedField buildResultVariable(OJAnnotatedOperation operation,OJBlock block,PropertyMap resultMap){
		OJAnnotatedField outPinVar = new OJAnnotatedField(resultMap.fieldname(), resultMap.javaTypePath());
		block.addToLocals(outPinVar);
		return outPinVar;
	}
	public String surroundWithBehaviorCall(String expression,Behavior transformation,ObjectFlow flow){
		OperationMap map = ojUtil.buildOperationMap(transformation);
		if(transformation.getContext().conformsTo(EmfActivityUtil.getContainingActivity(flow))){
			if(ActivityUtil.flowsInStructuredNode(flow)){
				return "getContainingActivity()." + map.javaOperName() + "(" + expression + ")";
			}else{
				return map.javaOperName() + "(" + expression + ")";
			}
		}else if(EmfActivityUtil.getContainingActivity(flow).getContext() != null && transformation.getContext().conformsTo(EmfActivityUtil.getContainingActivity(flow).getContext())){
			return "getContextObject()." + map.javaOperName() + "(" + expression + ")";
		}else{
			return map.javaOperName() + "(" + expression + ")";
		}
	}
	public String expressInputPinOrOutParamOrExpansionNode(OJBlock block,ObjectNode pin){
		if(pin.getIncomings().isEmpty()){
			return "null";
		}else{
			ObjectFlow edge = (ObjectFlow) pin.getIncomings().iterator().next();
			ObjectNode feedingNode = EmfActivityUtil.getFeedingNode( pin);
			PropertyMap feedingMap = ojUtil.buildStructuralFeatureMap(feedingNode);
			String call = feedingMap.getter() + "()";
			return surroundWithSelectionAndTransformation(call, edge);
		}
	}
	protected String initForResultVariable(PropertyMap map){
		return map.getter() + "()";
	}
	public String expressExceptionInput(OJBlock block,ObjectNode pin){
		PropertyMap map = ojUtil.buildStructuralFeatureMap(pin);
		return "(" + map.javaType() + ")" + EXCEPTION_FIELD;
	}
	@Override
	public String expressFeedingNodeForObjectFlowGuard(OJBlock block,ObjectFlow flow){
		ObjectNode feedingNode = (ObjectNode) EmfActivityUtil.getOriginatingObjectNode( flow);
		PropertyMap map = ojUtil.buildStructuralFeatureMap(feedingNode);
		String call = map.getter() + "()";
		return surroundWithSelectionAndTransformation(call, flow);
	}
	@Override
	public String clear(PropertyMap map){
		return map.clearer() + "()";
	}
}