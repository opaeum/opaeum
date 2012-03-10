package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.List;

import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedTypedElement;

public class NakedSignalEventImpl extends NakedEventImpl implements INakedSignalEvent{
	private static final long serialVersionUID = 2719164087299253327L;
	INakedSignal signal;
	
	public NakedSignalEventImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
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
