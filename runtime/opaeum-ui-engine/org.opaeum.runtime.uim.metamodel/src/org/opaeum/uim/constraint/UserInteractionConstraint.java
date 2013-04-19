package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface UserInteractionConstraint extends EObject, RootUserInteractionConstraint {
	public void buildTreeFromXml(Element xml);
	
	public String getUid();
	
	public boolean isInheritFromParent();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setInheritFromParent(boolean inheritFromParent);
	
	public void setUid(String uid);

}