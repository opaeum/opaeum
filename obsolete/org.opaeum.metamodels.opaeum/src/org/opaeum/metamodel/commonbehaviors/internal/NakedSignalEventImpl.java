package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.List;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedSignal;
import org.eclipse.uml2.uml.INakedSignalEvent;
import org.eclipse.uml2.uml.INakedTypedElement;

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
