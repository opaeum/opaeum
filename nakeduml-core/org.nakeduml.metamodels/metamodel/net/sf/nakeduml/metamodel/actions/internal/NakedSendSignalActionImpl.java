package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;

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
