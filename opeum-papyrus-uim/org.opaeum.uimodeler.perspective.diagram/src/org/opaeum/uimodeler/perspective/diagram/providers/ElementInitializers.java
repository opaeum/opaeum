package org.opaeum.uimodeler.perspective.diagram.providers;

import org.opaeum.uimodeler.perspective.diagram.part.UimPerspectiveDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers{
	protected ElementInitializers(){
		// use #getInstance to access cached instance
	}
	/**
	 * @generated
	 */
	public static ElementInitializers getInstance(){
		ElementInitializers cached = UimPerspectiveDiagramEditorPlugin.getInstance().getElementInitializers();
		if(cached == null){
			UimPerspectiveDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
