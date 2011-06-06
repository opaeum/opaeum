package net.sf.nakeduml.metamodel.bpm.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.bpm.INakedBusinessService;
import net.sf.nakeduml.metamodel.bpm.INakedBusinessRole;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.internal.NakedEntityImpl;

public class NakedUserInRoleImpl extends NakedEntityImpl implements INakedBusinessRole{
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
