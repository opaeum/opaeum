package org.opeum.metamodel.components;

import org.opeum.metamodel.core.INakedMultiplicityElement;
import org.opeum.metamodel.core.INakedProperty;

public interface INakedConnectorEnd extends INakedMultiplicityElement{
	INakedProperty getPartWithPort();
	INakedProperty getRole();
}
