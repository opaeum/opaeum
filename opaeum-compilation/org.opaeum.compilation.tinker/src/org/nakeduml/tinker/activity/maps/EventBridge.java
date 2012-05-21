package org.nakeduml.tinker.activity.maps;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.name.NameConverter;

public class EventBridge extends AbstractEmulatedProperty implements INakedProperty{
	/**
	 * 
	 */
	private static final long serialVersionUID = 211415204864858873L;
	protected INakedEvent event;
	boolean ensureLocallyUniqueName = true;

	public EventBridge(INakedClassifier owner,INakedEvent event){
		super(owner, event);
		this.event = event;
		ensureLocallyUniqueName=false;
	}

	@Override
	public boolean isDerived(){
		return super.isDerived();
	}
	public boolean shouldEnsureLocallyUniqueName(){
		return this.ensureLocallyUniqueName;
	}
	public INakedClassifier getNakedBaseType(){
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.event.getNameSpace(), this.event);
		return jippo;
	}
	public boolean isOrdered(){
		return false;
	}
	public boolean isUnique(){
		return false;
	}
	public INakedMultiplicityElement getOriginal(){
		return null;
	}
	public IClassifier getType(){
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.event.getNameSpace(), this.event);
		return jippo;
	}
	@Override
	public String getName(){
		return NameConverter.decapitalize(this.event.getName());
	}
	public INakedMultiplicity getNakedMultiplicity(){
		return new NakedMultiplicityImpl(1, 1);
	}
	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}
	@Override
	public boolean equals(Object other){
		if(other instanceof EventBridge){
			EventBridge o = (EventBridge) other;
			return o == this || (o.getId().equals(getId()) && o.shouldEnsureLocallyUniqueName() == shouldEnsureLocallyUniqueName());
		}else{
			return false;
		}
	}
	@Override
	public boolean isMeasure(){
		return false;
	}
	@Override
	public boolean isDimension(){
		return false;
	}
}
