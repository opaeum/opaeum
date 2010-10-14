package net.sf.nakeduml.metamodel.activities.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityPartition;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.internal.NakedModelElementImpl;

public class NakedActivityNodeImpl extends NakedModelElementImpl implements INakedActivityNode{
	@Override
	public Collection<INakedElement> getOwnedElements(){
		// activity nodes have to derive this collection, so duplicate it to avoid accidental growth
		return new HashSet<INakedElement>(super.getOwnedElements());
	}
	private static final long serialVersionUID = 1142310904812L;
	private Set<INakedActivityEdge> incoming = new HashSet<INakedActivityEdge>();
	private Set<INakedActivityEdge> outgoing = new HashSet<INakedActivityEdge>();
	private INakedActivityPartition inPartition;
	public void addIncoming(INakedActivityEdge edge){
		this.incoming.add(edge);
	}
	public void addOutgoing(INakedActivityEdge edge){
		this.outgoing.add(edge);
	}
	@Override
	public String getMetaClass(){
		return "activityNode";
	}
	public boolean isImplicitFork(){
		return getDefaultOutgoing().size() > 1;
	}
	public boolean isImplicitDecision(){
		return getConditionalOutgoing().size() > 1;
	}
	public boolean isImplicitJoin(){
		return getAllEffectiveIncoming().size() > 1;
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
		Set<INakedActivityEdge> results = new HashSet<INakedActivityEdge>();
		for(INakedActivityEdge e:getAllEffectiveOutgoing()){
			if(hasGuard == (e.getGuard() != null && !Boolean.TRUE.equals(e.getGuard().getValue()))){
				results.add(e);
			}
		}
		return results;
	}
	public Set<INakedActivityEdge> getDefaultOutgoing(){
		return getOutgoing(false);
	}
	@Override
	public final void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
	}
}
