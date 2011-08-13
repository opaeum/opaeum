package org.nakeduml.eclipse.starter;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.javageneration.hibernate.HibernateConfigGenerator;
import net.sf.nakeduml.javageneration.hibernate.HibernatePackageAnnotator;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5EnvironmentBuilder;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gmf.runtime.common.ui.services.statusline.GetStatusLineContributionOperation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.generation.features.BpmUsingJbpm5;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.OclExpressionExecution;
import org.nakeduml.generation.features.PersistenceUsingHibernate;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.topcased.uml.editor.NakedUmlContextListener;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;
import org.nakeduml.uml2uim.ModelCopyStep;

public class JavaTransformationProcessManager implements IStartup,Runnable{
	public static TransformationProcess getCurrentTransformationProcess(){
		return currentTransformationProcess;
	}
	private static Map<NakedUmlEclipseContext,TransformationProcess> processes = Collections
			.synchronizedMap(new WeakHashMap<NakedUmlEclipseContext,TransformationProcess>());
	private static TransformationProcess currentTransformationProcess;
	public JavaTransformationProcessManager(){
	}
	@Override
	public void run(){
		try{
			// Continuously associate new contexts with transformation processes
			NakedUmlEclipseContext currentContext = NakedUmlEditor.getCurrentContext();
			if(currentContext != null && currentContext.getUmlElementCache() != null && currentContext.getUmlElementCache().getNakedWorkspace() != null){
				getTransformationProcess(currentContext);
			}
		}catch(Throwable e){
			e.printStackTrace();
			// Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
		}finally{
			try{
				Display.getDefault().timerExec(1000, this);
			}catch(Throwable e){
				e.printStackTrace();
				// Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
			}
		}
	}
	private static TransformationProcess getTransformationProcess(final NakedUmlEclipseContext ne){
		TransformationProcess process = processes.get(ne);
		if(process == null && ne.getCurrentEmfWorkspace() != null){
			process = new TransformationProcess();
			// Load classes for config
			NakedUmlEclipsePlugin.getDefault();
			NakedUmlConfig cfg = ne.getUmlElementCache().getConfig();
			ne.addContextListener(new JavaSourceSynchronizer(ne, process));
			reinitializeProcess(process, cfg,ne);
			processes.put(ne, process);
		}
		if(process!=null && ne.getCurrentEmfWorkspace() !=null && process.findModel(EmfWorkspace.class) != ne.getCurrentEmfWorkspace()){
			process.replaceModel(ne.getCurrentEmfWorkspace());
		}
		currentTransformationProcess = process;
		return process;
	}
	public static void reinitializeProcess(TransformationProcess process,NakedUmlConfig cfg, NakedUmlEclipseContext ne){
		Set<Class<? extends ITransformationStep>> steps = getAllSteps(cfg);
		IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
		cfg.setOutputRoot(new File(workspace.getLocation().toFile(), cfg.getWorkspaceIdentifier()));
		mapAdditionalOutputRoots(cfg);
		process.removeModel(OJPackage.class);
		process.removeModel(TextWorkspace.class);
		process.removeModel(EmfWorkspace.class);
		process.removeModel(INakedModelWorkspace.class);
		process.initialize(cfg, steps);
		process.replaceModel(ne.getUmlElementCache().getNakedWorkspace());
	}
	public static Set<Class<? extends ITransformationStep>> getAllSteps(NakedUmlConfig cfg){
		Set<Class<? extends ITransformationStep>> steps = getAllSteps();
		steps.addAll(cfg.getAdditionalTransformationSteps());
		return steps;
	}
	private static Set<Class<? extends ITransformationStep>> getAllSteps(){
		Set<Class<? extends ITransformationStep>> basicIntegrationSteps = getBasicIntegrationSteps();
		// basicIntegrationSteps.add(GeneratorGenerator.class);
		// basicIntegrationSteps.add(GeneratorPomStep.class);
		basicIntegrationSteps.addAll(getBasicSteps());
		return basicIntegrationSteps;
	}
	private static void mapAdditionalOutputRoots(NakedUmlConfig cfg){
		cfg.defineSourceFolder(GeneratorSourceFolderIdentifier.GENERATOR_SRC, true, "-generator", "src/main/java");
		if(cfg.getOutputRoot().exists()){
			for(File file:cfg.getOutputRoot().listFiles()){
				if(file.getName().equals(".project") || file.getName().equals(".classpath")){
					file.delete();
				}
			}
		}
		cfg.defineSourceFolder(GeneratorSourceFolderIdentifier.GENERATOR_SRC, true, "-generator", "src/main/java");
	}
	protected static Set<Class<? extends ITransformationStep>> toSet(Class<? extends ITransformationStep>...classes){
		return new HashSet<Class<? extends ITransformationStep>>(Arrays.asList(classes));
	}
	public static Set<Class<? extends ITransformationStep>> getBasicSteps(){
		Set<Class<? extends ITransformationStep>> result = toSet(ExtendedCompositionSemantics.class, OclExpressionExecution.class, BpmUsingJbpm5.class,
				PersistenceUsingHibernate.class);
		return result;
	}
	@Override
	public void earlyStartup(){
		UmlElementCache.schedule(new Runnable(){
			@Override
			public void run(){
				Display.getDefault().syncExec(JavaTransformationProcessManager.this);
			}
		}, 5000);
	}
	public static Set<Class<? extends ITransformationStep>> getBasicIntegrationSteps(){
		return toSet(HibernateConfigGenerator.class, HibernatePackageAnnotator.class, Jbpm5EnvironmentBuilder.class);
	}
	public static TransformationProcess getTransformationProcessFor(IContainer folder){
		return getTransformationProcess(NakedUmlEditor.getNakedUmlEclipseContextFor(folder));
	}
}
