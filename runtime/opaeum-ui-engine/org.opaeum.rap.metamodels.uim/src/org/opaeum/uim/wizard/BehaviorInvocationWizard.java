package org.opaeum.uim.wizard;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.model.BehaviorUserInteractionModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface BehaviorInvocationWizard extends EObject, InvocationWizard {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public BehaviorUserInteractionModel getModel();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setModel(BehaviorUserInteractionModel model);
	
	public void setUid(String uid);

}