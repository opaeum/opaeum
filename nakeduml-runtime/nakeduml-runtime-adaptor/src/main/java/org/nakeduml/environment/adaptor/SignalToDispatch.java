package org.nakeduml.environment.adaptor;

import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.IActiveObject;

public class SignalToDispatch extends org.nakeduml.environment.SignalToDispatch{
	public SignalToDispatch(){
		super();
	}
	public SignalToDispatch(Object source,IActiveObject target,AbstractSignal signal){
		super(source, target, signal);
	}
}
