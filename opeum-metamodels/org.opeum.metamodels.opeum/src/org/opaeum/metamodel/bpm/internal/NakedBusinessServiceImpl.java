package org.opeum.metamodel.bpm.internal;

import java.util.Collection;

import org.opeum.metamodel.bpm.INakedBusinessService;
import org.opeum.metamodel.bpm.INakedResponsibility;
import org.opeum.metamodel.core.internal.NakedInterfaceImpl;

public class NakedBusinessServiceImpl extends NakedInterfaceImpl implements INakedBusinessService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1267712657060062671L;

	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	@Override
	public Collection<INakedResponsibility> getResponsibilities(){
		return (Collection)getEffectiveOperations();
	}
}
