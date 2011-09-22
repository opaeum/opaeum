package org.nakeduml.eclipse.javasync;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.javageneration.basicjava.JavaMetaInfoMapGenerator;
import net.sf.nakeduml.javageneration.hibernate.HibernatePackageAnnotator;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5EnvironmentBuilder;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IContainer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.eclipse.starter.GeneratorSourceFolderIdentifier;
import org.nakeduml.generation.features.BpmUsingJbpm5;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.OclExpressionExecution;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.topcased.uml.editor.EmfToNakedUmlSynchronizer;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class JavaTransformationProcessManager implements IStartup,Runnable{
	public static TransformationProcess getCurrentTransformationProcess(){
		return currentTransformationProcess;
	}
	private static Map<NakedUmlEclipseContext,TransformationProcess> processes = new WeakHashMap<NakedUmlEclipseContext,TransformationProcess>();
	private static TransformationProcess currentTransformationProcess;
	public JavaTransformationProcessManager(){
	}
	@Override
	public void run(){
		try{
			// Continuously associate new contexts with transformation processes
			NakedUmlEclipseContext currentContext = NakedUmlEditor.getCurrentContext();
			if(currentContext != null && !currentContext.isLoading()){
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
	private static synchronized TransformationProcess getTransformationProcess(final NakedUmlEclipseContext ne){
		TransformationProcess process = processes.get(ne);
		if(process == null){
			process = new TransformationProcess();
			// Load classes for config
			NakedUmlEclipsePlugin.getDefault();
			ne.addContextListener(new JavaSourceSynchronizer(ne, process));
			reinitializeProcess(process, ne);
			processes.put(ne, process);
		}
		if(process!=null && ne.getCurrentEmfWorkspace() !=null && process.findModel(EmfWorkspace.class) != ne.getCurrentEmfWorkspace()){
			process.replaceModel(ne.getCurrentEmfWorkspace());
		}
		currentTransformationProcess = process;
		return process;
	}
	public static void reinitializeProcess(TransformationProcess process, NakedUmlEclipseContext ne){
		Set<Class<? extends ITransformationStep>> steps = getAllSteps(ne.getConfig());
		ne.getConfig().calculateOutputRoot(ne.getUmlDirectory().getProject().getLocation().toFile());
		mapAdditionalOutputRoots(ne.getConfig());
		process.removeModel(OJPackage.class);
		process.removeModel(TextWorkspace.class);
		process.removeModel(EmfWorkspace.class);
		process.removeModel(INakedModelWorkspace.class);
		process.initialize(ne.getConfig(), steps);
		process.replaceModel(ne.getNakedWorkspace());
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
	@SuppressWarnings("unchecked")
	public static Set<Class<? extends ITransformationStep>> getBasicSteps(){
		Set<Class<? extends ITransformationStep>> result = toSet(ExtendedCompositionSemantics.class, OclExpressionExecution.class, BpmUsingJbpm5.class);
		return result;
	}
	@Override
	public void earlyStartup(){
		EmfToNakedUmlSynchronizer.schedule(new Runnable(){
			@Override
			public void run(){
				Display.getDefault().syncExec(JavaTransformationProcessManager.this);
			}
		}, 5000);
	}
	@SuppressWarnings("unchecked")
	public static Set<Class<? extends ITransformationStep>> getBasicIntegrationSteps(){
		return toSet(HibernatePackageAnnotator.class, Jbpm5EnvironmentBuilder.class,JavaMetaInfoMapGenerator.class);
	}
	public static TransformationProcess getTransformationProcessFor(IContainer folder){
		return getTransformationProcess(NakedUmlEditor.findOrCreateContextFor(folder));
	}
}
