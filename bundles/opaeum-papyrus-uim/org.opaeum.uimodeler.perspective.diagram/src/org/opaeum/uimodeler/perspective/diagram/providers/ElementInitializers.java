package org.opaeum.uimodeler.perspective.diagram.providers;

import org.opaeum.uimodeler.perspective.diagram.part.PerspectiveConfigurationDiagramEditorPlugin;

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
		ElementInitializers cached = PerspectiveConfigurationDiagramEditorPlugin.getInstance().getElementInitializers();
		if(cached == null){
			PerspectiveConfigurationDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
