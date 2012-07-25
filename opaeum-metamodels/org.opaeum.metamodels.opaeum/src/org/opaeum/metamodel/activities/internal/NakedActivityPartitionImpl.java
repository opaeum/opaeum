package org.opaeum.metamodel.activities.internal;

import nl.klasse.octopus.model.IClassifier;

import org.eclipse.uml2.uml.INakedActivityPartition;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedMultiplicity;
import org.eclipse.uml2.uml.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedMultiplicityElement;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;

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
	@Override
	public boolean isMeasure(){
		return false;
	}
	@Override
	public boolean isDimension(){
		// TODO Auto-generated method stub
		return true;
	}
	
}
