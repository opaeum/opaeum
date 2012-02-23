package org.eclipse.papyrus.uml.modelexplorer;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class OpaeumPerspective implements IPerspectiveFactory{
	protected static final String ID_MODELEXPLORER = "org.eclipse.papyrus.views.modelexplorer.modelexplorer";
	public void createInitialLayout(IPageLayout layout){
		defineActions(layout);
		defineLayout(layout);
	}
	public void defineActions(IPageLayout layout){
		// Add "new wizards".
		layout.addNewWizardShortcut("org.eclipse.papyrus.uml.diagram.wizards.1createproject");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		layout.addNewWizardShortcut("org.eclipse.papyrus.uml.diagram.wizards.createmodel");
		// Add "show views".
		layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(ID_MODELEXPLORER);
		layout.addActionSet("org.eclipse.debug.ui.launchActionSet");
		// add perspectives
		layout.addPerspectiveShortcut("org.eclipse.ui.resourcePerspective");
		layout.addPerspectiveShortcut("org.eclipse.jdt.ui.JavaPerspective");
	}
	public void defineLayout(IPageLayout layout){
		// Editors are placed for free.
		String editorArea = layout.getEditorArea();
		IFolderLayout f = layout.createFolder("left", IPageLayout.LEFT, 0.2f, editorArea);
		f.addView(IPageLayout.ID_PROJECT_EXPLORER);
		f.addView(ID_MODELEXPLORER);
		// place properties under the editor
		layout.addView(IPageLayout.ID_PROP_SHEET, IPageLayout.BOTTOM, (float) 0.70, editorArea);
	}
}
