package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface ResponsibilityViewer extends EObject, AbstractEditor {
	public void buildTreeFromXml(Element xml);
	
	public ResponsibilityUserInteractionModel getModel();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setModel(ResponsibilityUserInteractionModel model);
	
	public void setUid(String uid);

}