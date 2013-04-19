package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.QueryResultPage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface QueryInvoker extends EObject, AbstractUserInteractionModel, AbstractEditor {
	public void buildTreeFromXml(Element xml);
	
	public QueryResultPage getResultPage();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setResultPage(QueryResultPage resultPage);
	
	public void setUid(String uid);

}