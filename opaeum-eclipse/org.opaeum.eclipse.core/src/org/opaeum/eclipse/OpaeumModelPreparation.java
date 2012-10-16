package org.opaeum.eclipse;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.commands.ApplyOpaeumStandardProfileCommand;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class OpaeumModelPreparation implements WorkspaceLoadListener{
	private OpenUmlFile context;
	@Override
	public void workspaceLoaded(final EmfWorkspace w){
		context.executeAndForget(new AbstractCommand(){
			@Override
			public void execute(){
				for(Package model:w.getPrimaryRootObjects()){
					boolean dirty = false;
					if(model instanceof Model || model instanceof Profile){
						if(!EmfWorkspace.isReadOnly(model.eResource())){
							EMap<String,String> d = StereotypesHelper.findOrCreateNumlAnnotation(model).getDetails();
							if(d.get("uuid") == null || d.get("uuid").trim().length() == 0){
								d.put("uuid", Math.round(Math.random() * 1000000) + "");
								dirty = true;
							}
						}
						if(model instanceof Model){
							ApplyOpaeumStandardProfileCommand cmd = new ApplyOpaeumStandardProfileCommand(context.getEditingDomain(), model);
							cmd.execute();
							Profile opaeumStandardProfile=cmd.getProfile();
							Stereotype modelStereotype = opaeumStandardProfile.getOwnedStereotype(StereotypeNames.MODEL);
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
	public OpaeumModelPreparation(OpenUmlFile context){
		super();
		this.context = context;
	}
}
