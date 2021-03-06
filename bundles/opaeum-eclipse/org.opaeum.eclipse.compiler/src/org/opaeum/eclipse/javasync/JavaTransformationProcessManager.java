package org.opaeum.eclipse.javasync;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.OpaeumScheduler;
import org.opaeum.eclipse.OpaeumSynchronizationListener;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.generation.features.ExtendedCompositionSemantics;
import org.opaeum.generation.features.OclExpressionExecution;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opaeum.javageneration.bpm.BpmJavaStep;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.textmetamodel.TextWorkspace;

public class JavaTransformationProcessManager implements IStartup,Runnable{
	private static Map<IResource,TransformationProcess> processes = new WeakHashMap<IResource,TransformationProcess>();
	private static Map<IResource,JavaSourceSynchronizer> synchronizers = new WeakHashMap<IResource,JavaSourceSynchronizer>();
	public JavaTransformationProcessManager(){
	}
	@Override
	public void run(){
		try{
			// Continuously associate new contexts with transformation processes
			for(OpenUmlFile openUmlFile:OpaeumEclipseContext.getAllOpenUmlFiles()){
				getTransformationProcess(openUmlFile);
			}
		}catch(Throwable e){
			e.printStackTrace();
			// Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
		}finally{
			try{
				Display.getDefault().timerExec(10000, this);
			}catch(Throwable e){
				e.printStackTrace();
				// Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
			}
		}
	}
	private static synchronized TransformationProcess getTransformationProcess(final OpenUmlFile ouf){
		// For transformations running on a single model
		TransformationProcess process = processes.get(ouf.getFile());
		if(process == null){
			process = new TransformationProcess();
			// Load classes for config
			OpaeumEclipsePlugin.getDefault();
			reinitializeProcess(process, ouf.getConfig(), ouf.getFile().getParent());
			process.replaceModel(ouf.getEmfWorkspace());
			processes.put(ouf.getFile(), process);
			synchronizers.put(ouf.getFile(), new JavaSourceSynchronizer(ouf, process));
			ouf.addContextListener(new OpaeumSynchronizationListener(){
				@Override
				public void synchronizationComplete(OpenUmlFile openUmlFile,Set<Element> affectedElements){
				}
				@Override
				public void onClose(OpenUmlFile openUmlFile){
					processes.get(openUmlFile.getFile()).release();
					processes.remove(openUmlFile.getFile());
					synchronizers.remove(openUmlFile.getFile());
				}
			});
		}
		return process;
	}
	public static void reinitializeProcess(TransformationProcess process,OpaeumConfig cfg,IContainer umlDir){
		Set<Class<? extends ITransformationStep>> steps = getAllSteps(cfg);
		if(cfg.getOutputRoot().exists()){
			for(File file:cfg.getOutputRoot().listFiles()){
				if(file.getName().equals(".project") || file.getName().equals(".classpath")){
					file.delete();
				}
			}
		}
		process.removeModel(OJWorkspace.class);
		process.removeModel(TextWorkspace.class);
		process.replaceModel(new OJUtil());
		process.initialize(cfg, steps);
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
	protected static Set<Class<? extends ITransformationStep>> toSet(Class<? extends ITransformationStep>...classes){
		return new HashSet<Class<? extends ITransformationStep>>(Arrays.asList(classes));
	}
	@SuppressWarnings("unchecked")
	public static Set<Class<? extends ITransformationStep>> getBasicSteps(){
		Set<Class<? extends ITransformationStep>> result = toSet(ExtendedCompositionSemantics.class, OclExpressionExecution.class,
				BpmJavaStep.class);
		return result;
	}
	@Override
	public void earlyStartup(){
		OpaeumScheduler.schedule(new Runnable(){
			@Override
			public void run(){
				Display.getDefault().syncExec(JavaTransformationProcessManager.this);
			}
		}, 5000);
	}
	@SuppressWarnings("unchecked")
	public static Set<Class<? extends ITransformationStep>> getBasicIntegrationSteps(){
		return toSet(JavaMetaInfoMapGenerator.class);
	}
	public static TransformationProcess getTransformationProcessFor(OpenUmlFile file){
		if(file == null){
			return null;
		}
		return getTransformationProcess(file);
	}
	public static JavaSourceSynchronizer getSynchronizerFor(IFile f){
		return synchronizers.get(f);
	}
}
