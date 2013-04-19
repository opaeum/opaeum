package org.opaeum.runtime.organization;

import org.opaeum.runtime.domain.CompositionNode;


public interface IBusinessComponentBase extends CompositionNode{
	public IOrganizationNode getRepresentedOrganization();
	public void setRepresentedOrganization(IOrganizationNode representedOrganization);
}
