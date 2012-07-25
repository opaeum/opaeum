package org.opaeum.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedConnectorEnd;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedMultiplicity;
import org.eclipse.uml2.uml.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;

public class AssociationClassToEnd extends AbstractEmulatedProperty{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6946143562782045457L;
	private INakedProperty property;
	private INakedMultiplicity multiplicity;
	public AssociationClassToEnd(INakedProperty property){
		super((INakedClassifier) property.getAssociation(), checkBaseType(property));
		this.property = property;
		this.multiplicity = NakedMultiplicityImpl.ONE_ONE;
		this.id = ((INakedElement) property.getAssociation()).getId() + property.getId();
		this.mappingInfo = checkBaseType(property).getMappingInfo().getCopy();
		this.mappingInfo.setIdInModel(id);
		isComposite=property.getAssociation().getOtherEnd(property).isComposite();
	}
	private static INakedClassifier checkBaseType(INakedProperty property){
		if(property.getBaseType()==null){
			System.out.println("BaseType null:" + property.getPathName());
		}
		if(property.getOtherEnd().getBaseType()==null){
			System.out.println("Other BaseType null:" + property.getPathName());
		}
		return property.getOtherEnd().getNakedBaseType();
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
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
	@Override
	public boolean isMeasure(){
		return false;
	}
	@Override
	public boolean isDimension(){
		return true;
	}
}
