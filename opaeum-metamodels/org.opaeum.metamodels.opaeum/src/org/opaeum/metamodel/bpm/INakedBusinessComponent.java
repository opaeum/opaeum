package org.opaeum.metamodel.bpm;

import java.util.Collection;

import org.opaeum.metamodel.components.INakedComponent;

public interface INakedBusinessComponent extends INakedComponent{
	Collection<INakedBusinessService> getProvidedBusinessServices();
	boolean isRoot();
	INakedBusinessRole getAdminRole();
}
