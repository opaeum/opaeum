package org.opeum.metamodel.core.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedInstanceSpecification;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSlot;
import org.opeum.metamodel.core.INakedValueSpecification;

public class NakedSlotImpl extends NakedElementImpl implements INakedSlot,Serializable{
	private static final long serialVersionUID = 3768032110306457324L;
	private INakedProperty definingFeature;
	private List<INakedValueSpecification> values = new ArrayList<INakedValueSpecification>();
	@Override
	public String getMetaClass(){
		return "slot";
	}
	public INakedInstanceSpecification getOwningInstance(){
		return (INakedInstanceSpecification) getOwnerElement();
	}
	public INakedProperty getDefiningFeature(){
		return this.definingFeature;
	}
	public void setDefiningFeature(INakedProperty definingFeature){
		this.definingFeature = definingFeature;
		super.setName(this.definingFeature.getName());
	}
	public INakedValueSpecification getFirstValue(){
		if(this.values.size() == 1){
			return this.values.get(0);
		}else{
			return null;
		}
	}
	public List<INakedValueSpecification> getValues(){
		return this.values;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		this.values.add((INakedValueSpecification) element);
	}
}
