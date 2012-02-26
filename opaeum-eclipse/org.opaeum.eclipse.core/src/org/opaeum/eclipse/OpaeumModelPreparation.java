package org.opaeum.eclipse;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class OpaeumModelPreparation implements WorkspaceLoadListener{
	OpaeumEclipseContext context;
	@Override
	public void workspaceLoaded(final EmfWorkspace w){
		context.executeAndForget(new AbstractCommand(){
			@Override
			public void execute(){
				for(Package model:w.getPotentialGeneratingModels()){
					boolean dirty = false;
					if(model instanceof Model || model instanceof Profile){
						if(!EmfWorkspace.isReadOnly(model.eResource())){
							EMap<String,String> d = StereotypesHelper.getNumlAnnotation(model).getDetails();
							if(d.get("uuid") == null || d.get("uuid").trim().length() == 0){
								d.put("uuid", Math.round(Math.random() * 1000000) + "");
								dirty = true;
							}
						}
						if(model instanceof Model){
							Profile opaeumStandardProfile=null;
							for(Profile profile:model.getAllAppliedProfiles()){
								if(profile.getName().equals("OpaeumStandardProfile")){
									opaeumStandardProfile = profile;
								}
							}
							if(opaeumStandardProfile == null){
								opaeumStandardProfile = ProfileApplier.applyNakedUmlProfile((Model) model);
							}
							Stereotype modelStereotype = opaeumStandardProfile.getOwnedStereotype(StereotypeNames.MODEL);
							if(StereotypesHelper.hasStereotype(model, StereotypeNames.MODEL_LIBRARY)){
								modelStereotype = opaeumStandardProfile.getOwnedStereotype(StereotypeNames.MODEL_LIBRARY);
							}
							if(!model.isStereotypeApplied(modelStereotype)){
								dirty = true;
								model.applyStereotype(modelStereotype);
							}
							if(model.getValue(modelStereotype, "mappedImplementationPackage") == null){
								dirty = true;
								model.setValue(modelStereotype, "mappedImplementationPackage", context.getConfig().getMavenGroupId() + "."
										+ model.getName().toLowerCase());
							}
						}
						try{
							if(dirty){
								model.eResource().save(new HashMap<Object,Object>());
							}
							;
						}catch(IOException e){
							e.printStackTrace();
						}
					}
				}
			}
			@Override
			public void redo(){
			}
		});
	}
	public OpaeumModelPreparation(OpaeumEclipseContext context){
		super();
		this.context = context;
	}
}
