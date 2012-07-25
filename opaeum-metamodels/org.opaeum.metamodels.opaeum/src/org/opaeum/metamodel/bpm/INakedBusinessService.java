package org.opaeum.metamodel.bpm;

import java.util.Collection;

import org.eclipse.uml2.uml.INakedInterface;

public interface INakedBusinessService extends INakedInterface{
	Collection<INakedResponsibility> getResponsibilities();
}
