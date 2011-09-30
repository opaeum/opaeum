package org.opeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.opeum.metamodel.actions.IActionWithTargetElement;
import org.opeum.metamodel.actions.ITargetElement;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.activities.internal.NakedActionImpl;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedProperty;

public abstract class NakedStructuralFeatureActionImpl extends NakedActionImpl implements IActionWithTargetElement{
	private static final long serialVersionUID = 3165514874679324190L;
	public INakedProperty feature;
	public INakedInputPin object;
	public Set<INakedInputPin> getInput(){
		Set<INakedInputPin> results = new HashSet<INakedInputPin>();
		if(object != null){
			results.add(object);
		}
		return results;
	}
	public Collection<INakedOutputPin> getOutput(){
		return new HashSet<INakedOutputPin>();
	}
	public INakedClassifier getExpectedTargetType(){
		if(feature == null){
			return null;
		}else{
			return feature.getOwner();
		}
	}
	public ITargetElement getTargetElement(){
		if(getTarget() == null){
			return getInPartition();
		}else{
			return getTarget();
		}
	}
	public INakedProperty getFeature(){
		return this.feature;
	}
	public void setFeature(INakedProperty feature){
		this.feature = feature;
	}
	public INakedInputPin getObject(){
		return this.object;
	}
	public void setObject(INakedInputPin object){
		if(this.object != object){
			removeOwnedElement(this.object, true);
			this.object = object;
		}
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		Collection<INakedElement> result = super.getOwnedElements();
		if(this.object != null){
			result.add(this.object);
		}
		return result;
	}
	public INakedInputPin getTarget(){
		return getObject();
	}
}