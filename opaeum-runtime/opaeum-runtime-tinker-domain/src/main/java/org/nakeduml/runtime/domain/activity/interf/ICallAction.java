package org.nakeduml.runtime.domain.activity.interf;

import java.util.List;

public interface ICallAction extends IInvocationAction {
	boolean isSynchronous();
	List<? extends IOutputPin<?, ?>> getResult();
}
