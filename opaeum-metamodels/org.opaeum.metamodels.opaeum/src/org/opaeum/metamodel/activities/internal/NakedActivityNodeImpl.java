package org.opaeum.metamodel.activities.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import nl.klasse.octopus.expressions.internal.types.PathName;

import org.opaeum.metamodel.activities.ActivityNodeContainer;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityPartition;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.internal.NakedElementImpl;

public class NakedActivityNodeImpl extends NakedElementImpl implements INakedActivityNode{
	private static final long serialVersionUID = 1142310904812L;
	private Set<INakedActivityEdge> incoming = new TreeSet<INakedActivityEdge>();
	private Set<INakedActivityEdge> outgoing = new TreeSet<INakedActivityEdge>();
	private INakedActivityPartition inPartition;
	private Set<INakedActivityNode> redefinedNodes = new HashSet<INakedActivityNode>();
	@Override
	public Collection<INakedElement> getOwnedElements(){
		// activity nodes have to derive this collection, so duplicate it to
		// avoid accidental growth
		return new HashSet<INakedElement>(super.getOwnedElements());
	}
	public void addIncoming(INakedActivityEdge edge){
		this.incoming.add(edge);
	}
	public void addOutgoing(INakedActivityEdge edge){
		this.outgoing.add(edge);
	}
	@Override
	public void removeIncoming(INakedActivityEdge edge){
		this.incoming.remove(edge);
	}
	@Override
	public void removeOutgoing(INakedActivityEdge edge){
		this.outgoing.remove(edge);
	}
	@Override
	public String getMetaClass(){
		return "activityNode";
	}
	public boolean isImplicitFork(){
		return getDefaultOutgoing().size() > 1 && getConditionalOutgoing().isEmpty();
	}
	public boolean isImplicitDecision(){
		return getDefaultOutgoing().size() <= 1 && getConditionalOutgoing().size() >= 1;
	}
	public boolean isImplicitJoin(){
		Set<INakedActivityEdge> in = getAllEffectiveIncoming();
		int i = 0;
		for(INakedActivityEdge e:in){
			if(!isFromInputParameter(e)){
				// Forks caused by multiple InputParameters will be resolved by
				// the process flow generation and
				i++;
			}
		}
		return i > 1;
	}
	private boolean isFromInputParameter(INakedActivityEdge e){
		return e.getSource() instanceof INakedParameterNode && ((INakedParameterNode) e.getSource()).getParameter().isArgument();
	}
	public Set<INakedActivityEdge> getOutgoing(){
		return this.outgoing;
	}
	public Set<INakedActivityEdge> getIncoming(){
		return this.incoming;
	}
	public Set<INakedActivityEdge> getAllEffectiveOutgoing(){
		return getOutgoing();
	}
	public Set<INakedActivityEdge> getAllEffectiveIncoming(){
		return getIncoming();
	}
	public INakedStructuredActivityNode getInStructuredNode(){
		INakedElement ns = (INakedElement) super.getOwnerElement();
		if(ns instanceof INakedStructuredActivityNode){
			return (INakedStructuredActivityNode) ns;
		}else if(ns instanceof INakedActivityNode){
			return ((INakedActivityNode) ns).getInStructuredNode();
		}else{
			return null;
		}
	}
	public INakedActivity getActivity(){
		INakedElement ns = (INakedElement) super.getOwnerElement();
		if(ns instanceof INakedActivity){
			return (INakedActivity) ns;
		}else if(ns instanceof INakedActivityNode){
			return ((INakedActivityNode) ns).getActivity();
		}else{
			return null;
		}
	}
	public INakedActivityPartition getInPartition(){
		return this.inPartition;
	}
	public void setInPartition(INakedActivityPartition inParition){
		this.inPartition = inParition;
	}
	public Set<INakedActivityEdge> getConditionalOutgoing(){
		return getOutgoing(true);
	}
	private Set<INakedActivityEdge> getOutgoing(boolean hasGuard){
		Set<INakedActivityEdge> results = new TreeSet<INakedActivityEdge>();
		for(INakedActivityEdge e:getAllEffectiveOutgoing()){
			if(e.hasGuard() == hasGuard){
				results.add(e);
			}
		}
		return results;
	}
	public Set<INakedActivityEdge> getDefaultOutgoing(){
		return getOutgoing(false);
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
	}
	@Override
	public Set<INakedActivityNode> getRedefinedNodes(){
		return redefinedNodes;
	}
	public void setRedefinedNodes(Set<INakedActivityNode> redefinedNodes){
		this.redefinedNodes = redefinedNodes;
	}
	@Override
	public PathName getStatePath(){
//		if(getInStructuredNode() == null){
			return new PathName(getName());
//		}else{
//			PathName statePath = getInStructuredNode().getStatePath();
//			statePath.addString(getName());
//			return statePath;
//		}
	}
	@Override
	public INakedClassifier getNearestStructuredElementAsClassifier(){
		if(getOwnerElement() instanceof INakedActivity){
			return (INakedClassifier) getOwnerElement();
		}else if(getOwnerElement() instanceof INakedStructuredActivityNode){
			return ((INakedStructuredActivityNode)getOwnerElement()).getMessageStructure();
		}else if(getOwnerElement() instanceof INakedActivityNode){
			return ((INakedActivityNode)getOwnerElement()).getNearestStructuredElementAsClassifier();
		}
		return null;
	}
	@Override
	public ActivityNodeContainer getNearestNodeContainer(){
		INakedElementOwner ownerElement = getOwnerElement();
		while(!(ownerElement instanceof ActivityNodeContainer)){
			ownerElement=((INakedElement) ownerElement).getOwnerElement();
		}
		return (ActivityNodeContainer) ownerElement;
	}
}
