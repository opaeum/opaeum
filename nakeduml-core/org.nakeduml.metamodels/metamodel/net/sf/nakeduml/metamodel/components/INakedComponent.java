package net.sf.nakeduml.metamodel.components;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;

public interface INakedComponent extends INakedNameSpace,INakedClassifier{
	boolean isOrganizationUnit();
	Collection<INakedPort> getOwnedPorts();
	Collection<INakedPort> getEffectivePorts();
	Collection<INakedConnector> getOwnedConnectors();
}
