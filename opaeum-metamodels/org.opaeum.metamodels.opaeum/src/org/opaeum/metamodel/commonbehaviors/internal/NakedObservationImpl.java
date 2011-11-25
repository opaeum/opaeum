package org.opaeum.metamodel.commonbehaviors.internal;

import nl.klasse.octopus.model.IMultiplicityKind;

import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.NakedTypedElementImpl;

public class NakedObservationImpl extends NakedTypedElementImpl{
	@Override
	public INakedMultiplicity getNakedMultiplicity(){
		return (INakedMultiplicity) NakedMultiplicityImpl.ZERO_ONE;
	}
	@Override
	public IMultiplicityKind getMultiplicity(){
		// TODO Auto-generated method stub
		return super.getNakedMultiplicity();
	}
	private static final long serialVersionUID = 5768089014751192131L;
}
