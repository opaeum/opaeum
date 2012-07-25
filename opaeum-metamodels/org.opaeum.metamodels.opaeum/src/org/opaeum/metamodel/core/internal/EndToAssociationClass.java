package org.opaeum.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.eclipse.uml2.uml.INakedAssociation;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedConnectorEnd;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedMultiplicity;
import org.eclipse.uml2.uml.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.name.NameConverter;

public class EndToAssociationClass extends AbstractEmulatedProperty{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6359772681105953184L;
	private INakedProperty property;
	public EndToAssociationClass(INakedProperty property){
		super(property.getOtherEnd().getNakedBaseType(), (INakedClassifier) property.getAssociation());
		this.property = property;
		INakedAssociation association = (INakedAssociation) property.getAssociation();
		this.id = property.getId() + association.getId();
		this.mappingInfo.setIdInModel(id);
	}
	@Override
	public boolean isNavigable(){
		return property.isNavigable();
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		return Collections.emptySet();
	}
	@Override
	public boolean isInverse(){
		return true;
	}
	@Override
	public boolean isComposite(){
		return true;
	}
	public String getName(){
		return NameConverter.decapitalize(property.getAssociation().getName()) + "_" + property.getName();
	}
	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}
	@Override
	public void setType(IClassifier type){
		this.type = type;
	}
	@Override
	public IClassifier getType(){
		return type;
	}
	@Override
	public boolean isOrdered(){
		return property.isOrdered();
	}
	@Override
	public boolean isUnique(){
		return property.isUnique();
	}
	@Override
	public INakedMultiplicity getNakedMultiplicity(){
		return (INakedMultiplicity) property.getMultiplicity();
	}
	@Override
	public INakedClassifier getNakedBaseType(){
		return (INakedClassifier) property.getAssociation();
	}
	public int getIndexInAssocation(){
		boolean b = property.getAssociation().getEnd1() == property;
		return b ? 0 : 1;
	}
	@Override
	public boolean isMeasure(){
		return false;
	}
	@Override
	public boolean isDimension(){
		return false;
	}
}
