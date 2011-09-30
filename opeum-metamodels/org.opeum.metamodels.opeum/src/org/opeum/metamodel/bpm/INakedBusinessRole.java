package org.opeum.metamodel.bpm;

import java.util.Collection;

import org.opeum.metamodel.core.INakedEntity;

public interface INakedBusinessRole extends INakedEntity{
	Collection<INakedBusinessService> getServicesProvided();
}
