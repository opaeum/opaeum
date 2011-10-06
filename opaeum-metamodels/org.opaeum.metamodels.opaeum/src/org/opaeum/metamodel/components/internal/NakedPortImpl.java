package org.opaeum.metamodel.components.internal;

import org.opaeum.metamodel.bpm.INakedBusinessRole;
import org.opaeum.metamodel.bpm.INakedBusinessService;
import org.opaeum.metamodel.components.INakedPort;
import org.opaeum.metamodel.core.internal.NakedPropertyImpl;

public class NakedPortImpl extends NakedPropertyImpl implements INakedPort{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1593895201835920222L;

	@Override
	public boolean isBusinessService(){
		if(getNakedBaseType() instanceof INakedBusinessService){
			return true;
		}else if(getNakedBaseType() instanceof INakedBusinessRole){
			return true;
		}
		return false;
	}
}
