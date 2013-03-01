package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface DetailComponent extends EObject, UimComponent {
	public void buildTreeFromXml(Element xml);
	
	public MasterComponent getMasterComponent();
	
	public List<PanelForClass> getPanelsForClasses();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setMasterComponent(MasterComponent masterComponent);
	
	public void setPanelsForClasses(List<PanelForClass> panelsForClasses);
	
	public void setUid(String uid);

}