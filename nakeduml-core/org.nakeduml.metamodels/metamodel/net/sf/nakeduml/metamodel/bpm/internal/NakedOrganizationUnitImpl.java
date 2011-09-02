package net.sf.nakeduml.metamodel.bpm.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.bpm.INakedBusinessComponent;
import net.sf.nakeduml.metamodel.bpm.INakedBusinessService;
import net.sf.nakeduml.metamodel.components.INakedPort;
import net.sf.nakeduml.metamodel.components.internal.NakedComponentImpl;

public class NakedOrganizationUnitImpl extends NakedComponentImpl implements INakedBusinessComponent{
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
