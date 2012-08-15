package org.opaeum.metamodel.core.internal;

import nl.klasse.octopus.model.IMultiplicityKind;

import org.eclipse.uml2.uml.INakedExpansionNode;
import org.eclipse.uml2.uml.INakedMultiplicity;
import org.eclipse.uml2.uml.INakedMultiplicityElement;

public class NakedMultiplicityElement extends NakedElementImpl implements INakedMultiplicityElement{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3598191743169166481L;
	private boolean ordered;
	private boolean unique;
	private INakedMultiplicity multiplicity;
	public boolean isUnique(){
		return unique;
	}
	public boolean fitsInTo(INakedMultiplicityElement other){
		return fitsInto(this, other);
	}
	public static boolean fitsInto(INakedMultiplicityElement from,INakedMultiplicityElement into){
		if(from.getNakedMultiplicity().isSingleObject()){
			return true;
		}else if(into.isOrdered() && !from.isOrdered()){
			return false;
		}else if(into.isUnique() && !from.isUnique()){
			return false;
		}else{
			return from.getNakedMultiplicity().getUpper() <= into.getNakedMultiplicity().getUpper();
		}
	}
	public void setIsUnique(boolean unique){
		this.unique = unique;
	}
	public INakedMultiplicity getNakedMultiplicity(){
		return multiplicity;
	}
	public IMultiplicityKind getMultiplicity(){
		return getNakedMultiplicity();
	}
	public boolean isOrdered(){
		return ordered;
	}
	public void setIsOrdered(boolean ordered){
		this.ordered = ordered;
	}
	public void setMultiplicity(INakedMultiplicity multiplicity){
		this.multiplicity = multiplicity;
	}
	@Override
	public String getMetaClass(){
		return "multiplicityElement";
	}
}
