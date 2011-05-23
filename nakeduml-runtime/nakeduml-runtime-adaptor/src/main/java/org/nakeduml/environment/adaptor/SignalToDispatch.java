package org.nakeduml.environment.adaptor;

import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.ActiveObject;

public class SignalToDispatch extends org.nakeduml.environment.SignalToDispatch{
	public SignalToDispatch(){
		super();
	}
	public SignalToDispatch(Object source,ActiveObject target,AbstractSignal signal){
		super(source, target, signal);
	}
}
