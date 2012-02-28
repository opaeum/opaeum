package org.opaeum.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.OclExpression;

import org.opaeum.feature.MappingInfo;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;

public class NonInverseArtificialProperty extends AbstractEmulatedProperty implements IVariableDeclaration{
	private static final long serialVersionUID = 1L;
	private INakedClassifier baseType;
	private String name;
	private boolean isDerived;
	@Override
	public void setName(String umlName){
		this.name = umlName;
	}
	public NonInverseArtificialProperty(INakedProperty otherEnd,String name){
		super(otherEnd.getNakedBaseType(), otherEnd.getOwner());
		this.otherEnd = otherEnd;
		otherEnd.setOtherEnd(this);
		this.name = name;
		this.type = otherEnd.getOwner();
		this.baseType = otherEnd.getOwner();
		this.mappingInfo = (MappingInfo) otherEnd.getOwner().getMappingInfo().getCopy();
		this.mappingInfo.setPersistentName(null);
	}
	public NonInverseArtificialProperty(String string,INakedClassifier c,boolean derived){
		super(c, c);
		baseType = c;
		type = c;
		name = string;
		this.isDerived = derived;
		id=string+c.getId();
		mappingInfo.setIdInModel(id);
			
	}
	@Override
	public boolean isInverse(){
		return false;
	}
	@Override
	public INakedMultiplicity getNakedMultiplicity(){
		return NakedMultiplicityImpl.ONE_ONE;
	}
	@Override
	public INakedClassifier getNakedBaseType(){
		return baseType;
	}
	public String getName(){
		return name;
	}
	@Override
	public boolean isOrdered(){
		return false;
	}
	@Override
	public boolean isUnique(){
		return false;
	}
	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}
	public INakedClassifier getBaseType(){
		return baseType;
	}
	public NakedMultiplicityImpl getMultiplicity(){
		return NakedMultiplicityImpl.ONE_ONE;
	}
	public INakedProperty getOtherEnd(){
		return otherEnd;
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		return Collections.emptySet();
	}
	@Override
	public boolean equals(Object other){
		if(other == this){
			return true;
		}else if(other instanceof NonInverseArtificialProperty){
			NonInverseArtificialProperty ap = (NonInverseArtificialProperty) other;
			return ap.originalElement.equals(originalElement) && ap.owner.equals(owner) && ap.name.equals(name);
		}else{
			return super.equals(other);
		}
	}
	@Override
	public OclExpression getInitExpression(){
		return null;
	}
	@Override
	public boolean isIteratorVar(){
		return false;
	}
	public boolean isDerived(){
		return isDerived;
	}
}
