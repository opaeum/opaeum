package org.opaeum.metamodel.bpm;

import java.util.Collection;

import org.opaeum.metamodel.core.INakedEntity;

public interface INakedBusinessRole extends INakedEntity{
	Collection<INakedBusinessService> getServicesProvided();
}
