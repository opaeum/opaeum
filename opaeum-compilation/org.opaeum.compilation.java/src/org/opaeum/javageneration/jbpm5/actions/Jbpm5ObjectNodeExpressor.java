package org.opaeum.javageneration.jbpm5.actions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.activity.ActivityUtil;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedVariableAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public final class Jbpm5ObjectNodeExpressor extends AbstractObjectNodeExpressor{
	// oyoyoyoyoy Jbpm
	public static final String EXCEPTION_FIELD = "currentException";
	@Override
	public boolean pinsAvailableAsVariables(){
		return true;
	}
	public Jbpm5ObjectNodeExpressor(OpaeumLibrary l){
		super(l);
	}
	@Override
	public String pathToVariableContext(INakedVariableAction action){
		if(action.getVariable().getOwnerElement() instanceof INakedActivity){
			if(action.getOwnerElement() instanceof INakedActivity){
				return "this.";
			}else{
				return "getContainingActivity().";
			}
		}else{
			StringBuilder sb = new StringBuilder();
			INakedActivityNode node = action;
			while(node.getOwnerElement() != action.getOwnerElement()){
				node = (INakedActivityNode) node.getOwnerElement();
				sb.append("getNodeContainer().");
			}
			return sb.toString();
		}
	}
	public String storeResults(NakedStructuralFeatureMap resultMap,String call,boolean isMany){
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
	public OJAnnotatedField buildResultVariable(OJAnnotatedOperation operation,OJBlock block,NakedStructuralFeatureMap resultMap){
		OJAnnotatedField outPinVar = new OJAnnotatedField(resultMap.fieldname(), resultMap.javaTypePath());
		block.addToLocals(outPinVar);
		return outPinVar;
	}
	public String surroundWithBehaviorCall(String expression,INakedBehavior transformation,INakedObjectFlow flow){
		NakedOperationMap map = OJUtil.buildOperationMap(transformation);
		if(transformation.getContext().conformsTo(flow.getActivity())){
			if(ActivityUtil.flowsInStructuredNode(flow)){
				return "getContainingActivity()." + map.javaOperName() + "(" + expression + ")";
			}else{
				return map.javaOperName() + "(" + expression + ")";
			}
		}else if(flow.getActivity().getContext() != null && transformation.getContext().conformsTo(flow.getActivity().getContext())){
			return "getContextObject()." + map.javaOperName() + "(" + expression + ")";
		}else{
			return map.javaOperName() + "(" + expression + ")";
		}
	}
	public String expressInputPinOrOutParamOrExpansionNode(OJBlock block,INakedObjectNode pin){
		if(pin.getIncoming().isEmpty()){
			return "null";
		}else{
			INakedObjectFlow edge = (INakedObjectFlow) pin.getIncoming().iterator().next();
			INakedObjectNode feedingNode = pin.getFeedingNode();
			NakedStructuralFeatureMap feedingMap = OJUtil.buildStructuralFeatureMap(feedingNode.getActivity(), feedingNode, shouldEnsureUniquenes(feedingNode));
			String call = feedingMap.getter() + "()";
			return surroundWithSelectionAndTransformation(call, edge);
		}
	}
	protected String initForResultVariable(NakedStructuralFeatureMap map){
		return map.getter() + "()";
	}
	public String expressExceptionInput(OJBlock block,INakedObjectNode pin){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, true);
		return "(" + map.javaType() + ")" + EXCEPTION_FIELD;
	}
	@Override
	public String expressFeedingNodeForObjectFlowGuard(OJBlock block,INakedObjectFlow flow){
		INakedObjectNode feedingNode = (INakedObjectNode) flow.getOriginatingObjectNode();
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(flow.getActivity(), feedingNode, shouldEnsureUniquenes(feedingNode));
		String call = map.getter() + "()";
		return surroundWithSelectionAndTransformation(call, flow);
	}
	@Override
	public String clear(NakedStructuralFeatureMap map){
		return map.clearer() + "()";
	}
}