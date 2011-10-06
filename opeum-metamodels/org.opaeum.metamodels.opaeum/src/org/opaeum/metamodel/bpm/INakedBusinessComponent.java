package org.opeum.metamodel.bpm;

import java.util.Collection;

import org.opeum.metamodel.components.INakedComponent;

public interface INakedBusinessComponent extends INakedComponent{
	Collection<INakedBusinessService> getProvidedBusinessServices();
}
