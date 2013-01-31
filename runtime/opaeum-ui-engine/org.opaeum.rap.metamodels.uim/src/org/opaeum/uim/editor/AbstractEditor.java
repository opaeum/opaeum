package org.opaeum.uim.editor;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UserInterfaceRoot;

public interface AbstractEditor extends EObject, UserInterfaceRoot {
	public ActionBar getActionBar();
	
	public List<EditorPage> getPages();
	
	public void setActionBar(ActionBar actionBar);
	
	public void setPages(List<EditorPage> pages);

}