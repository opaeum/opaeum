package org.opaeum.uim.component;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface DetailComponent extends EObject, UimComponent {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public MasterComponent getMasterComponent();
	
	public List<PanelForClass> getPanelsForClasses();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setMasterComponent(MasterComponent masterComponent);
	
	public void setPanelsForClasses(List<PanelForClass> panelsForClasses);
	
	public void setUid(String uid);

}