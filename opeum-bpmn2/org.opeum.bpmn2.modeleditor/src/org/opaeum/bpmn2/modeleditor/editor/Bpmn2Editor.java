/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opeum.bpmn2.modeleditor.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.opeum.bpmn2.modeleditor.Bpmn2Plugin;
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
public class Bpmn2Editor extends Modeler{
	public static final String EDITOR_ID = "org.opeum.bpmn2.modeleditor.editor.Bpmn2Editor";
	/**
	 * @see org.topcased.modeler.editor.Modeler#getAdapterFactories()
	 * @generated
	 */
	protected List getAdapterFactories(){
		List factories = new ArrayList();
		factories.add(new org.eclipse.bpmn2.provider.Bpmn2ItemProviderAdapterFactory());
		factories.add(new org.opeum.bpmn2.modeleditor.providers.Bpmn2ModelerProviderAdapterFactory());
		factories.add(new org.eclipse.bpmn2.di.provider.BpmnDiItemProviderAdapterFactory());
		factories.add(new org.opeum.bpmn2.modeleditor.providers.BpmnDiModelerProviderAdapterFactory());
		factories.add(new org.eclipse.dd.di.provider.DiItemProviderAdapterFactory());
		factories.add(new org.opeum.bpmn2.modeleditor.providers.DiModelerProviderAdapterFactory());
		factories.add(new org.eclipse.dd.dc.provider.DcItemProviderAdapterFactory());
		factories.add(new org.opeum.bpmn2.modeleditor.providers.DcModelerProviderAdapterFactory());
		factories.addAll(super.getAdapterFactories());
		return factories;
	}
	/**
	 * @see org.topcased.modeler.editor.Modeler#getId()
	 * @generated
	 */
	public String getId(){
		return EDITOR_ID;
	}
	/**
	 * @see org.topcased.modeler.editor.Modeler#getAdapter(java.lang.Class)
	 * @generated
	 */
	public Object getAdapter(Class type){
		if(type == IDocPage.class){
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
	public IPreferenceStore getPreferenceStore(){
		IProject project = (((IFileEditorInput) getEditorInput()).getFile()).getProject();
		Preferences root = Platform.getPreferencesService().getRootNode();
		try{
			if(root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(Bpmn2Plugin.getId())){
				return new ScopedPreferenceStore(new ProjectScope(project), Bpmn2Plugin.getId());
			}
		}catch(BackingStoreException e){
			e.printStackTrace();
		}
		return Bpmn2Plugin.getDefault().getPreferenceStore();
	}
}
