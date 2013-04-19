package org.opaeum.metamodel.bpm;

import java.util.Collection;

import org.eclipse.uml2.uml.INakedEntity;

public interface INakedBusinessRole extends INakedEntity{
	Collection<INakedBusinessService> getServicesProvided();
}
