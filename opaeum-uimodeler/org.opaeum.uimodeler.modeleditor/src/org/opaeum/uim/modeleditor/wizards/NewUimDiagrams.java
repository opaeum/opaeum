/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.uim.modeleditor.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;
import org.opaeum.uim.modeleditor.UimImageRegistry;
import org.opaeum.uim.modeleditor.UimPlugin;
import org.topcased.modeler.extensions.Template;
import org.topcased.modeler.extensions.TemplatesManager;
import org.topcased.modeler.tools.DiagramFileInitializer;
import org.topcased.modeler.wizards.DiagramsPage;

/**
 * Generated wizard that offers the model creation facilities.
 *
 * @generated
 */
public class NewUimDiagrams extends Wizard implements INewWizard{
	/**
	 * @generated
	 */
	private IStructuredSelection selection;
	/**
	 * @generated
	 */
	private DiagramsPage diagPage;
	/**
	 * @generated
	 */
	private IFile createdFile;
	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 * @generated
	 */
	public void init(IWorkbench workbench,IStructuredSelection sel){
		createdFile = null;
		selection = sel;
		// TODO put the Wizard image
		setDefaultPageImageDescriptor(UimImageRegistry.getImageDescriptor("NEW_PAGE_WZD"));
		setDialogSettings(UimPlugin.getDefault().getDialogSettings());
		setWindowTitle("Create new Uim diagrams");
	}
	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 * @generated
	 */
	public boolean performFinish(){
		boolean result = true;
		if(diagPage.isPageComplete()){
			if(diagPage.isNewModel()){
				result = result & createModelFile();
				result = result & createDiagramFile();
				if(createdFile != null && result){
					// Open the newly created model
					try{
						IDE.openEditor(UimPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage(), createdFile, true);
					}catch(PartInitException pie){
						UimPlugin.log(pie);
					}
				}
			}else{
				createDiagramFromExistingModel();
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	private boolean createDiagramFromExistingModel(){
		WorkspaceModifyOperation op = new WorkspaceModifyOperation(){
			/**
			 * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
			 */
			protected void execute(IProgressMonitor monitor) throws CoreException,InvocationTargetException,InterruptedException{
				DiagramFileInitializer initializer = new DiagramFileInitializer();
				try{
					initializer.createDiagram(diagPage.getDiagramEObject(), diagPage.getDiagramId(), diagPage.isInitialized(), monitor);
				}catch(IOException ioe){
					throw new InvocationTargetException(ioe);
				}
			}
		};
		try{
			getContainer().run(false, true, op);
			return true;
		}catch(InvocationTargetException ite){
			UimPlugin.log(ite);
		}catch(InterruptedException ie){
			// Wizard stopped
		}
		return false;
	}
	/**
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 * @generated
	 */
	public void addPages(){
		diagPage = new UimDiagramsPage("New User Interaction Model Editor Diagram", selection);
		diagPage.setTitle("Uim Diagrams");
		diagPage.setDescription("Define the model diagram informations.");
		addPage(diagPage);
	}
	/**
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 * @generated
	 */
	public boolean canFinish(){
		return diagPage.isPageComplete();
	}
	/**
	 * @return true if the model file was successfully created
	 * @generated
	 */
	private boolean createModelFile(){
		try{
			Template template = TemplatesManager.getInstance().find(diagPage.getTemplateId()).getTemplateModel();
			template.setDestination(diagPage.getSelectedIContainer());
			template.addVariable("name", diagPage.getModelName());
			template.generate(new NullProgressMonitor());
		}catch(CoreException ce){
			UimPlugin.log(ce);
			UimPlugin.displayDialog(null, "An error occured during the template generation.", IStatus.ERROR);
			return false;
		}
		return true;
	}
	/**
	 * @return true if the diagram file was successfully created
	 * @generated
	 */
	private boolean createDiagramFile(){
		try{
			Template template = TemplatesManager.getInstance().find(diagPage.getTemplateId()).getTemplateDI();
			template.setDestination(diagPage.getSelectedIContainer());
			template.addVariable("name", diagPage.getModelName());
			// Bug #1395 : Add an additional variable used to encode the model file name
			template.addVariable("escapedName", URI.encodeFragment(diagPage.getModelName(), false));
			createdFile = (IFile) template.generate(new NullProgressMonitor());
		}catch(CoreException ce){
			UimPlugin.log(ce);
			UimPlugin.displayDialog(null, "An error occured during the template generation.", IStatus.ERROR);
			return false;
		}
		return true;
	}
}
