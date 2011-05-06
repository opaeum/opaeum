/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.modeleditor.wizards;

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
public class UIMDiagramsPage extends DiagramsPage{
	/**
	 * @param pageName
	 * @param selection
	 * @generated
	 */
	public UIMDiagramsPage(String pageName,IStructuredSelection selection){
		super(pageName, selection, true);
	}
	/**
	 * @see org.topcased.modeler.wizards.DiagramsPage#getEditorID()
	 * @generated
	 */
	public String getEditorID(){
		return "org.nakeduml.uim.modeleditor.editor.UIMEditor";
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
		factories.add(new org.nakeduml.uim.provider.UIMItemProviderAdapterFactory());
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
