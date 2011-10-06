/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.uim.modeleditor.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.topcased.modeler.wizards.DiagramsPage;

/**
 * @generated
 */
public class UimDiagramsPage extends DiagramsPage{
	/**
	 * @param pageName
	 * @param selection
	 * @generated
	 */
	public UimDiagramsPage(String pageName,IStructuredSelection selection){
		super(pageName, selection, true);
	}
	/**
	 * @see org.topcased.modeler.wizards.DiagramsPage#getEditorID()
	 * @generated
	 */
	public String getEditorID(){
		return "org.opaeum.uim.modeleditor.editor.UimEditor";
	}
	/**
	 * @see org.topcased.modeler.wizards.DiagramsPage#getFileExtension()
	 * @generated
	 */
	public String getFileExtension(){
		return "uim";
	}
	/**
	 * @see org.topcased.modeler.wizards.DiagramsPage#getAdapterFactory()
	 * @generated
	 */
	public ComposedAdapterFactory getAdapterFactory(){
		List factories = new ArrayList();
		factories.add(new org.opaeum.uim.provider.UimItemProviderAdapterFactory());
		factories.add(new org.opaeum.uim.layout.provider.LayoutItemProviderAdapterFactory());
		factories.add(new org.opaeum.uim.control.provider.ControlItemProviderAdapterFactory());
		factories.add(new org.opaeum.uim.folder.provider.FolderItemProviderAdapterFactory());
		factories.add(new org.opaeum.uim.form.provider.FormItemProviderAdapterFactory());
		factories.add(new org.opaeum.uim.binding.provider.BindingItemProviderAdapterFactory());
		factories.add(new org.opaeum.uim.security.provider.SecurityItemProviderAdapterFactory());
		factories.add(new org.opaeum.uim.action.provider.ActionItemProviderAdapterFactory());
		factories.add(new ResourceItemProviderAdapterFactory());
		factories.add(new ReflectiveItemProviderAdapterFactory());
		return new ComposedAdapterFactory(factories);
	}
	/**
	 * @see org.topcased.modeler.wizards.DiagramsPage#getDefaultTemplateId()
	 * @return String
	 * @generated
	 */
	public String getDefaultTemplateId(){
		// TODO return the corresponding ID of the default template
		return "";
	}
}
