package org.opaeum.metamodel.usecases.internal;

import org.opaeum.metamodel.core.internal.NakedClassifierImpl;
import org.opaeum.metamodel.usecases.INakedActor;

public class NakedActorImpl extends NakedClassifierImpl implements INakedActor{
	private static final long serialVersionUID = -8856760453841246918L;
	@Override
	public String getMetaClass() {
		return "actor";
	}
}
