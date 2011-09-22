package net.sf.nakeduml.metamodel.bpm.internal;

import java.util.Collection;

import net.sf.nakeduml.metamodel.bpm.INakedBusinessService;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.core.internal.NakedInterfaceImpl;

public class NakedBusinessServiceImpl extends NakedInterfaceImpl implements INakedBusinessService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1267712657060062671L;

	@Override
	public Collection<INakedResponsibility> getResponsibilities(){
		return (Collection)getEffectiveOperations();
	}
}
