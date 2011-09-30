package org.opeum.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import org.opeum.metamodel.components.INakedConnectorEnd;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedMultiplicity;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.internal.emulated.AbstractPropertyBridge;
import nl.klasse.octopus.model.IClassifier;

public class AssociationClassToEnd extends AbstractPropertyBridge{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6946143562782045457L;
	private INakedProperty property;
	private INakedMultiplicity multiplicity;
	public AssociationClassToEnd(INakedProperty property){
		super((INakedClassifier) property.getAssociation(), property.getOtherEnd().getNakedBaseType());
		this.property = property;
		this.multiplicity = NakedMultiplicityImpl.ONE_ONE;
		this.id = ((INakedElement) property.getAssociation()).getId() + property.getId();
		this.mappingInfo = property.getOtherEnd().getNakedBaseType().getMappingInfo().getCopy();
		this.mappingInfo.setIdInModel(id);
	}
	@Override
	public Collection<? extends INakedElement> getOwnedElements(){
		return Collections.emptySet();
	}
	public INakedMultiplicity getMultiplicity(){
		return multiplicity;
	}
	@Override
	public String getName(){
		return property.getOtherEnd().getName();
	}
	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}
	@Override
	public IClassifier getType(){
		return property.getOtherEnd().getNakedBaseType();
	}
	@Override
	public boolean isOrdered(){
		return false;
	}
	@Override
	public boolean isUnique(){
		return true;
	}
	@Override
	public INakedMultiplicity getNakedMultiplicity(){
		return this.multiplicity;
	}
	@Override
	public INakedClassifier getNakedBaseType(){
		return property.getOtherEnd().getNakedBaseType();
	}
}
