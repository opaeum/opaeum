package org.opaeum.metamodel.activities.internal;

import java.util.Collection;
import java.util.Iterator;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.actions.INakedExceptionHandler;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.ObjectNodeType;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;

public class NakedObjectNodeImpl extends NakedActivityNodeImpl implements INakedObjectNode{
	private static final long serialVersionUID = 1789017383946876842L;
	private boolean ordered;
	private boolean unique;
	protected INakedMultiplicity multiplicity;
	protected IClassifier type;
	private INakedClassifier baseType;
	private int index;
	private INakedExceptionHandler incomingExceptionHandler;
	public INakedObjectNode getFeedingNode(){
		return getObjectNodeSource(getIncoming());
	}
	public INakedExceptionHandler getIncomingExceptionHandler(){
		return incomingExceptionHandler;
	}
	public void setIncomingExceptionHandler(INakedExceptionHandler incomingExceptionHandler){
		this.incomingExceptionHandler = incomingExceptionHandler;
	}
	private INakedObjectNode getObjectNodeSource(Collection<INakedActivityEdge> source){
		for(INakedActivityEdge edge:source){
			return ((INakedObjectFlow) edge).getOriginatingObjectNode();
		}
		return null;
	}
	public INakedObjectNode getFedNode(){
		return getObjectNodeTarget(getOutgoing());
	}
	private INakedObjectNode getObjectNodeTarget(Collection<INakedActivityEdge> outgoing){
		Iterator<INakedActivityEdge> iter = outgoing.iterator();
		while(iter.hasNext()){
			INakedActivityEdge next = iter.next();
			if(next instanceof INakedObjectFlow){
				INakedObjectFlow flow = (INakedObjectFlow) next;
				if(flow.getTarget() instanceof INakedObjectNode){
					return (INakedObjectNode) flow.getTarget();
				}else if(flow.getTarget() instanceof INakedControlNode){
					return getObjectNodeTarget(flow.getTarget().getOutgoing());
				}
			}
		}
		return null;
	}
	public int getIndex(){
		return this.index;
	}
	public void setIndex(int index){
		this.index = index;
	}
	public boolean isOrdered(){
		return this.ordered;
	}
	public void setIsOrdered(boolean isOrdered){
		this.ordered = isOrdered;
	}
	public boolean isUnique(){
		return this.unique;
	}
	public void setIsUnique(boolean isUnique){
		this.unique = isUnique;
	}
	public IClassifier getType(){
		return this.type;
	}
	public void setType(IClassifier type){
		this.type = type;
	}
	public INakedMultiplicity getNakedMultiplicity(){
		return this.multiplicity;
	}
	public void setMultiplicity(INakedMultiplicity multiplicity){
		this.multiplicity = multiplicity;
	}
	public void setBaseType(INakedClassifier type){
		this.baseType = type;
	}
	public INakedClassifier getNakedBaseType(){
		return this.baseType;
	}
	public ObjectNodeType getObjectNodeType(){
		return ObjectNodeType.CENTRAL_BUFFER;
	}
}
