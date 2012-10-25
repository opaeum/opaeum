package org.opaeum.runtime.organization;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;


public interface IBusinessActorBase extends CompositionNode,IParticipantBase,IPersistentObject{
	public IOrganizationNode getOrganization();
	public IPersonNode getRepresentedPerson();
	public void setOrganization(IOrganizationNode organization);
	public void setRepresentedPerson(IPersonNode representedPerson);
}
