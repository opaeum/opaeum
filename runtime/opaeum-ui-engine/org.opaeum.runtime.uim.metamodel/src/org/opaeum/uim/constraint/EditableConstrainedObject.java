package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface EditableConstrainedObject extends EObject, ConstrainedObject {
	public void buildTreeFromXml(Element xml);
	
	public UserInteractionConstraint getEditability();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setEditability(UserInteractionConstraint editability);
	
	public void setUid(String uid);

}