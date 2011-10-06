package org.opeum.metamodel.components;

import java.util.Collection;

import org.opeum.metamodel.core.ICompositionParticipant;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedNameSpace;

public interface INakedComponent extends INakedNameSpace,INakedClassifier,ICompositionParticipant,INakedComplexStructure{
	boolean isOrganizationUnit();
	Collection<INakedPort> getOwnedPorts();
	Collection<INakedPort> getEffectivePorts();
	Collection<INakedConnector> getOwnedConnectors();
}
