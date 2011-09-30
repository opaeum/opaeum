package org.opeum.metamodel.components;

import org.opeum.metamodel.core.INakedElement;

public interface INakedConnector extends INakedElement{
	INakedConnectorEnd getEnd1();
	INakedConnectorEnd getEnd2();
	NakedConnectorKind getKind();
}
