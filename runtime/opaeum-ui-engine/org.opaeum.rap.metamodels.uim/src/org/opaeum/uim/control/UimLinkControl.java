package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.ObjectEditor;

public interface UimLinkControl extends EObject, UimControl {
	public ObjectEditor getEditorToOpen();
	
	public void setEditorToOpen(ObjectEditor editorToOpen);

}