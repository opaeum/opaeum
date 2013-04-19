package org.opaeum.runtime.organization;

import org.opaeum.runtime.domain.IPersistentObject;

public interface IBusinessNetwork extends IPersistentObject{
	public IPersonNode createPerson(String username);
	public IOrganizationNode createOrganization(String name);
	public void addToBusinessCollaboration(IBusinessCollaborationBase sb);
	public IPersonNode getPerson(String userName);
}
