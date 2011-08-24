package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.actions.ITargetElement;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActionImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public abstract class NakedStructuralFeatureActionImpl extends NakedActionImpl implements IActionWithTargetElement {
	private static final long serialVersionUID = 3165514874679324190L;
	public INakedProperty feature;
	public INakedInputPin object;

	public Set<INakedInputPin> getInput() {
		Set<INakedInputPin> results = new HashSet<INakedInputPin>();
		if (object != null) {
			results.add(object);
		}
		return results;
	}
	public Collection<INakedOutputPin> getOutput() {
		return new HashSet<INakedOutputPin>();
	}

	public INakedClassifier getExpectedTargetType() {
		return feature.getOwner();
	}

	public ITargetElement getTargetElement() {
		if (getTarget() == null) {
			return getInPartition();
		} else {
			return getTarget();
		}
	}

	public INakedProperty getFeature() {
		return this.feature;
	}

	public void setFeature(INakedProperty feature) {
		this.feature = feature;
	}

	public INakedInputPin getObject() {
		return this.object;
	}

	public void setObject(INakedInputPin object) {
		removeOwnedElement(this.object);
		this.object = object;
	}

	public ActionType getActionType() {
		return ActionType.WRITE_STRUCTURAL_FEATURE_ACTION;
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> result = super.getOwnedElements();
		if (this.object != null) {
			result.add(this.object);
		}
		return result;
	}

	public INakedInputPin getTarget() {
		return getObject();
	}
}
