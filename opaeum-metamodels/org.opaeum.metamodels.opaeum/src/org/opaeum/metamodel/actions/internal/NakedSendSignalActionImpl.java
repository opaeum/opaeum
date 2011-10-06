package org.opaeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;

public class NakedSendSignalActionImpl extends NakedInvocationActionImpl implements INakedSendSignalAction{
	private static final long serialVersionUID = 3809690763786259025L;
	private INakedSignal signal;
	public void setSignal(INakedSignal signal){
		this.signal = signal;
	}
	public INakedSignal getSignal(){
		return this.signal;
	}
	public Collection<INakedOutputPin> getOutput() {
		return new HashSet<INakedOutputPin>();
	}
}
