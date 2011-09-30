package org.opeum.metamodel.activities.internal;

import nl.klasse.octopus.model.IClassifier;

import org.opeum.metamodel.activities.INakedActivityPartition;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedInstanceSpecification;
import org.opeum.metamodel.core.INakedMultiplicity;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.internal.NakedMultiplicityElement;
import org.opeum.metamodel.core.internal.NakedMultiplicityImpl;

public class NakedActivityPartitionImpl extends NakedMultiplicityElement implements INakedActivityPartition{
	private static final long serialVersionUID = -2640734629787128086L;
	private INakedElement represented;
	private INakedMultiplicity multiplicity = NakedMultiplicityImpl.ONE_ONE;
	private IClassifier type;
	@Override
	public String getMetaClass(){
		return "activityPartition";
	}
	public INakedElement getRepresents(){
		return this.represented;
	}
	public void setRepresents(INakedElement e){
		this.represented = e;
		if(e instanceof INakedProperty){
			this.multiplicity = ((INakedProperty) e).getNakedMultiplicity();
		}
	}

	public INakedMultiplicity getNakedMultiplicity(){
		return multiplicity;
	}
	public IClassifier getType(){
		return type;
	}
	public boolean isOrdered(){
		if(getRepresents() instanceof INakedProperty){
			return getProperty().isOrdered();
		}else{
			return true;
		}
	}
	public void setType(IClassifier type){
		this.type = type;
	}
	public INakedClassifier getNakedBaseType(){
		if(getRepresents() instanceof INakedClassifier){
			return (INakedClassifier) getRepresents();
		}else if(getRepresents() instanceof INakedProperty){
			return getProperty().getNakedBaseType();
		}else if(getRepresents() instanceof INakedInstanceSpecification){
			return ((INakedInstanceSpecification) getRepresents()).getClassifier();
		}else{
			return null;
		}
	}
	private INakedProperty getProperty(){
		return((INakedProperty) getRepresents());
	}
	public boolean isUnique(){
		if(getRepresents() instanceof INakedProperty){
			return getProperty().isUnique();
		}else{
			return true;
		}
	}
	public void setBaseType(INakedClassifier nakedPeer){
	}
	
}
