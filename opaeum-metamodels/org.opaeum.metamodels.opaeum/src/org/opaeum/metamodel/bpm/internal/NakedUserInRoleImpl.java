package org.opeum.metamodel.bpm.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.opeum.metamodel.bpm.INakedBusinessRole;
import org.opeum.metamodel.bpm.INakedBusinessService;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedInterfaceRealization;
import org.opeum.metamodel.core.internal.NakedEntityImpl;

public class NakedUserInRoleImpl extends NakedEntityImpl implements INakedBusinessRole{
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
