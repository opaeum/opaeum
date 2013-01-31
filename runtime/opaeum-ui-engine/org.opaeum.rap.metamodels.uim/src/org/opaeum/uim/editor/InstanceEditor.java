package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;

public interface InstanceEditor extends EObject, AbstractEditor {
	public MenuConfiguration getMenuConfiguration();
	
	public void setMenuConfiguration(MenuConfiguration menuConfiguration);

}