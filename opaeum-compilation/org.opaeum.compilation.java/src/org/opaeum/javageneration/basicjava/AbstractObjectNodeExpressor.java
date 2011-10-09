package org.opaeum.javageneration.basicjava;

import java.util.Set;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class AbstractObjectNodeExpressor{
	protected OpaeumLibrary library;
	public AbstractObjectNodeExpressor(OpaeumLibrary l){
		this.library = l;
	}
	abstract public boolean pinsAvailableAsVariables();
	public abstract String expressFeedingNodeForObjectFlowGuard(OJBlock block,INakedObjectFlow flow);
	abstract public String expressInputPinOrOutParamOrExpansionNode(OJBlock block,INakedObjectNode pin);
	abstract public OJAnnotatedField buildResultVariable(OJAnnotatedOperation operation,OJBlock block,NakedStructuralFeatureMap map);
	public abstract String setterForSingleResult(NakedStructuralFeatureMap resultMap,String call);
	public abstract String getterForStructuredResults(NakedStructuralFeatureMap resultMap);
	protected String surroundWithSelectionAndTransformation(String expression,INakedObjectFlow edge){
		if(edge.getSource() instanceof INakedControlNode){
			Set<INakedActivityEdge> incoming = edge.getSource().getIncoming();
			for(INakedActivityEdge flow:incoming){
				if(flow instanceof INakedObjectFlow){
					// TODO with merges, find out which transition was actually
					// taken
					expression = surroundWithSelectionAndTransformation(expression, (INakedObjectFlow) flow);
					break;
				}
			}
		}
		if(edge.getSelection() != null){
			expression = edge.getSelection().getMappingInfo().getJavaName() + "(" + expression + ")";
		}
		if(edge.getTransformation() != null){
			expression = edge.getTransformation().getMappingInfo().getJavaName() + "(" + expression + ")";
		}
		if(edge.getSelection() == null && edge.getTransformation() == null){
			INakedObjectNode source = edge.getOriginatingObjectNode();
			// TODO what if the target is a controlNode
			if(edge.getTarget() instanceof INakedObjectNode){
				INakedObjectNode target = (INakedObjectNode) edge.getTarget();
				// TODO need to take the transformations and selections of intermediary object flows into account
				if(target.getNakedMultiplicity().isMany() && source.getNakedMultiplicity().isMany()
						&& (source.isOrdered() != target.isOrdered() || source.isUnique() != target.isUnique())){
					NakedStructuralFeatureMap targetMap = OJUtil.buildStructuralFeatureMap(edge.getActivity(), target);
					expression = "new " + targetMap.javaDefaultTypePath().getLast() + "<" + targetMap.javaDefaultTypePath().getElementTypes().get(0).getLast() + ">("
							+ expression + ")";
				}
			}
		}
		return expression;
	}
	public String storeResults(NakedStructuralFeatureMap resultMap,String call,boolean isMany){
		if(resultMap.isCollection()){
			if(isMany){
				call = getterForStructuredResults(resultMap) + ".addAll(" + call + ")";
			}else{
				call = getterForStructuredResults(resultMap) + ".add(" + call + ")";
			}
		}else{
			call = setterForSingleResult(resultMap, call);
		}
		return call;
	}
	public String expressExceptionInput(OJBlock block,INakedObjectNode pin){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin);
		return "(" + map.javaType() + ")e.getValue()";
	}
	protected OpaeumLibrary getLibrary(){
		return library;
	}
}
