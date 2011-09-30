package org.opeum.metamodel.bpm.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.opeum.metamodel.bpm.INakedBusinessComponent;
import org.opeum.metamodel.bpm.INakedBusinessService;
import org.opeum.metamodel.components.INakedPort;
import org.opeum.metamodel.components.internal.NakedComponentImpl;

public class NakedOrganizationUnitImpl extends NakedComponentImpl implements INakedBusinessComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2850233045345520573L;

	@Override
	public Collection<INakedBusinessService> getProvidedBusinessServices(){
		Set<INakedBusinessService> result = new HashSet<INakedBusinessService>();
		Collection<INakedPort> ownedPorts = getOwnedPorts();
		for(INakedPort port:ownedPorts){
			if(port.getBaseType() instanceof INakedBusinessService){
				result.add((INakedBusinessService) port.getBaseType());
			}
		}
		return result;
	}
}
