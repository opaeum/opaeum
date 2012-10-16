package org.opaeum.runtime.organization;

import org.opaeum.runtime.domain.CompositionNode;


public interface IBusinessActorBase extends CompositionNode{
	public IOrganizationNode getOrganization();
	public IPersonNode getRepresentedPerson();
	public void setOrganization(IOrganizationNode organization);
	public void setRepresentedPerson(IPersonNode representedPerson);
}
