package org.nakeduml.tinker.activity.maps;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.feature.MappingInfo;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.name.NameConverter;

public class ActivityNodeBridge extends AbstractEmulatedProperty implements INakedProperty {
	private static final long serialVersionUID = 211415204864858873L;
	protected INakedActivityNode activityNode1;
	private boolean inverse = false;

	public ActivityNodeBridge(INakedClassifier owner, INakedActivityNode activityNode1, boolean inverse) {
		super(owner, activityNode1);
		this.activityNode1 = activityNode1;
		this.inverse = inverse;
	}

	@Override
	public boolean isInverse() {
		return this.inverse;
	}

	@Override
	public MappingInfo getMappingInfo() {
		return super.getMappingInfo();
	}
	@Override
	public boolean isDerived() {
		return super.isDerived();
	}

	public INakedClassifier getNakedBaseType() {
		ConcreteEmulatedClassifier jippo;
		if (this.activityNode1 instanceof INakedPin) {
			jippo = new ConcretePinEmulatedClassifier(this.activityNode1.getNameSpace(), (INakedPin) this.activityNode1);
		} else {
			jippo = new ConcreteEmulatedClassifier(this.activityNode1.getNameSpace(), this.activityNode1);
		}
		return jippo;
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
		ConcreteEmulatedClassifier jippo;
		if (this.activityNode1 instanceof INakedPin) {
			jippo = new ConcretePinEmulatedClassifier(this.activityNode1.getNameSpace(), (INakedPin) this.activityNode1);
		} else {
			jippo = new ConcreteEmulatedClassifier(this.activityNode1.getNameSpace(), this.activityNode1);
		}
		return jippo;
	}

	@Override
	public String getName() {
		if (this.activityNode1 instanceof INakedPin) {
			return ((INakedPin)this.activityNode1).getAction().getMappingInfo().getJavaName().getCapped() + NameConverter.capitalize(this.activityNode1.getName());
		} else {
			return NameConverter.capitalize(this.activityNode1.getName());
		}
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
		if (other instanceof ActivityNodeBridge) {
			ActivityNodeBridge o = (ActivityNodeBridge) other;
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
