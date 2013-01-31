package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.panel.AbstractPanel;

public interface ActionBar extends EObject, AbstractPanel {
	public AbstractEditor getEditor();
	
	public void setEditor(AbstractEditor editor);

}