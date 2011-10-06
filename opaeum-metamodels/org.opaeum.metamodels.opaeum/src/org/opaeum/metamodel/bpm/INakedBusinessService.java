package org.opaeum.metamodel.bpm;

import java.util.Collection;

import org.opaeum.metamodel.core.INakedInterface;

public interface INakedBusinessService extends INakedInterface{
	Collection<INakedResponsibility> getResponsibilities();
}
