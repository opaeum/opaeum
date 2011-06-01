package net.sf.nakeduml.metamodel.bpm;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedEntity;

public interface INakedUserInRole extends INakedEntity{
	Collection<INakedBusinessService> getServicesProvided();
}
