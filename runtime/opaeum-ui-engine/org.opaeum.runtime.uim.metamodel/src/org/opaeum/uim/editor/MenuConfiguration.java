package org.opaeum.uim.editor;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.UserInteractionElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface MenuConfiguration extends EObject, UserInteractionElement {
	public void buildTreeFromXml(Element xml);
	
	public InstanceEditor getEditor();
	
	public List<OperationMenuItem> getOperations();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setEditor(InstanceEditor editor);
	
	public void setOperations(List<OperationMenuItem> operations);
	
	public void setUid(String uid);

}