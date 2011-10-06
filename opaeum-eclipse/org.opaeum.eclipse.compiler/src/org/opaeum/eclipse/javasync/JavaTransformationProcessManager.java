package org.opaeum.eclipse.javasync;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.opaeum.eclipse.EmfToNakedUmlSynchronizer;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.starter.GeneratorSourceFolderIdentifier;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.generation.features.BpmUsingJbpm5;
import org.opaeum.generation.features.ExtendedCompositionSemantics;
import org.opaeum.generation.features.OclExpressionExecution;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opaeum.javageneration.hibernate.HibernatePackageAnnotator;
import org.opaeum.javageneration.jbpm5.Jbpm5EnvironmentBuilder;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.textmetamodel.TextWorkspace;

public class JavaTransformationProcessManager implements IStartup,Runnable{
	public static TransformationProcess getCurrentTransformationProcess(){
		return currentTransformationProcess;
	}
	private static Map<OpaeumEclipseContext,TransformationProcess> processes = new WeakHashMap<OpaeumEclipseContext,TransformationProcess>();
	private static TransformationProcess currentTransformationProcess;
	public JavaTransformationProcessManager(){
	}
	@Override
	public void run(){
		try{
			// Continuously associate new contexts with transformation processes
			OpaeumEclipseContext currentContext = OpaeumEclipseContext.getCurrentContext();
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
	private static synchronized TransformationProcess getTransformationProcess(final OpaeumEclipseContext ne){
		TransformationProcess process = processes.get(ne);
		if(process == null){
			process = new TransformationProcess();
			// Load classes for config
			OpaeumEclipsePlugin.getDefault();
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
	public static void reinitializeProcess(TransformationProcess process, OpaeumEclipseContext ne){
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
	public static Set<Class<? extends ITransformationStep>> getAllSteps(OpaeumConfig cfg){
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
	private static void mapAdditionalOutputRoots(OpaeumConfig cfg){
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
		return getTransformationProcess(OpaeumEclipseContext.findOrCreateContextFor(folder));
	}
}
