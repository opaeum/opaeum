package org.opaeum.uimodeler.actionbar.diagram.providers;

import org.opaeum.uimodeler.actionbar.diagram.part.UimDiagramEditorPlugin;

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
		ElementInitializers cached = UimDiagramEditorPlugin.getInstance().getElementInitializers();
		if(cached == null){
			UimDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
