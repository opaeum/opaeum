package org.opaeum.runtime.organization;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;


public interface IBusinessComponentBase extends CompositionNode,IPersistentObject {
	public IOrganizationNode getRepresentedOrganization();
	public void setRepresentedOrganization(IOrganizationNode representedOrganization);
}
