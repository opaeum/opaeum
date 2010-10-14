/*
 * Created on Feb 29, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.ui.views;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import nl.klasse.octopus.ui.OctopusPlugin;
import nl.klasse.octopus.ui.OctopusPluginConstants;
import nl.klasse.octopus.ui.properties.DirField;

/**
 * RenameWizard : 
 */
public class RenameDirView extends ViewPart {
	private DirField dirField = null;
	private Button button = null;
	private IFolder currentSelection = null;
	private IProject currentProject = null;

	/**
	 * 
	 */
	public RenameDirView() {
		super();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent) {
		dirField = new DirField(parent, "Rename directory to");
		button = new Button(parent, SWT.PUSH);
		button.setText("Okee");
		button.addSelectionListener(new Show(this));		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
	}

	/**
	 * @param selProject
	 * @param selResource
	 */
	public void openViewOn(IProject selProject, IResource selResource) {
		currentSelection = (IFolder) selResource;
		currentProject = selProject;
		dirField.setValue(selResource.getName());
	}
	
	class Show implements SelectionListener {
			
		private RenameDirView parent;
			
		public Show(RenameDirView parent) {
			super();
			this.parent = parent;
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			System.out.println("BUTTON PUSH");
		}
		
		/** GENERATE: except for the final SAVE,  all switch options are generated
		 *  from the model
		 */
		public void widgetSelected(SelectionEvent e) {
			IFolder resource = (IFolder) currentSelection;
			IProject project = currentProject;
			//
			String modelLocation = "";
			try {
				modelLocation =
					project.getPersistentProperty(
						OctopusPluginConstants.OCTOPUS_MODEL_LOCATION);
			} catch (CoreException e) {
				OctopusPlugin.getDefault().logError(this.getClass().getName(), e);
			}
			if (resource.getProjectRelativePath().equals(modelLocation)){
	
			}
		}

	}
	


}
