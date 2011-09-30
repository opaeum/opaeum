package org.opeum.metamodel.bpm;

import java.util.Collection;

import org.opeum.metamodel.core.INakedInterface;

public interface INakedBusinessService extends INakedInterface{
	Collection<INakedResponsibility> getResponsibilities();
}
