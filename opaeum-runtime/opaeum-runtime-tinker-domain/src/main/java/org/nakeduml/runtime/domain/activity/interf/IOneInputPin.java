package org.nakeduml.runtime.domain.activity.interf;

import org.nakeduml.runtime.domain.activity.SingleObjectToken;

public interface IOneInputPin<O> extends IInputPin<O, SingleObjectToken<O>>, IOnePin<O> {
	
}
