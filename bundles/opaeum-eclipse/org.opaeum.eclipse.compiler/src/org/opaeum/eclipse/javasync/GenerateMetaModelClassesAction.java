package org.opaeum.eclipse.javasync;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.opaeum.eclipse.menu.AbstractOpaeumAction;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.filegeneration.TextFileDeleter;
import org.opaeum.filegeneration.TextFileGenerator;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.MetaModelJavaTransformationStep;
import org.opaeum.textmetamodel.TextWorkspace;

public class GenerateMetaModelClassesAction extends AbstractOpaeumAction{
	public GenerateMetaModelClassesAction(IStructuredSelection selection){
		super(selection, "Generate Opaeum MetaModel classes");
	}
	@Override
	public void run(){
		ContainerSelectionDialog d = new ContainerSelectionDialog(Display.getDefault().getActiveShell(),
				ResourcesPlugin.getWorkspace().getRoot(), false, "Select Project");
		d.open();
		
		IPath path = (IPath)d.getResult()[0];
		IProject p =  ResourcesPlugin.getWorkspace().getRoot().getProject(path.lastSegment());
		EPackage ep = (EPackage) getElementFrom();
		MetaModelJavaTransformationStep t = new MetaModelJavaTransformationStep();
		TextWorkspace textWorkspace = new TextWorkspace();
		t.initialize(textWorkspace, ep, new OJWorkspace(),p.getName(), "org.opaeum");
		t.startVisiting(ep);
		OpaeumConfig cfg = new OpaeumConfig(new File(p.getLocation().toFile(), "opaeum.properties"));
		cfg.setOutputRoot(p.getLocation().toFile().getParentFile());
		TextFileDeleter textFileDeleter = new TextFileDeleter();
		textFileDeleter.initialize(cfg);
		textFileDeleter.startVisiting(textWorkspace);
		TextFileGenerator textFileGenerator = new TextFileGenerator();
		textFileGenerator.initialize(cfg);
		textFileGenerator.startVisiting(textWorkspace);
		try{
			p.refreshLocal(IResource.DEPTH_INFINITE, null);
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws ClassNotFoundException{
		Class.forName("boolean");
	}
}
