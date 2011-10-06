package org.opaeum.metamodel.components;

import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedProperty;

public interface INakedConnectorEnd extends INakedMultiplicityElement{
	INakedProperty getPartWithPort();
	INakedProperty getRole();
}
