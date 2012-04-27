package org.nakeduml.runtime.domain.activity.interf;

import org.nakeduml.runtime.domain.activity.CollectionObjectToken;

public interface IManyOutputPin<O> extends IOutputPin<O, CollectionObjectToken<O>>, IManyPin<O> {
	
}
