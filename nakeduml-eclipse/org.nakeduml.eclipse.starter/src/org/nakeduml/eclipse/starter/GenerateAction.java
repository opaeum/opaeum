package org.nakeduml.eclipse.starter;

import java.io.File;
import java.util.Iterator;
import java.util.StringTokenizer;

import net.sf.nakeduml.feature.NakedUmlConfig;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.internal.service.GetLiveConstraintsOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;
import org.nakeduml.eclipse.NakedUmlConfigDialog;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class GenerateAction implements IObjectActionDelegate{
	private IStructuredSelection selection;
	private IWorkbenchPart workbenchPart;
	private NakedUmlConfigDialog dlg;
	static Model model;
	@Override
	public void run(IAction action){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			if(element instanceof Model){
				try{
					int noOfProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects().length;
					model = (Model) element;
					File modelFile = getModeFile(model);
					NakedUmlConfig cfg = new NakedUmlConfig();
					cfg.load(new File(modelFile.getParentFile(), "nakeduml.properties"), "dummy");
		
					File outputRoot = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), cfg.getWorkspaceIdentifier());
					if(cfg.getWorkspaceIdentifier().equals("dummy")){
						this.dlg = new NakedUmlConfigDialog(this.workbenchPart.getSite().getShell());
						dlg.open();
						String domain = dlg.getCompanyDomain();
						StringBuilder mavenGroup = null;
						StringTokenizer st = new StringTokenizer(domain, ".");
						while(st.hasMoreTokens()){
							if(mavenGroup == null){
								mavenGroup = new StringBuilder(st.nextToken());
							}else{
								mavenGroup.insert(0, '.');
								mavenGroup.insert(0, st.nextToken());
							}
						}
						mavenGroup.append('.');
						mavenGroup.append(dlg.getWorkspaceIdentifier());
						cfg.setMavenGroupId(mavenGroup.toString());
						model = (Model) element;
						cfg.setWorkspaceIdentifier(dlg.getWorkspaceIdentifier());
						cfg.setOutputRoot(outputRoot);
						// TODO add nakeduml libraries and profile
						// TODO automaticall prefix domain to model qualified names
						StarterCodeGenerator codeGen = new StarterCodeGenerator(model.eResource().getResourceSet(), cfg, modelFile.getParentFile());
						generateCode(codeGen);
					}else{
						cfg.setOutputRoot(outputRoot);
						// TODO add nakeduml libraries and profile
						// TODO automaticall prefix domain to model qualified names
						StarterCodeGenerator codeGen = new StarterCodeGenerator(model.eResource().getResourceSet(), cfg, modelFile.getParentFile());
						generateCode(codeGen);
					}
					if(noOfProjects != ResourcesPlugin.getWorkspace().getRoot().getProjects().length){
						JavaCore.setClasspathVariable("M2_REPO", new Path(System.getProperty("user.home") + "/.m2/repository"), null);
						Process p = Runtime.getRuntime().exec("mvn eclipse:eclipse -o", new String[0], cfg.getOutputRoot());
						p.waitFor();
					}
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IProject.DEPTH_INFINITE, null);
				}catch(Exception e){
					NakedUmlEclipsePlugin.getDefault().getLog().log(new Status(Status.INFO, NakedUmlEclipsePlugin.getPluginId(), Status.OK, e.getMessage(), e));
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	private void generateCode(StarterCodeGenerator codeGen){
		try{
			codeGen.transformDirectory();
			codeGen.generateIntegrationCode();
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public File getModeFile(Model model){
		String uriPAth = model.eResource().getURI().toPlatformString(true);
		IFile modelIFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uriPAth));
		File modelFile = modelIFile.getLocation().toFile();
		return modelFile;
	}
	@Override
	public void selectionChanged(IAction arg0,ISelection selection){
		this.selection = (IStructuredSelection) selection;
	}
	@Override
	public void setActivePart(IAction arg0,IWorkbenchPart workbenchPart){
		new JavaSourceSynchronizer(Display.getCurrent());

		this.workbenchPart = workbenchPart;
	}
}
