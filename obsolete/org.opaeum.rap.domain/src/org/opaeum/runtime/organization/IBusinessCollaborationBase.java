package org.opaeum.runtime.organization;

import java.util.Set;

import org.opaeum.runtime.domain.CompositionNode;

public interface IBusinessCollaborationBase extends CompositionNode{
	public Set<? extends IBusinessActorBase> getBusinessActor();
	public Set<? extends IBusinessBase> getBusiness();
	public IBusinessNetwork getBusinessNetwork();
}
