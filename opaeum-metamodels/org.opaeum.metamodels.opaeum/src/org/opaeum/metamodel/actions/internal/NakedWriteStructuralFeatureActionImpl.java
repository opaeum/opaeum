package org.opaeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.Set;

import org.opaeum.metamodel.actions.INakedWriteStructuralFeatureAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedProperty;

public abstract class NakedWriteStructuralFeatureActionImpl extends NakedStructuralFeatureActionImpl implements INakedWriteStructuralFeatureAction{
	private static final long serialVersionUID = 3165514874679324190L;
	public INakedInputPin value;
	@Override
	public java.util.Set<INakedInputPin> getInput(){
		Set<INakedInputPin> results = super.getInput();
		if(value != null){
			results.add(value);
		}
		return results;
	};
	@Override
	public Collection<INakedElement> getOwnedElements(){
		Collection<INakedElement> result = super.getOwnedElements();
		if(this.value != null){
			result.add(this.value);
		}
		return result;
	}
	public INakedInputPin getValue(){
		return this.value;
	}
	public void setValue(INakedInputPin value){
		if(this.value != value){
			this.removeOwnedElement(this.value, true);
			this.value = value;
			linkFeature();
		}
	}
	private void linkFeature(){
		if(getValue() != null && getFeature() != null){
			getValue().setLinkedTypedElement(getFeature());
		}
	}
	@Override
	public void setFeature(INakedProperty feature){
		super.setFeature(feature);
		linkFeature();
	}
}
