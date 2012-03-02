package org.opaeum.metamodel.bpm.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.metamodel.bpm.INakedBusinessComponent;
import org.opaeum.metamodel.bpm.INakedBusinessRole;
import org.opaeum.metamodel.bpm.INakedBusinessService;
import org.opaeum.metamodel.components.INakedPort;
import org.opaeum.metamodel.components.internal.NakedComponentImpl;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.emulated.NakedBusinessCollaboration;

public class NakedBusinessComponentImpl extends NakedComponentImpl implements INakedBusinessComponent{
	private static final long serialVersionUID = -2850233045345520573L;
	private INakedBusinessRole adminRole;
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
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasTaggedValue(StereotypeNames.BUSINESS_COMPONENT, "adminRole")){
			adminRole = (INakedBusinessRole) stereotype.getFirstValueFor("adminRole").getValue();
		}
	}
	@Override
	public boolean isRoot(){
		return getEndToComposite() == null || getEndToComposite().getBaseType() instanceof NakedBusinessCollaboration;
	}
	@Override
	public INakedBusinessRole getAdminRole(){
		return adminRole;
	}
}
