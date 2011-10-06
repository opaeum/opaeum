package org.opeum.modeler.product;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;

public class OpeumPerspectiveFactory implements IPerspectiveFactory{
	public void createInitialLayout(IPageLayout layout){
		// Editors are placed for free.
		String editorArea = layout.getEditorArea();
		// Top Left
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, (float) 0.26, editorArea); 
		topLeft.addView(IPageLayout.ID_RES_NAV);
		topLeft.addView("org.topcased.ui.navigator.view"); 
		topLeft.addView(IPageLayout.ID_OUTLINE); 
		IViewLayout navigator = layout.getViewLayout(IPageLayout.ID_RES_NAV);
		navigator.setCloseable(false);
		// Bottom
		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.66, editorArea);
		bottom.addView(IPageLayout.ID_PROP_SHEET);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		IViewLayout properties = layout.getViewLayout(IPageLayout.ID_PROP_SHEET);
		properties.setCloseable(false);
	}
}
