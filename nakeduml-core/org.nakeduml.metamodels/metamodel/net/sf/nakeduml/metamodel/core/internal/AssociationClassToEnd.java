package net.sf.nakeduml.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import org.nakeduml.name.NameConverter;

import net.sf.nakeduml.feature.MappingInfo;
import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.AbstractPropertyBridge;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;

public class AssociationClassToEnd extends AbstractPropertyBridge{
	private INakedProperty property;
	private INakedMultiplicity multiplicity;
	public AssociationClassToEnd(INakedProperty property){
		super((INakedClassifier) property.getAssociation(), property.getOtherEnd().getNakedBaseType());
		this.property = property;
		this.multiplicity = NakedMultiplicityImpl.ONE_ONE;
		this.id = property.getAssociation() + property.getId();
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
