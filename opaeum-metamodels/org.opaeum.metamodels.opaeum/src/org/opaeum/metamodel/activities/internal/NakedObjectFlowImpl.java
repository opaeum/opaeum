package org.opaeum.metamodel.activities.internal;

import java.util.Set;

import org.eclipse.uml2.uml.ControlNodeType;
import org.eclipse.uml2.uml.INakedActivityEdge;
import org.eclipse.uml2.uml.INakedActivityNode;
import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedControlNode;
import org.eclipse.uml2.uml.INakedExpansionNode;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedObjectFlow;
import org.eclipse.uml2.uml.INakedObjectNode;
import org.eclipse.uml2.uml.INakedOutputPin;

public class NakedObjectFlowImpl extends NakedActivityEdgeImpl implements INakedObjectFlow{
	private static final long serialVersionUID = 6481759202136150887L;
	private INakedBehavior transformation;
	private INakedBehavior selection;
	public NakedObjectFlowImpl(){
		super();
	}
	@Override
	public INakedActivityNode getEffectiveTarget(){
		if(getTarget() instanceof INakedExpansionNode){
			INakedExpansionNode target = (INakedExpansionNode) getTarget();
			if(target.isInputElement()){
				return target.getExpansionRegion();
			}else{
				return target;
			}
		}
		if(getTarget() instanceof INakedInputPin){
			return (INakedActivityNode) getTarget().getOwnerElement();
		}else{
			return getTarget();
		}
	}
	public INakedBehavior getTransformation(){
		return this.transformation;
	}
	public void setTransformation(INakedBehavior transformation){
		this.transformation = transformation;
	}
	public INakedBehavior getSelection(){
		return selection;
	}
	public void setSelection(INakedBehavior selection){
		this.selection = selection;
	}
	@Override
	public INakedActivityNode getEffectiveSource(){
		if(getSource() instanceof INakedExpansionNode){
			INakedExpansionNode source = (INakedExpansionNode) getSource();
			if(source.isOutputElement()){
				return source.getExpansionRegion();
			}else{
				return source;
			}
		}else if(getSource() instanceof INakedOutputPin){
			return (INakedActivityNode) getSource().getOwnerElement();
		}else{
			return getSource();
		}
	}
	@Override
	public INakedObjectNode getOriginatingObjectNode(){
		if(getSource() instanceof INakedObjectNode){
			return (INakedObjectNode) getSource();
		}else if(getSource() instanceof INakedControlNode){
			INakedControlNode c= (INakedControlNode) getSource();
			Set<INakedActivityEdge> allEffectiveIncoming = getSource().getAllEffectiveIncoming();
			if((c.getControlNodeType()==ControlNodeType.JOIN_NODE ||c.getControlNodeType()==ControlNodeType.MERGE_NODE) && multipleObjectFlows(allEffectiveIncoming)){
				//Eliminate guess work. Under these conditions it would be misleading to return anything
				return null;
			}
			for(INakedActivityEdge edge:allEffectiveIncoming){
				if(edge instanceof INakedObjectFlow){
					return ((INakedObjectFlow) edge).getOriginatingObjectNode();
				}
			}
		}
		return null;
	}
	@Override
	public INakedObjectNode getFedObjectNode(){
		if(getTarget() instanceof INakedObjectNode){
			return (INakedObjectNode) getTarget();
		}else if(getTarget() instanceof INakedControlNode){
			INakedControlNode c= (INakedControlNode) getTarget();
			Set<INakedActivityEdge> allEffectiveOutgoing= getTarget().getAllEffectiveOutgoing();
			if((c.getControlNodeType()==ControlNodeType.FORK_NODE ||c.getControlNodeType()==ControlNodeType.DECISION_NODE) && multipleObjectFlows(allEffectiveOutgoing)){
				//Eliminate guess work. Under these conditions it would be misleading to return anything
				return null;
			}
			for(INakedActivityEdge edge:allEffectiveOutgoing){
				if(edge instanceof INakedObjectFlow){
					return ((INakedObjectFlow) edge).getFedObjectNode();
				}
			}
		}
		return null;
	}
	private boolean multipleObjectFlows(Set<INakedActivityEdge> allEffectiveIncoming){
		int count=0;
		for(INakedActivityEdge edge:allEffectiveIncoming){
			if(edge instanceof INakedObjectFlow){
				count++;
			}
		}
		return count>1;
	}
}
