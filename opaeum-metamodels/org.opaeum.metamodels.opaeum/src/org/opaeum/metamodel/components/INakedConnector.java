package org.opaeum.metamodel.components;

import org.opaeum.metamodel.core.INakedElement;

public interface INakedConnector extends INakedElement{
	INakedConnectorEnd getEnd1();
	INakedConnectorEnd getEnd2();
	NakedConnectorKind getKind();
}
