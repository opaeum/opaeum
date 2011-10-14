package org.opaeum.eclipse.versioning;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.JavaProjectGenerator;
import org.opaeum.eclipse.javasync.RecompileModelDirectoryAction;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.JavaTextSource;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.pomgeneration.PomGenerationPhase;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextWorkspace;

public class CompileVersionAction extends RecompileModelDirectoryAction{
	public static String reg = "[0-9]+(\\.[0-9]+){0,2}";
	public CompileVersionAction(IStructuredSelection selection2){
		super(selection2, "Compile Version");
		// TODO Auto-generated constructor stub
	}
	protected CompileVersionAction(IStructuredSelection selection2,String string){
		super(selection2, string);
	}
	public static void main(String[] args){
		System.out.println("00fasdfasd".matches(reg));
		System.out.println("01.01".matches(reg));
		System.out.println("0.023.0231".matches(reg));
		System.out.println("01.01.01lkjlkj".matches(reg));
		System.out.println(".1.1".matches(reg));
		System.out.println("a.1.1".matches(reg));
		System.out.println("1231asdf.1".matches(reg));
		System.out.println(".1.1".matches(reg));
		System.out.println(".1.1".matches(reg));
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext versionContext = OpaeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Recompiling code for version " + folder.getName()){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				try{
					File currentOutputRoot = OpaeumEclipseContext.findOrCreateContextFor(folder.getParent()).getConfig().getOutputRoot();
					String suffix = versionContext.getConfig().getMavenGroupVersionSuffix();
					monitor.beginTask("Loading All Models", 1000);
					TransformationProcess p = prepareDirectoryForTransformation(folder, monitor);
					// TODO this is for UimSynchronizationPhase which should perhaps now take a NakedModelWorkspace as input
					monitor.subTask("Generating Java Code");
					versionContext.getConfig().setOutputRoot(new File(currentOutputRoot,folder.getName()));
					versionContext.getConfig().setGenerateMavenPoms(false);
					p.executeFrom(JavaTransformationPhase.class, new ProgressMonitorTransformationLog(monitor, 400));
					if(!(monitor.isCanceled())){
						p.integrate(new ProgressMonitorTransformationLog(monitor, 100));
					}
					monitor.subTask("Generating text files");
					TextWorkspace tw = p.findModel(TextWorkspace.class);
					Set<OJPathName> affectedClasses = new HashSet<OJPathName>();
					for(TextFile textFile:tw.prepareForVersioning(suffix)){
						if(textFile.getTextSource() instanceof JavaTextSource){
							JavaTextSource jts = (JavaTextSource) textFile.getTextSource();
							if(jts.getJavaSource() instanceof OJClassifier){
								affectedClasses.add(((OJClassifier) jts.getJavaSource()).getPathName());
							}
						}
					}
					for(SourceFolderDefinition sfd:versionContext.getConfig().getSourceFolderDefinitions().values()){
						sfd.overwriteFiles();
						sfd.cleanDirectories();
					}
					p.findModel(OJPackage.class).renameAll(affectedClasses, suffix);
					versionContext.getConfig().setGenerateMavenPoms(true);
					p.getPhase(PomGenerationPhase.class).generateVersionedPoms(new ProgressMonitorTransformationLog(monitor, 50));
					JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 400), p, versionContext, true);
					versionContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
				}catch(Exception e){
					e.printStackTrace();
					return new Status(Status.ERROR, OpaeumEclipsePlugin.getPluginId(), Status.ERROR, e.getMessage(), e);
				}finally{
					monitor.done();
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
			}
		}.schedule();
	}
}
