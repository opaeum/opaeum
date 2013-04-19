package org.opaeum.runtime.organization;

import org.opaeum.runtime.domain.CompositionNode;

public interface IBusinessRoleBase extends CompositionNode,IParticipantBase{
	public void setRepresentedPerson(IPersonNode representedPerson);
}
