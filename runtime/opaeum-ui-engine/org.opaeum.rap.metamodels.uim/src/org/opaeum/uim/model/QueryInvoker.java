package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.QueryResultPage;

public interface QueryInvoker extends EObject, AbstractUserInteractionModel, AbstractEditor {
	public QueryResultPage getResultPage();
	
	public void setResultPage(QueryResultPage resultPage);

}