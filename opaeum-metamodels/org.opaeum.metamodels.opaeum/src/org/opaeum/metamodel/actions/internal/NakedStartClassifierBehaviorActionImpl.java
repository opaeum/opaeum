package org.opaeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import org.opaeum.metamodel.actions.INakedStartClassifierBehaviorAction;
import org.opaeum.metamodel.actions.ITargetElement;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.internal.NakedActionImpl;
import org.opaeum.metamodel.core.INakedClassifier;

public class NakedStartClassifierBehaviorActionImpl extends NakedActionImpl implements INakedStartClassifierBehaviorAction{
	private static final long serialVersionUID = -236758123440467617L;
	private INakedInputPin object;
	public INakedInputPin getObject(){
		return object;
	}
	public void setObject(INakedInputPin object){
		if(this.object != object){
			this.replacePin(this.object, object);
			this.object = object;
		}
	}
	public Collection<INakedOutputPin> getOutput(){
		return new HashSet<INakedOutputPin>();
	}
	@Override
	public ITargetElement getTargetElement(){
		if(getObject() != null){
			return getObject();
		}else{
			return getInPartition();
		}
	}
	@Override
	public Collection<INakedInputPin> getInput(){
		Collection<INakedInputPin> result = new HashSet<INakedInputPin>();
		if(getObject() != null){
			result.add(getObject());
		}
		return result;
	}
	@Override
	public INakedInputPin getTarget(){
		return getObject();
	}
	@Override
	public INakedClassifier getExpectedTargetType(){
		// TODO Auto-generated method stub
		return null;
	}
}
