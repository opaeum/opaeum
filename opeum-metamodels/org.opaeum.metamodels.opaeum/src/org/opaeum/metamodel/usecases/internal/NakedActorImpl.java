package org.opeum.metamodel.usecases.internal;

import org.opeum.metamodel.core.internal.NakedClassifierImpl;
import org.opeum.metamodel.usecases.INakedActor;

public class NakedActorImpl extends NakedClassifierImpl implements INakedActor{
	private static final long serialVersionUID = -8856760453841246918L;
	@Override
	public String getMetaClass() {
		return "actor";
	}
}
