package net.sf.nakeduml.metamodel.usecases.internal;

import net.sf.nakeduml.metamodel.core.internal.NakedClassifierImpl;
import net.sf.nakeduml.metamodel.usecases.INakedActor;

public class NakedActorImpl extends NakedClassifierImpl implements INakedActor{
	private static final long serialVersionUID = -8856760453841246918L;
	@Override
	public String getMetaClass() {
		return "actor";
	}
}
