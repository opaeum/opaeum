package org.opeum.eclipse;

import java.util.HashSet;
import java.util.Set;

import net.sf.opeum.emf.extraction.EmfExtractionPhase;
import net.sf.opeum.feature.TransformationProcess;
import net.sf.opeum.metamodel.core.INakedElement;
import net.sf.opeum.metamodel.workspace.INakedModelWorkspace;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;

public final class SynchronizationProcessRunner extends Job{
	private Set<EObject> emfChanges;
	private Set<NakedUmlSynchronizationListener> synchronizationListener;
	private TransformationProcess transformationProcess;
	public SynchronizationProcessRunner(TransformationProcess transformationProcess,Set<NakedUmlSynchronizationListener> synchronizationListener2,Set<? extends EObject> emfChanges){
		super("Synchronizing Opium Metadata with UML");
		this.transformationProcess = transformationProcess;
		synchronized(emfChanges){
			this.emfChanges = new HashSet<EObject>(emfChanges);
			emfChanges.clear();
		}
		this.synchronizationListener = synchronizationListener2;
	}
	public IStatus run(IProgressMonitor monitor){
		try{
			monitor.beginTask("Synchronizing Opium Metadata", 50);
			if(emfChanges.size() > 0){
				long start = System.currentTimeMillis();
				Set<INakedElement> changedNakedElements = new HashSet<INakedElement>();
				for(Object object:transformationProcess.processElements(emfChanges, EmfExtractionPhase.class, new ProgressMonitorTransformationLog(monitor, 50))){
					if(object instanceof INakedElement){
						changedNakedElements.add((INakedElement) object);
					}
				}
				final INakedModelWorkspace findModel = transformationProcess.findModel(INakedModelWorkspace.class);
				for(NakedUmlSynchronizationListener listener:synchronizationListener){
					listener.synchronizationComplete(findModel, changedNakedElements);
				}
				System.out.println("Synchronization took " + (System.currentTimeMillis() - start));
			}
			return new Status(IStatus.OK, NakedUmlEclipsePlugin.getId(), "Opium Metadata Synchronized Successfully");
		}catch(Exception e){
			e.printStackTrace();
			return new Status(IStatus.ERROR, NakedUmlEclipsePlugin.getId(), "Synchronization Failed", e);
		}finally{
			monitor.done();
		}
	}
}