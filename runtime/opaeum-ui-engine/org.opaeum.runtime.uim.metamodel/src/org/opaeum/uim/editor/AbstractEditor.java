package org.opaeum.uim.editor;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UserInterfaceRoot;
import org.w3c.dom.Element;

public interface AbstractEditor extends EObject, UserInterfaceRoot {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public ActionBar getActionBar();
	
	public List<EditorPage> getPages();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setActionBar(ActionBar actionBar);
	
	public void setPages(List<EditorPage> pages);
	
	public void setUid(String uid);

}