package org.opaeum.uim.editor;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.UserInterfaceRoot;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface AbstractEditor extends EObject, UserInterfaceRoot {
	public void buildTreeFromXml(Element xml);
	
	public ActionBar getActionBar();
	
	public List<EditorPage> getPages();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setActionBar(ActionBar actionBar);
	
	public void setPages(List<EditorPage> pages);
	
	public void setUid(String uid);

}