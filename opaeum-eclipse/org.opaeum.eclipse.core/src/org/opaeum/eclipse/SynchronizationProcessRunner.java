package org.opaeum.eclipse;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.emf.extraction.EmfExtractionPhase;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

public final class SynchronizationProcessRunner extends Job{
	private Set<EObject> emfChanges;
	private Set<OpaeumContextSynchronizationListener> synchronizationListener;
	private TransformationProcess transformationProcess;
	public SynchronizationProcessRunner(TransformationProcess transformationProcess,Set<OpaeumContextSynchronizationListener> synchronizationListener2,Set<? extends EObject> emfChanges){
		super("Synchronizing Opaeum Metadata with UML");
		this.transformationProcess = transformationProcess;
		synchronized(emfChanges){
			this.emfChanges = new HashSet<EObject>(emfChanges);
			emfChanges.clear();
		}
		this.synchronizationListener = synchronizationListener2;
	}
	public IStatus run(IProgressMonitor monitor){
		try{
			monitor.beginTask("Synchronizing Opaeum Metadata", 50);
			if(emfChanges.size() > 0){
				long start = System.currentTimeMillis();
				Set<INakedElement> changedNakedElements = new HashSet<INakedElement>();
				for(Object object:transformationProcess.processElements(emfChanges, EmfExtractionPhase.class, new ProgressMonitorTransformationLog(monitor, 50))){
					if(object instanceof INakedElement){
						changedNakedElements.add((INakedElement) object);
					}
				}
				final INakedModelWorkspace findModel = transformationProcess.findModel(INakedModelWorkspace.class);
				for(OpaeumContextSynchronizationListener listener:synchronizationListener){
					listener.synchronizationComplete(findModel, changedNakedElements);
				}
				System.out.println("Synchronization took " + (System.currentTimeMillis() - start));
			}
			return new Status(IStatus.OK, OpaeumEclipsePlugin.getId(), "Opaeum Metadata Synchronized Successfully");
		}catch(Exception e){
			e.printStackTrace();
			return new Status(IStatus.ERROR, OpaeumEclipsePlugin.getId(), "Synchronization Failed", e);
		}finally{
			monitor.done();
		}
	}
}