package org.opaeum.metamodel.activities.internal;

import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.core.INakedMultiplicityElement;

public class NakedExpansionNodeImpl extends NakedObjectNodeImpl implements INakedExpansionNode {

	@Override
	public boolean canAcceptInputFrom(INakedMultiplicityElement from){
		if(isInputElement()){
			return from.getNakedMultiplicity().getUpper()>1;
		}else{
			return true;
		}
	}

	@Override
	public boolean canDeliverOutputTo(INakedMultiplicityElement to){
		if(isInputElement()){
			return true;
		}else{
			return to.getNakedMultiplicity().getUpper()>1;
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 2266933881560401012L;

	@Override
	public INakedExpansionRegion getExpansionRegion() {
		return (INakedExpansionRegion) getOwnerElement();
	}

	@Override
	public boolean isOutputElement() {
		return getExpansionRegion().getOutputElement().contains(this);
	}

	@Override
	public boolean isInputElement() {
		return getExpansionRegion().getInputElement().contains(this);
	}
	@Override
	public boolean fitsInTo(INakedMultiplicityElement other){
		
		return super.fitsInTo(other);
	}
}
