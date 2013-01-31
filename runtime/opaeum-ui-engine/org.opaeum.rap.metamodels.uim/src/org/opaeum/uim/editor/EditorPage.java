package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Page;

public interface EditorPage extends EObject, Page {
	public AbstractEditor getEditor();
	
	public void setEditor(AbstractEditor editor);

}