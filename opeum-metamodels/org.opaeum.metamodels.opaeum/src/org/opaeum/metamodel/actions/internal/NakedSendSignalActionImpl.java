package org.opeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import org.opeum.metamodel.actions.INakedSendSignalAction;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.commonbehaviors.INakedSignal;

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
