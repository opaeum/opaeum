/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.modeleditor.wizards;

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
public class Bpmn2DiagramsPage extends DiagramsPage{
	/**
	 * @param pageName
	 * @param selection
	 * @generated
	 */
	public Bpmn2DiagramsPage(String pageName,IStructuredSelection selection){
		super(pageName, selection, true);
	}
	/**
	 * @see org.topcased.modeler.wizards.DiagramsPage#getEditorID()
	 * @generated
	 */
	public String getEditorID(){
		return "org.opaeum.bpmn2.modeleditor.editor.Bpmn2Editor";
	}
	/**
	 * @see org.topcased.modeler.wizards.DiagramsPage#getFileExtension()
	 * @generated
	 */
	public String getFileExtension(){
		return "bpmn2";
	}
	/**
	 * @see org.topcased.modeler.wizards.DiagramsPage#getAdapterFactory()
	 * @generated
	 */
	public ComposedAdapterFactory getAdapterFactory(){
		List factories = new ArrayList();
		factories.add(new org.eclipse.bpmn2.provider.Bpmn2ItemProviderAdapterFactory());
		factories.add(new org.eclipse.bpmn2.di.provider.BpmnDiItemProviderAdapterFactory());
		factories.add(new org.eclipse.dd.di.provider.DiItemProviderAdapterFactory());
		factories.add(new org.eclipse.dd.dc.provider.DcItemProviderAdapterFactory());
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
