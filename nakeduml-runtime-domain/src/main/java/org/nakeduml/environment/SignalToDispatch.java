package org.nakeduml.environment;

import java.io.Serializable;

import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

public class SignalToDispatch implements Serializable {
	private static final long serialVersionUID = 7738510016359598668L;
	protected AbstractSignal signal;
	protected Object source;
	protected ActiveObject target;

	public SignalToDispatch(Object source, ActiveObject target, AbstractSignal signal) {
		super();
		this.source = source;
		this.target = target;
		this.signal = signal;
	}

	public AbstractSignal getSignal() {
		return signal;
	}

	public Object getSource() {
		return source;
	}

	public ActiveObject getTarget() {
		return target;
	}

}
