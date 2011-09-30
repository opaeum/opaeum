package org.opeum.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import org.opeum.metamodel.components.INakedConnectorEnd;
import org.opeum.metamodel.core.INakedAssociation;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedMultiplicity;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.internal.emulated.AbstractPropertyBridge;
import nl.klasse.octopus.model.IClassifier;

import org.opeum.name.NameConverter;

public class EndToAssociationClass extends AbstractPropertyBridge{
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
	public Collection<? extends INakedElement> getOwnedElements(){
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
}
