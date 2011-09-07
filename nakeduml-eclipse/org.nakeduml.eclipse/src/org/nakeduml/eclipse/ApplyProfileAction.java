package org.nakeduml.eclipse;

import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;

public class ApplyProfileAction implements IObjectActionDelegate{
	private IStructuredSelection selection;
	@Override
	public void run(IAction arg0){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			if(element instanceof Model){
				Model model = (Model) element;
				if(arg0.getId().equals("org.nakeduml.eclipse.ApplyNakedUMLProfileForBPMAction")){
					applyProfile(model, "OpiumBPMProfile.uml");
				}else{
					applyNakedUmlProfile(model);
				}
			}
		}
	}
	public static Profile applyNakedUmlProfile(Model model){
		String profileName = "OpiumStandardProfile.uml";
		return applyProfile(model, profileName);
	}
	public static Profile applyProfile(Model model,String profileName){
		Profile appliedProfile = getAppliedProfile(model, profileName);
		if(appliedProfile == null){
			Resource resource = model.eResource().getResourceSet().getResource(URI.createURI(StereotypeNames.MODELS_PATHMAP + "profiles/" + profileName), true);
			appliedProfile = (Profile) resource.getContents().get(0);
			model.applyProfile(appliedProfile);
		}else if(model.getProfileApplication(appliedProfile).getAppliedDefinition() != appliedProfile.getDefinition()){
			model.applyProfile(appliedProfile);
		}
		return appliedProfile;
	}
	private static Profile getAppliedProfile(Model model,String profileName){
		for(Profile profile:model.getAllAppliedProfiles()){
			if(!profile.eIsProxy() && profile.eResource().getURI().lastSegment().equals(profileName)){
				return profile;
			}
		}
		return null;
	}
	@Override
	public void selectionChanged(IAction arg0,ISelection selection){
		this.selection = (IStructuredSelection) selection;
	}
	@Override
	public void setActivePart(IAction arg0,IWorkbenchPart arg1){
	}
}
