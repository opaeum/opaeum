package org.nakeduml.eclipse.starter;

import java.util.Collection;
import java.util.HashMap;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;
import org.topcased.modeler.utils.ResourceUtils;

public class ToggleAutomaticSynchronization extends AbstractOpiumAction{
	public ToggleAutomaticSynchronization(IStructuredSelection selection2){
		super(selection2, NakedUmlEditor.getNakedUmlEclipseContextFor((IContainer) selection2.getFirstElement()).getAutoSync()?"Turn off Auto Synchronization":"Turn on Auto Synchronization");
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final NakedUmlEclipseContext currentContext = NakedUmlEditor.getNakedUmlEclipseContextFor(folder);
		currentContext.setAutoSync(!currentContext.getAutoSync());
	}
}
