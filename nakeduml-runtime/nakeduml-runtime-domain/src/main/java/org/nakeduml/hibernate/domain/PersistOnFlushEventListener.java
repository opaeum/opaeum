package org.nakeduml.hibernate.domain;

import org.hibernate.engine.CascadingAction;

public class PersistOnFlushEventListener extends PersistEventListener{

	@Override
	protected CascadingAction getCascadeAction(){
		return CascadingAction.PERSIST_ON_FLUSH;
	}
}
