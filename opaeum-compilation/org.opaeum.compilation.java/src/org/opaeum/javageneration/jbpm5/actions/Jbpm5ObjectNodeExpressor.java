package org.opaeum.javageneration.jbpm5.actions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
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
	public OJAnnotatedField buildResultVariable(OJAnnotatedOperation operation,OJBlock block,NakedStructuralFeatureMap resultMap){
		OJAnnotatedField outPinVar = new OJAnnotatedField(resultMap.fieldname(), resultMap.javaTypePath());
		block.addToLocals(outPinVar);
		return outPinVar;
	}
	@Override
	public String getterForStructuredResults(NakedStructuralFeatureMap resultMap){
		return resultMap.getter() + "()";
	}
	@Override
	public String setterForSingleResult(NakedStructuralFeatureMap resultMap,String call){
		return resultMap.setter() + "(" + call + ")";
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
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(flow.getActivity(), feedingNode,shouldEnsureUniquenes(feedingNode));
		String call = map.getter() + "()";
		return surroundWithSelectionAndTransformation(call, flow);
	}
	@Override
	public String clear(NakedStructuralFeatureMap map){
		return map.clearer() +"()";
	}
}