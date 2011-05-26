package net.sf.nakeduml.metamodel.components;

import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public interface INakedConnectorEnd extends INakedMultiplicityElement{
	INakedProperty getPartWithPort();
	INakedProperty getRole();
}
