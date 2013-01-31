package org.opaeum.uim.perspective;

import java.util.List;

import org.opaeum.ecore.EObject;

public interface ExplorerConfiguration extends EObject, ViewAllocation {
	public List<ExplorerClassConstraint> getClasses();
	
	public void setClasses(List<ExplorerClassConstraint> classes);

}