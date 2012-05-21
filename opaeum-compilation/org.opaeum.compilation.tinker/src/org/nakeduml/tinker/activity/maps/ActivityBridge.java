package org.nakeduml.tinker.activity.maps;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.name.NameConverter;

public class ActivityBridge extends AbstractEmulatedProperty implements INakedProperty {

	protected INakedBehavior activityNode1;
	private boolean inverse = false;

	@Override
	public boolean isInverse() {
		return this.inverse;
	}

	public ActivityBridge(INakedClassifier owner, INakedBehavior activityNode1, boolean inverse) {
		super(owner, activityNode1);
		this.activityNode1 = activityNode1;
		this.inverse = inverse;
	}

	@Override
	public boolean isDerived() {
		return super.isDerived();
	}

	public INakedClassifier getNakedBaseType() {
		return new ConcreteEmulatedClassifier(this.activityNode1.getNameSpace(), this.activityNode1);
	}

	public boolean isOrdered() {
		return false;
	}

	public boolean isUnique() {
		return false;
	}

	public INakedMultiplicityElement getOriginal() {
		return null;
	}

	public IClassifier getType() {
		return new ConcreteEmulatedClassifier(this.activityNode1.getNameSpace(), this.activityNode1);
	}

	@Override
	public String getName() {
		return NameConverter.capitalize(this.activityNode1.getName());
	}

	public INakedMultiplicity getNakedMultiplicity() {
		return new NakedMultiplicityImpl(1, 1);
	}

	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd() {
		return Collections.emptySet();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof ActivityBridge) {
			ActivityBridge o = (ActivityBridge) other;
			return o == this || (o.getId().equals(getId()));
		} else {
			return false;
		}
	}

	@Override
	public boolean isMeasure() {
		return false;
	}

	@Override
	public boolean isDimension() {
		return false;
	}

}
