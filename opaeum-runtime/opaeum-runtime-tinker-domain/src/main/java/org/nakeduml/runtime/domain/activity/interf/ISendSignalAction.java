package org.nakeduml.runtime.domain.activity.interf;

import org.nakeduml.runtime.domain.activity.SingleObjectToken;
import org.opaeum.runtime.domain.ISignal;

public interface ISendSignalAction extends IInvocationAction {
	ISignal getSignal();
	IInputPin<?, ? extends SingleObjectToken<?>> getTarget();
}
