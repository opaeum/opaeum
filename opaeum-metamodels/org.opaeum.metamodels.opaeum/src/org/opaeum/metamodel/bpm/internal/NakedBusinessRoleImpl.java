package org.opaeum.metamodel.bpm.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.metamodel.bpm.INakedBusinessRole;
import org.opaeum.metamodel.bpm.INakedBusinessService;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedInterfaceRealization;
import org.opaeum.metamodel.core.internal.NakedEntityImpl;

public class NakedBusinessRoleImpl extends NakedEntityImpl implements INakedBusinessRole{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8101559859193547165L;

	@Override
	public Collection<INakedBusinessService> getServicesProvided(){
		Set<INakedBusinessService> result = new HashSet<INakedBusinessService>();
		Collection<INakedInterfaceRealization> ownedPorts = getInterfaceRealizations();
		for(INakedInterfaceRealization r:ownedPorts){
			if(r.getContract() instanceof INakedBusinessService){
				result.add((INakedBusinessService) r.getContract());
			}
		}
		if(getSupertype() instanceof INakedEntity){
			result.addAll(((INakedBusinessRole) getSupertype()).getServicesProvided());
		}
		return result;
	}
}
