package org.nakeduml.eclipse.starter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;

public class GenerateAction implements IObjectActionDelegate{
	private IStructuredSelection selection;
	private IWorkbenchPart workbenchPart;
	static Model model;
	@Override
	public void run(IAction action){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			if(element instanceof Model){
				NakedUmlConfigDialog dlg = new NakedUmlConfigDialog(this.workbenchPart.getSite().getShell());
				dlg.open();
				model = (Model) element;
				//TODO add nakeduml libraries and profile
				//TODO add nakeduml model stereotype with package prefix
				try{
					generateFor(model, dlg);
					
				}catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	public void generateFor(Model model,NakedUmlConfigDialog dlg) throws Exception{
		try{
			File workspaceFile = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
			//find out how to resolve the correct path
			String uriPAth = model.eResource().getURI().toPlatformString(true);
			File modelFile = new File(workspaceFile, uriPAth);
			StarterCodeGenerator codeGen = new StarterCodeGenerator(dlg, modelFile.getParentFile().getAbsolutePath());
			codeGen.transformDirectory();
			codeGen.generateIntegrationCode();
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IProject.DEPTH_INFINITE, null);
		}catch(CoreException e){
			throw new RuntimeException(e);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	@Override
	public void selectionChanged(IAction arg0,ISelection selection){
		this.selection = (IStructuredSelection) selection;
	}
	@Override
	public void setActivePart(IAction arg0,IWorkbenchPart workbenchPart){
		this.workbenchPart = workbenchPart;
	}
	public static String getModelDirPath(){
		URI uri = GenerateAction.model.eResource().getURI();
		return uri.trimSegments(1).toPlatformString(true);
	}
}
