package net.sf.nakeduml.metamodel.activities.internal;

import java.util.Collection;
import java.util.Iterator;

import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.ObjectNodeType;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import nl.klasse.octopus.model.IClassifier;

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
	
	public INakedExceptionHandler getIncomingExceptionHandler() {
		return incomingExceptionHandler;
	}

	public void setIncomingExceptionHandler(INakedExceptionHandler incomingExceptionHandler) {
		this.incomingExceptionHandler = incomingExceptionHandler;
	}
	private INakedObjectNode getObjectNodeSource(Collection<INakedActivityEdge> source){
		Iterator<INakedActivityEdge> iter = source.iterator();
		if(iter.hasNext()){
			INakedObjectFlow flow = (INakedObjectFlow) iter.next();
			if(flow.getSource() instanceof INakedObjectNode){
				return (INakedObjectNode) flow.getSource();
			}else if(flow.getSource() instanceof INakedControlNode){
				return getObjectNodeSource(flow.getSource().getIncoming());
			}
		}
		return null;
	}
	public INakedObjectNode getFedNode(){
		return getObjectNodeTarget(getOutgoing());
	}
	private INakedObjectNode getObjectNodeTarget(Collection<INakedActivityEdge> outgoing){
		Iterator<INakedActivityEdge> iter = outgoing.iterator();
		if(iter.hasNext()){
			INakedObjectFlow flow = (INakedObjectFlow) iter.next();
			if(flow.getTarget() instanceof INakedObjectNode){
				return (INakedObjectNode) flow.getTarget();
			}else if(flow.getTarget() instanceof INakedControlNode){
				return getObjectNodeSource(flow.getTarget().getOutgoing());
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
