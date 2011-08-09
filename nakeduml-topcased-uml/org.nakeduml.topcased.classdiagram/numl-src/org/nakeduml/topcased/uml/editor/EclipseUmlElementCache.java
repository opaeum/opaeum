package org.nakeduml.topcased.uml.editor;

import java.io.IOException;
import java.util.HashMap;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.nakeduml.eclipse.ApplyProfileAction;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;

public final class EclipseUmlElementCache extends UmlElementCache{
	private IProgressMonitor monitor;
	EclipseUmlElementCache(NakedUmlConfig cfg){
		super(new EclipseEmfResourceHelper(), cfg);
	}
	public void setMonitor(IProgressMonitor monitor){
		getTransformationProcess().setLog(new ProgressMonitorTransformationLog(monitor));
		this.monitor = monitor;
	}
	
	@Override
	public void emfWorkspaceLoaded(EmfWorkspace w){
		monitor.worked(15);
		for(Package pkg:w.getPrimaryModels()){
			if(pkg instanceof Model){
				Model model = (Model) pkg;
				Profile pf = ApplyProfileAction.applyNakedUmlProfile(model);
				Stereotype st = pf.getOwnedStereotype(StereotypeNames.PACKAGE);
				if(!model.isStereotypeApplied(st)){
					model.applyStereotype(st);
				}
				if(model.getValue(st, "mappedImplementationPackage") == null){
					model.setValue(st, "mappedImplementationPackage", cfg.getMavenGroupId() + "." + model.getName().toLowerCase());
				}
				try{
					model.eResource().save(new HashMap());
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	public void setCurrentEmfWorkspace(EmfWorkspace e){
		transformationProcess.replaceModel(e);
		INakedModelWorkspace nws = getNakedWorkspace();
		nws.clearGeneratingModelOrProfiles();
		Package generatingModel = e.getGeneratingModelsOrProfiles().iterator().next();//Should  be exactly one
		nws.addGeneratingRootObject((INakedRootObject) nws.getModelElement(resourceHelper. getId(generatingModel)));
		this.currentEmfWorkspace=e;
	}
	public EmfWorkspace getCurrentEmfWorkspace(){
		return currentEmfWorkspace;
	}

}