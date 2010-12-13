package net.sf.nakeduml.metamodel.activities.internal;

import java.util.Collection;
import java.util.Set;

import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedModelElementImpl;

public class NakedActivityEdgeImpl extends NakedModelElementImpl implements INakedActivityEdge {
	private static final long serialVersionUID = 6408889822146373878L;
	private INakedActivityNode source;
	private INakedActivityNode target;
	private INakedValueSpecification guardExpression;
	private INakedValueSpecification weight;

	public INakedValueSpecification getGuard() {
		return this.guardExpression;
	}

	public void setGuard(INakedValueSpecification guardExpression) {
		this.guardExpression = guardExpression;
	}

	public INakedActivityNode getSource() {
		return this.source;
	}

	public void setSource(INakedActivityNode source) {
		this.source = source;
		source.getOutgoing().add(this);
	}

	public INakedActivityNode getTarget() {
		return this.target;
	}

	public void setTarget(INakedActivityNode target) {
		this.target = target;
		Set<INakedActivityEdge> incoming = target.getIncoming();
		incoming.add(this);
	}

	@Override
	public String getMetaClass() {
		return "activityEdge";
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> results = super.getOwnedElements();
		if (this.guardExpression != null) {
			results.add(this.guardExpression);
		}
		if (this.weight != null) {
			results.add(weight);
		}
		return results;
	}

	@Override
	public void addOwnedElement(INakedElement element) {
	}

	public INakedActivityNode getEffectiveTarget() {
		return getTarget();
	}

	public boolean isFromExceptionPin() {
		if (getSource() instanceof INakedOutputPin) {
			INakedOutputPin o = (INakedOutputPin) getSource();
			if (o.getLinkedTypedElement() instanceof INakedParameter) {
				INakedParameter p = (INakedParameter) o.getLinkedTypedElement();
				if (p.isException()) {
					return true;
				}
			}
		}
		return false;
	}

	public INakedValueSpecification getWeight() {
		return weight;
	}

	public void setWeight(INakedValueSpecification weight) {
		this.weight = weight;
	}

	public INakedActivityNode getEffectiveSource() {
		return getSource();
	}

	public INakedClassifier getContext() {
		return getTarget().getActivity().getContext();
	}

	public INakedActivity getOwningBehavior() {
		return getActivity();
	}

	public INakedActivity getActivity() {
		return getTarget().getActivity();
	}

	@Override
	public boolean hasGuard() {
		return getGuard() != null && !Boolean.TRUE.equals(getGuard().getValue());
	}
}
