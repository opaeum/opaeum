package org.opaeum.eclipse.javasync;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EmfToOpaeumSynchronizer;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.OpaeumSynchronizationListener;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.generation.features.BpmUsingJbpm5;
import org.opaeum.generation.features.ExtendedCompositionSemantics;
import org.opaeum.generation.features.OclExpressionExecution;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opaeum.javageneration.hibernate.HibernatePackageAnnotator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.textmetamodel.TextWorkspace;

public class JavaTransformationProcessManager implements IStartup,Runnable{
	private static Map<IResource,TransformationProcess> processes = new WeakHashMap<IResource,TransformationProcess>();
	public JavaTransformationProcessManager(){
	}
	@Override
	public void run(){
		try{
			// Continuously associate new contexts with transformation processes
			OpaeumEclipseContext currentContext = OpaeumEclipseContext.getCurrentContext();
			if(currentContext != null && !currentContext.isLoading()){
				getTransformationProcess(currentContext);
				for(OpenUmlFile openUmlFile:currentContext.getEditingContexts()){
					getTransformationProcess(openUmlFile);
					
				}
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
	private static synchronized TransformationProcess getTransformationProcess(final OpaeumEclipseContext ne){
		//For transformations running on the entire directory of models
		TransformationProcess process = processes.get(ne.getUmlDirectory());
		if(process == null){
			process = new TransformationProcess();
			process.replaceModel(new OJUtil());
			// Load classes for config
			OpaeumEclipsePlugin.getDefault();
			reinitializeProcess(process, ne.getConfig(), ne.getUmlDirectory());
			processes.put(ne.getUmlDirectory(), process);
		}
		return process;
	}
	private static synchronized TransformationProcess getTransformationProcess(final OpenUmlFile ouf){
		//For transformations running on a single model
		TransformationProcess process = processes.get(ouf.getFile());
		if(process == null){
			process = new TransformationProcess();
			// Load classes for config
			OpaeumEclipsePlugin.getDefault();
			reinitializeProcess(process, ouf.getConfig(), ouf.getFile().getParent());
			processes.put(ouf.getFile(), process);
			new JavaSourceSynchronizer(ouf, process);
			ouf.addContextListener(new OpaeumSynchronizationListener(){
				@Override
				public void synchronizationComplete(OpenUmlFile openUmlFile,Set<Element> affectedElements){
				}
				@Override
				public void onClose(OpenUmlFile openUmlFile){
					processes.get(openUmlFile.getFile()).release();
					processes.remove(openUmlFile.getFile());
				}
			});
		}
		//TODO listen to the closing of files  and remove the process
		process.replaceModel(ouf.getOJUtil());
		process.replaceModel(ouf.getEmfWorkspace());
		return process;
	}
	public static void reinitializeProcess(TransformationProcess process, OpaeumConfig cfg, IContainer umlDir){
		Set<Class<? extends ITransformationStep>> steps = getAllSteps(cfg);
		cfg.calculateOutputRoot(umlDir.getProject().getLocation().toFile());
		if(cfg.getOutputRoot().exists()){
			for(File file:cfg.getOutputRoot().listFiles()){
				if(file.getName().equals(".project") || file.getName().equals(".classpath")){
					file.delete();
				}
			}
		}
		process.removeModel(OJWorkspace.class);
		process.removeModel(TextWorkspace.class);
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
		Set<Class<? extends ITransformationStep>> result = toSet(ExtendedCompositionSemantics.class, OclExpressionExecution.class, BpmUsingJbpm5.class);
		return result;
	}
	@Override
	public void earlyStartup(){
		EmfToOpaeumSynchronizer.schedule(new Runnable(){
			@Override
			public void run(){
				Display.getDefault().syncExec(JavaTransformationProcessManager.this);
			}
		}, 5000);
	}
	@SuppressWarnings("unchecked")
	public static Set<Class<? extends ITransformationStep>> getBasicIntegrationSteps(){
		return toSet(HibernatePackageAnnotator.class, JavaMetaInfoMapGenerator.class);
	}
	public static TransformationProcess getTransformationProcessFor(IContainer folder){
		return getTransformationProcess(OpaeumEclipseContext.findOrCreateContextFor(folder));
	}
	public static TransformationProcess getTransformationProcessFor(OpenUmlFile file){
		
		return getTransformationProcess(file);
	}
}
