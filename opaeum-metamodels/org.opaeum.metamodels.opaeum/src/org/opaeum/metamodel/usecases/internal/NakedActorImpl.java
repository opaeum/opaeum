package org.opaeum.metamodel.usecases.internal;

import org.opaeum.metamodel.commonbehaviors.internal.NakedBehavioredClassifierImpl;
import org.opaeum.metamodel.usecases.INakedActor;

public class NakedActorImpl extends NakedBehavioredClassifierImpl implements INakedActor{
	private static final long serialVersionUID = -8856760453841246918L;
	public NakedActorImpl(){
		
	}
	@Override
	public String getMetaClass() {
		return "actor";
	}
	@Override
	public boolean isPersistent(){
		return true;
	}
}
