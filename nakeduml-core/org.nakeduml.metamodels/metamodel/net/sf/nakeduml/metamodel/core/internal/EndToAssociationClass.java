package net.sf.nakeduml.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.AbstractPropertyBridge;
import nl.klasse.octopus.model.IClassifier;

import org.nakeduml.name.NameConverter;

public class EndToAssociationClass extends AbstractPropertyBridge{
	private INakedProperty property;
	public EndToAssociationClass(INakedProperty property){
		super(property.getOtherEnd().getNakedBaseType(),(INakedClassifier) property.getAssociation());
		this.property = property;
		INakedAssociation association = (INakedAssociation) property.getAssociation();
		this.id=property.getId() + association.getId();
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
		return  NameConverter.decapitalize(property.getAssociation().getName()) + "_" + property.getName();
	}
	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}
	@Override
	public void setType(IClassifier type){
		this.type=type;
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

}
