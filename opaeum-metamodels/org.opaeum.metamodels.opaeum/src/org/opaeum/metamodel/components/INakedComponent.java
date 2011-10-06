package org.opaeum.metamodel.components;

import java.util.Collection;

import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedNameSpace;

public interface INakedComponent extends INakedNameSpace,INakedClassifier,ICompositionParticipant,INakedComplexStructure{
	boolean isOrganizationUnit();
	Collection<INakedPort> getOwnedPorts();
	Collection<INakedPort> getEffectivePorts();
	Collection<INakedConnector> getOwnedConnectors();
}
