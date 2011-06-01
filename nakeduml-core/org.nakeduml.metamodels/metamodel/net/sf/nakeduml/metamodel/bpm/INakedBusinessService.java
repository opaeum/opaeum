package net.sf.nakeduml.metamodel.bpm;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedInterface;

public interface INakedBusinessService extends INakedInterface{
	Collection<INakedResponsibility> getResponsibilities();
}
