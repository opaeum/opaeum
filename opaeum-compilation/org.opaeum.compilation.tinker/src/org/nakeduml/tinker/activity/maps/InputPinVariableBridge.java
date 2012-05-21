package org.nakeduml.tinker.activity.maps;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.feature.MappingInfo;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.name.NameConverter;

public class InputPinVariableBridge extends AbstractEmulatedProperty implements INakedProperty {
	private static final long serialVersionUID = 211415204864858873L;
	protected INakedPin pin;
	private boolean inverse = false;
	private boolean unique;

	public InputPinVariableBridge(INakedClassifier owner, INakedPin pin, boolean inverse) {
		super(owner, pin);
		this.pin = pin;
		this.inverse = inverse;
	}
	
	public boolean isUnique() {
		return this.unique;
	}
	
	public void setIsUnique(boolean unique){
		this.unique = unique;
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
		return pin.getNakedBaseType();
	}

	public boolean isOrdered() {
		return false;
	}

	public INakedMultiplicityElement getOriginal() {
		return null;
	}

	public IClassifier getType() {
		return pin.getType();
	}

	@Override
	public String getName() {
		return NameConverter.capitalize(this.pin.getName());
	}

	public INakedMultiplicity getNakedMultiplicity() {
		return pin.getNakedMultiplicity();
	}

	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd() {
		return Collections.emptySet();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof InputPinVariableBridge) {
			InputPinVariableBridge o = (InputPinVariableBridge) other;
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
