package net.sf.nakeduml.metamodel.bpm;

import java.util.Collection;

import net.sf.nakeduml.metamodel.components.INakedComponent;

public interface INakedBusinessComponent extends INakedComponent{
	Collection<INakedBusinessService> getProvidedBusinessServices();
}
