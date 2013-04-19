package org.eclipse.uml2.uml;


public interface INakedConnector extends INakedElement{
	INakedConnectorEnd getEnd1();
	INakedConnectorEnd getEnd2();
	NakedConnectorKind getKind();
}
