package org.opeum.metamodel.commonbehaviors.internal;

import java.util.List;

import org.opeum.metamodel.commonbehaviors.INakedSignal;
import org.opeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedTypedElement;

public class NakedSignalEventImpl extends NakedEventImpl implements INakedSignalEvent{
	private static final long serialVersionUID = 2719164087299253327L;
	INakedSignal signal;
	public INakedSignal getSignal(){
		return signal;
	}
	public void setSignal(INakedSignal signal){
		this.signal = signal;
	}
	@Override
	public INakedClassifier getContext(){
		return null;
	}
	@Override
	public List<? extends INakedTypedElement> getEventParameters(){
		return getSignal().getEffectiveAttributes();
	}
	@Override
	public String getMetaClass(){
		return "signalEvent";
	}
}
