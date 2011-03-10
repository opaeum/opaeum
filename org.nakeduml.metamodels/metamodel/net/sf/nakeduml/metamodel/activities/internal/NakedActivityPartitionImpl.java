package net.sf.nakeduml.metamodel.activities.internal;

import net.sf.nakeduml.metamodel.activities.INakedActivityPartition;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedModelElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import nl.klasse.octopus.model.IClassifier;

public class NakedActivityPartitionImpl extends NakedModelElementImpl implements INakedActivityPartition{
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
