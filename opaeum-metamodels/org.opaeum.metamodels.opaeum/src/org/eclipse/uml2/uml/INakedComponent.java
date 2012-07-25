package org.eclipse.uml2.uml;

import java.util.Collection;


public interface INakedComponent extends INakedNameSpace,INakedClassifier,ICompositionParticipant,INakedComplexStructure{
	boolean isOrganizationUnit();
	boolean isSchema();
	Collection<INakedPort> getOwnedPorts();
	Collection<INakedPort> getEffectivePorts();
	Collection<INakedConnector> getOwnedConnectors();
}
