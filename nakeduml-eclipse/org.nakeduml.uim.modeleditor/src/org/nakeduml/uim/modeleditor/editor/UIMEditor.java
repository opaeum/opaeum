/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.modeleditor.editor;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.nakeduml.uim.modeleditor.UIMPlugin;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.commands.GEFtoEMFCommandStackWrapper;
import org.topcased.modeler.documentation.EAnnotationDocPage;
import org.topcased.modeler.documentation.IDocPage;
import org.topcased.modeler.editor.Modeler;

/**
 * Generated Model editor
 *
 * @generated
 */
public class UIMEditor extends Modeler {
	public static final String EDITOR_ID = "org.nakeduml.uim.modeleditor.editor.UIMEditor";

	/**
	 * @see org.topcased.modeler.editor.Modeler#getAdapterFactories()
	 * @generated
	 */
	protected List getAdapterFactories() {
		List factories = new ArrayList();
		factories.add(new org.nakeduml.uim.provider.UIMItemProviderAdapterFactory());
		factories.add(new org.nakeduml.uim.modeleditor.providers.UIMModelerProviderAdapterFactory());
		factories.addAll(super.getAdapterFactories());
		return factories;
	}

	/**
	 * @see org.topcased.modeler.editor.Modeler#getId()
	 * @generated
	 */
	public String getId() {
		return EDITOR_ID;
	}

	/**
	 * @see org.topcased.modeler.editor.Modeler#getAdapter(java.lang.Class)
	 * @generated
	 */
	public Object getAdapter(Class type) {
		if (type == IDocPage.class) {
			GEFtoEMFCommandStackWrapper stack = new GEFtoEMFCommandStackWrapper(getCommandStack());
			return new EAnnotationDocPage(stack);
		}
		return super.getAdapter(type);
	}

	/**
	 * @see org.topcased.modeler.editor.Modeler#getPreferenceStore()
	 *
	 * @generated
	 */
	public IPreferenceStore getPreferenceStore() {
		IProject project = (((IFileEditorInput) getEditorInput()).getFile()).getProject();
		Preferences root = Platform.getPreferencesService().getRootNode();
		try {
			if (root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(UIMPlugin.getId())) {
				return new ScopedPreferenceStore(new ProjectScope(project), UIMPlugin.getId());
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return UIMPlugin.getDefault().getPreferenceStore();
	}
}
