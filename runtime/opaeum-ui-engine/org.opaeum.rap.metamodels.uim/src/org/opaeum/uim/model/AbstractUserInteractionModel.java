package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;

public interface AbstractUserInteractionModel extends EObject {
	public String getLinkedUmlResource();
	
	public void setLinkedUmlResource(String linkedUmlResource);

}