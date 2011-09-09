package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignalEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;

public class NakedSignalEventImpl extends NakedEventImpl implements INakedSignalEvent{
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
