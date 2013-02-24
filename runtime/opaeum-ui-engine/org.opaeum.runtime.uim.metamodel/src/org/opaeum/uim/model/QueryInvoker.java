package org.opaeum.uim.model;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.QueryResultPage;
import org.w3c.dom.Element;

public interface QueryInvoker extends EObject, AbstractUserInteractionModel, AbstractEditor {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public QueryResultPage getResultPage();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setResultPage(QueryResultPage resultPage);
	
	public void setUid(String uid);

}