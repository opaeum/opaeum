package org.opaeum.uim.wizard;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface NewObjectWizard extends EObject, AbstractWizard {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public ClassUserInteractionModel getModel();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setModel(ClassUserInteractionModel model);
	
	public void setUid(String uid);

}