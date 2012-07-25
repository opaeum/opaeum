package org.eclipse.uml2.uml;


public interface INakedConnectorEnd extends INakedMultiplicityElement{
	INakedProperty getPartWithPort();
	INakedProperty getRole();
}
