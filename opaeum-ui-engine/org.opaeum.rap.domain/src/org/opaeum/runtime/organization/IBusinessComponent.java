package org.opaeum.runtime.organization;

import org.opaeum.runtime.domain.CompositionNode;


public interface IBusinessComponent extends CompositionNode{
	public IOrganizationNode getRepresentedOrganization();
	public void setRepresentedOrganization(IOrganizationNode representedOrganization);
}
