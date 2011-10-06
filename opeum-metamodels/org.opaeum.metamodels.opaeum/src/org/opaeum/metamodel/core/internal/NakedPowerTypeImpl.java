package org.opeum.metamodel.core.internal;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IEnumLiteral;

import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedPowerType;
import org.opeum.metamodel.core.INakedPowerTypeInstance;

/**
 * Somewhere between a generalization set and a powertype, represented as a kind of enumeration. The generalization set is assumed to be all
 * the generalizations that have the representedSupertype as parent - directly or indirectly. The PowerTypeInstances (enum literals) of a
 * powertype This type of classifier is ideal for use as the type of a discriminator field in hibernate.
 */
public class NakedPowerTypeImpl extends NakedEnumerationImpl implements INakedPowerType{
	private static final long serialVersionUID = -375810033999646853L;
	INakedClassifier representedSupertype;
	public INakedClassifier getRepresentedSupertype(){
		return this.representedSupertype;
	}
	public void setRepresentedSupertype(INakedClassifier representedSupertype){
		this.representedSupertype = representedSupertype;
		representedSupertype.setPowerType(this);
	}
	public boolean isPowerType(){
		return true;
	}
	public INakedPowerTypeInstance getLiteralForSubtype(IClassifier element){
		for(IEnumLiteral l:getLiterals()){
			INakedPowerTypeInstance el = (INakedPowerTypeInstance) l;
			if(el.getRepresentedGeneralization().getSpecific().equals(element)){
				return el;
			}
		}
		return null;
	}
}
