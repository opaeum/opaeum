package org.opaeum.uim.component;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.panel.AbstractPanel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface PanelForClass extends EObject, UmlReference {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public DetailComponent getDetailComponent();
	
	public AbstractPanel getPanel();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setDetailComponent(DetailComponent detailComponent);
	
	public void setPanel(AbstractPanel panel);
	
	public void setUid(String uid);

}