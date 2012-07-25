package org.opaeum.javageneration.basicjava;

import java.util.List;

import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.VariableAction;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class AbstractObjectNodeExpressor{
	protected OpaeumLibrary library;
	public AbstractObjectNodeExpressor(OpaeumLibrary l){
		this.library = l;
	}
	public abstract String storeResults(NakedStructuralFeatureMap resultMap,String call,boolean isMany);
	abstract public boolean pinsAvailableAsVariables();
	public abstract String clear(NakedStructuralFeatureMap map);
	public abstract String expressFeedingNodeForObjectFlowGuard(OJBlock block,ObjectFlow flow);
	abstract public String expressInputPinOrOutParamOrExpansionNode(OJBlock block,ObjectNode pin);
	abstract public OJAnnotatedField buildResultVariable(OJAnnotatedOperation operation,OJBlock block,NakedStructuralFeatureMap map);
	public abstract String pathToVariableContext(VariableAction action);
	protected abstract String surroundWithBehaviorCall(String expression,Behavior b, ObjectFlow flow);
	protected String surroundWithSelectionAndTransformation(String expression,ObjectFlow edge){
		if(edge.getSource() instanceof ControlNode){
			List<ActivityEdge> incoming = edge.getSource().getIncomings();
			for(ActivityEdge flow:incoming){
				if(flow instanceof ObjectFlow){
					// TODO with merges, find out which transition was actually
					// taken
					expression = surroundWithSelectionAndTransformation(expression, (ObjectFlow) flow);
					break;
				}
			}
		}
		Behavior selection = edge.getSelection();
		if(selection != null){
			expression = surroundWithBehaviorCall(expression, selection, edge);
		}
		Behavior transformation = edge.getTransformation();
		if(transformation != null){
			expression = surroundWithBehaviorCall(expression, transformation, edge);
		}
		if(selection == null && transformation == null){
			ObjectNode source = EmfActivityUtil.getOriginatingObjectNode( edge);
			// TODO what if the target is a controlNode
			if(edge.getTarget() instanceof Pin){
				Pin target = (Pin) edge.getTarget();
				// TODO need to take the transformations and selections of intermediary object flows into account
				if(target.isMultivalued() && EmfActivityUtil.isMultivalued( source)
						&& (source.isOrdered() != target.isOrdered() || source.isUnique() != target.isUnique())){
					NakedStructuralFeatureMap targetMap = OJUtil.buildStructuralFeatureMap(EmfActivityUtil.getContainingActivity( edge), target);
					expression = "new " + targetMap.javaDefaultTypePath().getLast() + "<" + targetMap.javaDefaultTypePath().getElementTypes().get(0).getLast() + ">("
							+ expression + ")";
				}
			}
		}
		return expression;
	}
	public String expressExceptionInput(OJBlock block,ObjectNode pin){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(EmfActivityUtil.getContainingActivity(pin), pin);
		return "(" + map.javaType() + ")e.getValue()";
	}
	protected OpaeumLibrary getLibrary(){
		return library;
	}
	protected boolean shouldEnsureUniquenes(ObjectNode feedingNode){
		boolean ensureUniqueness = true;
		if((feedingNode instanceof ExpansionNode && ((ExpansionNode) feedingNode).getRegionAsInput()!=null) || feedingNode instanceof ActivityParameterNode){
			ensureUniqueness = false;
		}
		return ensureUniqueness;
	}
}
