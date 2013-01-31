package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObject;

public interface ExplorerOperationConstraint extends EObject, ExplorerConstraint {
	public ExplorerClassConstraint getOwner();
	
	public void setOwner(ExplorerClassConstraint owner);

}