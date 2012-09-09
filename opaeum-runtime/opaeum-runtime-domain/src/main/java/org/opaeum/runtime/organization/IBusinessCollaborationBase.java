package org.opaeum.runtime.organization;

import java.util.Set;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;

public interface IBusinessCollaborationBase extends CompositionNode,IPersistentObject{
	public Set<? extends IBusinessActorBase> getBusinessActor();
	public Set<? extends IBusinessBase> getBusiness();
	public IBusinessNetwork getBusinessNetwork();
}
