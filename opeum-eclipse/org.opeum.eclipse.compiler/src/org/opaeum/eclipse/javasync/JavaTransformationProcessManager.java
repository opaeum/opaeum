package org.opeum.eclipse.javasync;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.opeum.eclipse.EmfToNakedUmlSynchronizer;
import org.opeum.eclipse.OpeumEclipsePlugin;
import org.opeum.eclipse.context.OpeumEclipseContext;
import org.opeum.eclipse.starter.GeneratorSourceFolderIdentifier;
import org.opeum.emf.workspace.EmfWorkspace;
import org.opeum.feature.ITransformationStep;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.TransformationProcess;
import org.opeum.generation.features.BpmUsingJbpm5;
import org.opeum.generation.features.ExtendedCompositionSemantics;
import org.opeum.generation.features.OclExpressionExecution;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opeum.javageneration.hibernate.HibernatePackageAnnotator;
import org.opeum.javageneration.jbpm5.Jbpm5EnvironmentBuilder;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.textmetamodel.TextWorkspace;

public class JavaTransformationProcessManager implements IStartup,Runnable{
	public static TransformationProcess getCurrentTransformationProcess(){
		return currentTransformationProcess;
	}
	private static Map<OpeumEclipseContext,TransformationProcess> processes = new WeakHashMap<OpeumEclipseContext,TransformationProcess>();
	private static TransformationProcess currentTransformationProcess;
	public JavaTransformationProcessManager(){
	}
	@Override
	public void run(){
		try{
			// Continuously associate new contexts with transformation processes
			OpeumEclipseContext currentContext = OpeumEclipseContext.getCurrentContext();
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
	private static synchronized TransformationProcess getTransformationProcess(final OpeumEclipseContext ne){
		TransformationProcess process = processes.get(ne);
		if(process == null){
			process = new TransformationProcess();
			// Load classes for config
			OpeumEclipsePlugin.getDefault();
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
	public static void reinitializeProcess(TransformationProcess process, OpeumEclipseContext ne){
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
	public static Set<Class<? extends ITransformationStep>> getAllSteps(OpeumConfig cfg){
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
	private static void mapAdditionalOutputRoots(OpeumConfig cfg){
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
		return getTransformationProcess(OpeumEclipseContext.findOrCreateContextFor(folder));
	}
}
