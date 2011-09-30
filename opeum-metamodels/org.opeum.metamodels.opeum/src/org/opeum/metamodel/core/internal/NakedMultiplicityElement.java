package org.opeum.metamodel.core.internal;

import org.opeum.metamodel.core.INakedMultiplicity;
import org.opeum.metamodel.core.INakedMultiplicityElement;
import nl.klasse.octopus.model.IMultiplicityKind;

public class NakedMultiplicityElement extends NakedElementImpl implements INakedMultiplicityElement{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3598191743169166481L;
	private boolean ordered;
	private boolean unique;
	private INakedMultiplicity multiplicity;

	public boolean isUnique() {
		return unique;
	}

	public void setIsUnique(boolean unique) {
		this.unique = unique;
	}

	public INakedMultiplicity getNakedMultiplicity() {
		return multiplicity;
	}
	public IMultiplicityKind getMultiplicity() {
		return getNakedMultiplicity();
	}


	public boolean isOrdered() {
		return ordered;
	}

	public void setIsOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	public void setMultiplicity(INakedMultiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	@Override
	public String getMetaClass(){
		return "multiplicityElement";
	}

}
