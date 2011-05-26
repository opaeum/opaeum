package net.sf.nakeduml.metamodel.components;

import net.sf.nakeduml.metamodel.core.INakedElement;

public interface INakedConnector extends INakedElement{
	INakedConnectorEnd getEnd1();
	INakedConnectorEnd getEnd2();
	NakedConnectorKind getKind();
}
