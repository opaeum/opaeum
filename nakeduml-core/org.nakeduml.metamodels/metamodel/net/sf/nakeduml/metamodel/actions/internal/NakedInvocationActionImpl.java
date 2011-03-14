package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.INakedInvocationAction;
import net.sf.nakeduml.metamodel.actions.ITargetElement;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActionImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public abstract class NakedInvocationActionImpl extends NakedActionImpl implements INakedInvocationAction {
	private static final long serialVersionUID = -2671425797278069465L;
	private INakedInputPin target;
	private List<INakedInputPin> arguments = new ArrayList<INakedInputPin>();

	public Set<INakedInputPin> getInput() {
		Set<INakedInputPin> results = new HashSet<INakedInputPin>();
		results.addAll(getArguments());
		if (target != null) {
			results.add(getTarget());
		}
		return results;
	}

	public INakedClassifier getExpectedTargetType() {
		return null;
	}

	public List<INakedInputPin> getArguments() {
		return this.arguments;
	}

	public void setArguments(List<INakedInputPin> arguments) {
		this.arguments = arguments;
	}

	public INakedInputPin getTarget() {
		return this.target;
	}

	public void setTarget(INakedInputPin target) {
		this.target = target;
	}

	public boolean hasTarget() {
		return this.target != null;
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> results = super.getOwnedElements();
		if (hasTarget()) {
			results.add(getTarget());
		}
		results.addAll(getArguments());
		return results;
	}

	public ITargetElement getTargetElement() {
		// Property Partition overrides the target
		if (getInPartition() != null && getInPartition().getRepresents() instanceof INakedProperty) {
			return getInPartition();
		} else if (getTarget() != null) {
			return getTarget();
		} else {
			// Classifier Partition
			return getInPartition();
		}
	}

	public boolean isTask() {
		return false;
	}
}
