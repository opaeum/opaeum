package org.opaeum.eclipse.javasync;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.eclipse.starter.MemoryUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.javageneration.JavaTransformationPhase;

public class RecompileModelDirectoryAction extends AbstractDirectoryReadingAction{
	public RecompileModelDirectoryAction(IStructuredSelection selection2){
		super(selection2, "Recompile Model Directory");
	}
	protected RecompileModelDirectoryAction(IStructuredSelection selection2,String string){
		super(selection2, string);
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext currentContext = OpaeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Recompiling model directory"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				TransformationProcess p = null;
				try{
					monitor.beginTask("Loading All Models", 1000);
					p = prepareDirectoryForTransformation(folder, monitor);
					monitor.subTask("Generating Java Code");
					p.executeFrom(JavaTransformationPhase.class, new ProgressMonitorTransformationLog(monitor, 400), false);
					if(!(monitor.isCanceled())){
						p.integrate(new ProgressMonitorTransformationLog(monitor, 100));
					}
					monitor.subTask("Generating text files");
					JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 400), p, true);
					currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
					EmfWorkspace mw = p.findModel(EmfWorkspace.class);
					TreeIterator<Notifier> iter = mw.getResourceSet().getAllContents();
					Map<Long,Element> ids = new HashMap<Long,Element>();
					int duplicates = 0;
					while(iter.hasNext()){
						Notifier notifier = (Notifier) iter.next();
						if(notifier instanceof Element){
							Element e = (Element) notifier;
							if(!ids.containsKey(EmfWorkspace.getOpaeumId(e))){
								ids.put(EmfWorkspace.getOpaeumId(e), e);
							}else{
								duplicates++;
								Element other = ids.get(EmfWorkspace.getOpaeumId(e));
								if(other instanceof NamedElement){
									OpaeumEclipsePlugin.logWarning(((NamedElement) e).getQualifiedName() + " collides with "
											+ ((NamedElement) other).getQualifiedName());
								}
								OpaeumEclipsePlugin.logWarning(e.getClass().getName() + " collides with " + other.getClass().getName());
								OpaeumEclipsePlugin.logWarning(EmfWorkspace.getId(e) + " collides with " + EmfWorkspace.getId(other));
							}
						}
					}
					if(duplicates > 0){
						OpaeumEclipsePlugin.logWarning("Number of duplicates: " + duplicates + " from " + ids.size());
					}
				}catch(Exception e){
					return OpaeumEclipsePlugin.logError("Model directory could not be built", e);
				}finally{
					if(p != null){
						p.release();
					}
					monitor.done();
					MemoryUtil.printMemoryUsage();
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
			}
		}.schedule();
	}
}
