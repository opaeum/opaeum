package org.opaeum.runtime.organization;





public interface IBusinessNetwork
{
	public IPersonNode createPerson(String username);
	public IOrganizationNode createOrganization();
	public void addToBusinessCollaboration(IBusinessCollaborationBase sb);
	public IPersonNode getPerson(String userName);
}
