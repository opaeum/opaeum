package org.nakeduml.eclipse.starter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.emf.workspace.UmlElementCache.RenamedNamespace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.MavenProjectCodeGenerator;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.refactoring.RenameSupport;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.nakeduml.generation.features.BpmUsingJbpm5;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.OclExpressionExecution;
import org.nakeduml.generation.features.PersistenceUsingHibernate;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class JavaSourceSynchronizer implements Runnable{
	private Display display;
	Map<NakedUmlEditor,TransformationProcess> processes = new WeakHashMap<NakedUmlEditor,TransformationProcess>();
	public JavaSourceSynchronizer(Display d){
		this.display = d;
		display.timerExec(10000, this);
	}
	@Override
	public void run(){
		try{
			IJavaModel root = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
			for(IWorkbenchWindow wbw:Activator.getDefault().getWorkbench().getWorkbenchWindows()){
				for(IWorkbenchPage p:wbw.getPages()){
					for(IEditorReference er:p.getEditorReferences()){
						IEditorPart editor = er.getEditor(false);
						if(editor instanceof NakedUmlEditor){
							NakedUmlEditor ne = (NakedUmlEditor) editor;
							TransformationProcess process = getTransformationProcess(ne);
							UmlElementCache cache = ne.getUmlElementCache();
							renamePackages(root, cache);
							synchronizeClasses(process, cache);
						}
					}
				}
			}
		}finally{
			display.timerExec(500, this);
		}
	}
	public void renamePackages(IJavaModel root,UmlElementCache cache){
		RenamedNamespace rn = null;
		try{
			while((rn = cache.getRenamedNamespaces().poll(200, TimeUnit.MILLISECONDS)) != null){
				renamePackages(rn, root);
			}
		}catch(InterruptedException e){
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
		}
	}
	private void synchronizeClasses(TransformationProcess process,UmlElementCache cache){
		try{
			INakedClassifier modifiedClass = null;
			Set<INakedClassifier> clss = new HashSet<INakedClassifier>();
			while((modifiedClass = cache.getModifiedClasses().poll(200, TimeUnit.MILLISECONDS)) != null){
				clss.add(modifiedClass);
			}
			process.processElements(clss, JavaTransformationPhase.class);
		}catch(InterruptedException e){
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
		}
	}
	public TransformationProcess getTransformationProcess(NakedUmlEditor ne){
		TransformationProcess process = processes.get(ne);
		if(process == null){
			process = new TransformationProcess();
			NakedUmlConfig cfg = ne.getUmlElementCache().getConfig();
			MavenProjectCodeGenerator.mapDefaultMavenOutputRoots(cfg);
			cfg.setOutputRoot(new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), cfg.getWorkspaceIdentifier()));

			process.execute(cfg, ne.getUmlElementCache().getNakedWorkspace(), getBasicSteps());
			processes.put(ne, process);
		}
		return process;
	}
	protected static Set<Class<? extends TransformationStep>> toSet(Class<? extends TransformationStep>...classes){
		return new HashSet<Class<? extends TransformationStep>>(Arrays.asList(classes));
	}
	public static Set<Class<? extends TransformationStep>> getBasicSteps(){
		return toSet(ExtendedCompositionSemantics.class, OclExpressionExecution.class, BpmUsingJbpm5.class, PersistenceUsingHibernate.class);
	}
	private void renamePackages(RenamedNamespace rn,IJavaModel root){
		try{
			for(IJavaProject jp:root.getJavaProjects()){
				for(IPackageFragmentRoot pfr:jp.getPackageFragmentRoots()){
					IPackageFragment pf = pfr.getPackageFragment(rn.getOldName());
					if(pf.exists()){
						try{
							RenameSupport rs = RenameSupport.create(pf, rn.getNewName(), RenameSupport.UPDATE_REFERENCES);
							rs.perform(display.getActiveShell(), new ProgressMonitorDialog(display.getActiveShell()));
							pf.rename(rn.getNewName(), true, null);
						}catch(JavaModelException e){
							Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
						}catch(CoreException e){
							Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
						}catch(InterruptedException e){
							Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
						}catch(InvocationTargetException e){
							Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
						}
					}
				}
			}
		}catch(JavaModelException e){
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
		}
	}
}
