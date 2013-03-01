package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.UmlReference;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface RequiredRole extends EObject, UmlReference {
	public void buildTreeFromXml(Element xml);
	
	public RootUserInteractionConstraint getConstraint();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setConstraint(RootUserInteractionConstraint constraint);
	
	public void setUid(String uid);

}