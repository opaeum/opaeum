package net.sf.nakeduml.metamodel.bpm;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedEntity;

public interface INakedBusinessRole extends INakedEntity{
	Collection<INakedBusinessService> getServicesProvided();
}
