/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Cedric Dumoulin  Cedric.dumoulin@lifl.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.modelexplorer;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Perspective for the Papyrus tool.
 */
public class PapyrusPerspective implements IPerspectiveFactory {

	/** constant for the definition of papyrus model explorer **/
	protected static final String ID_MODELEXPLORER = "org.eclipse.papyrus.views.modelexplorer.modelexplorer";

	/**
	 * 
	 * this method create the layout attached to this perspective
	 * 
	 * @param layout
	 */
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}

	/**
	 * Add actions into the workbench UI.
	 * 
	 * @param layout
	 *        the page layout
	 * 
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void defineActions(IPageLayout layout) {
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

	public void defineLayout(IPageLayout layout) {
		// Editors are placed for free.
		String editorArea = layout.getEditorArea();

		IFolderLayout f = layout.createFolder("left", IPageLayout.LEFT, 0.2f, editorArea);
		f.addView(IPageLayout.ID_PROJECT_EXPLORER);
		f.addView(ID_MODELEXPLORER);

		// place properties under the editor
		layout.addView(IPageLayout.ID_PROP_SHEET, IPageLayout.BOTTOM, (float)0.70, editorArea);
	}
}
