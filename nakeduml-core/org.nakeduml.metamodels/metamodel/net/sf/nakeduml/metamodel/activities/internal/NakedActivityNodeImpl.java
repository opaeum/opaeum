package net.sf.nakeduml.metamodel.activities.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityPartition;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

public class NakedActivityNodeImpl extends NakedElementImpl implements INakedActivityNode {
	@Override
	public Collection<INakedElement> getOwnedElements() {
		// activity nodes have to derive this collection, so duplicate it to
		// avoid accidental growth
		return new HashSet<INakedElement>(super.getOwnedElements());
	}

	private static final long serialVersionUID = 1142310904812L;
	private Set<INakedActivityEdge> incoming = new HashSet<INakedActivityEdge>();
	private Set<INakedActivityEdge> outgoing = new HashSet<INakedActivityEdge>();
	private INakedActivityPartition inPartition;

	public void addIncoming(INakedActivityEdge edge) {
		this.incoming.add(edge);
	}

	public void addOutgoing(INakedActivityEdge edge) {
		this.outgoing.add(edge);
	}

	@Override
	public String getMetaClass() {
		return "activityNode";
	}

	public boolean isImplicitFork() {
		return getDefaultOutgoing().size() > 1 && getConditionalOutgoing().isEmpty();
	}

	public boolean isImplicitDecision() {
		return getDefaultOutgoing().size() <= 1 && getConditionalOutgoing().size() >= 1;
	}

	public boolean isImplicitJoin() {
		Set<INakedActivityEdge> in = getAllEffectiveIncoming();
		int i = 0;
		for (INakedActivityEdge e : in) {
			if (!isFromInputParameter(e)) {
				// Forks caused by multiple InputParameters will be resolved by
				// the process flow generation and 
				i++;
			}
		}
		return i > 1;
	}

	private boolean isFromInputParameter(INakedActivityEdge e) {
		return e.getSource() instanceof INakedParameterNode && ((INakedParameterNode) e.getSource()).getParameter().isArgument();
	}

	public Set<INakedActivityEdge> getOutgoing() {
		return this.outgoing;
	}

	public Set<INakedActivityEdge> getIncoming() {
		return this.incoming;
	}

	public Set<INakedActivityEdge> getAllEffectiveOutgoing() {
		return getOutgoing();
	}

	public Set<INakedActivityEdge> getAllEffectiveIncoming() {
		return getIncoming();
	}

	public INakedStructuredActivityNode getInStructuredNode() {
		INakedElement ns = (INakedElement) super.getOwnerElement();
		if (ns instanceof INakedStructuredActivityNode) {
			return (INakedStructuredActivityNode) ns;
		} else if (ns instanceof INakedActivityNode) {
			return ((INakedActivityNode) ns).getInStructuredNode();
		} else {
			return null;
		}
	}

	public INakedActivity getActivity() {
		INakedElement ns = (INakedElement) super.getOwnerElement();
		if (ns instanceof INakedActivity) {
			return (INakedActivity) ns;
		} else if (ns instanceof INakedActivityNode) {
			return ((INakedActivityNode) ns).getActivity();
		} else {
			return null;
		}
	}

	public INakedActivityPartition getInPartition() {
		return this.inPartition;
	}

	public void setInPartition(INakedActivityPartition inParition) {
		this.inPartition = inParition;
	}

	public Set<INakedActivityEdge> getConditionalOutgoing() {
		return getOutgoing(true);
	}

	private Set<INakedActivityEdge> getOutgoing(boolean hasGuard) {
		Set<INakedActivityEdge> results = new HashSet<INakedActivityEdge>();
		for (INakedActivityEdge e : getAllEffectiveOutgoing()) {
			if (e.hasGuard()==hasGuard) {
				results.add(e);
			}
		}
		return results;
	}

	public Set<INakedActivityEdge> getDefaultOutgoing() {
		return getOutgoing(false);
	}

	@Override
	public void addOwnedElement(INakedElement element) {
		super.addOwnedElement(element);
	}
}
