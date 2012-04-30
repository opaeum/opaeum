package org.nakeduml.tinker.activity;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.name.NameConverter;

public class PinBridge extends AbstractEmulatedProperty implements INakedProperty{
	/**
	 * 
	 */
	private static final long serialVersionUID = 211415204864858873L;
	protected INakedPin pin;
	boolean ensureLocallyUniqueName = true;

	public PinBridge(INakedClassifier owner,INakedPin pin){
		super(owner, pin);
		this.pin = pin;
		ensureLocallyUniqueName=true;
	}

	@Override
	public boolean isDerived(){
		return super.isDerived();
	}
	public boolean shouldEnsureLocallyUniqueName(){
		return this.ensureLocallyUniqueName;
	}
	public INakedClassifier getNakedBaseType(){
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.pin.getNameSpace(), this.pin);
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
		ConcreteEmulatedClassifier jippo = new ConcreteEmulatedClassifier(this.pin.getNameSpace(), this.pin);
		return jippo;
	}
	@Override
	public String getName(){
		return locallyUniqueName((INakedObjectNode) this.pin);
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
		if(other instanceof PinBridge){
			PinBridge o = (PinBridge) other;
			return o == this || (o.getId().equals(getId()) && o.shouldEnsureLocallyUniqueName() == shouldEnsureLocallyUniqueName());
		}else{
			return false;
		}
	}
	public static String locallyUniqueName(INakedObjectNode pin){
		return pin.getOwnerElement().getMappingInfo().getJavaName().getCapped() + NameConverter.capitalize(pin.getName());
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
