package org.opaeum.metamodel.bpm;

import java.util.Collection;

import org.eclipse.uml2.uml.INakedComponent;

public interface INakedBusinessComponent extends INakedComponent{
	Collection<INakedBusinessService> getProvidedBusinessServices();
	boolean isRoot();
	INakedBusinessRole getAdminRole();
}
