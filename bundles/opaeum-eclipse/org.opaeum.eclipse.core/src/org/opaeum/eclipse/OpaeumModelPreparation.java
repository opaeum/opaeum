package org.opaeum.eclipse;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class OpaeumModelPreparation implements WorkspaceLoadListener{
	private OpenUmlFile context;
	@Override
	public void workspaceLoaded(final EmfWorkspace w){
		for(Package model:w.getPrimaryRootObjects()){
			if(model instanceof Model || model instanceof Profile){
				if(!EmfWorkspace.isReadOnly(model.eResource())){
					EAnnotation ann = StereotypesHelper.getNumlAnnotation(model);
					if(ann == null){
						ann = EcoreFactory.eINSTANCE.createEAnnotation();
						ann.setSource(StereotypeNames.NUML_ANNOTATION);
						context.executeAndForget(AddCommand.create(context.getEditingDomain(), model,
								EcorePackage.eINSTANCE.getEModelElement_EAnnotations(), ann));
					}
					if(!ann.getDetails().contains("uuid") || ann.getDetails().get("uuid").length() == 0){
						EStringToStringMapEntryImpl entry = (EStringToStringMapEntryImpl) EcoreFactory.eINSTANCE.create(EcorePackage.eINSTANCE
								.getEStringToStringMapEntry());
						entry.setKey("uuid");
						entry.setValue(Math.round(Math.random() * 1000000) + "");
						context.executeAndForget(AddCommand.create(context.getEditingDomain(), ann, EcorePackage.eINSTANCE.getEAnnotation_Details(),
								entry));
					}
				}
			}
			if(model instanceof Model && !StereotypesHelper.hasStereotype(model, StereotypeNames.MODEL)){
				Profile opaeumStandardProfile = ProfileApplier.getProfile(model, StereotypeNames.OPAEUM_STANDARD_PROFILE);
				if(opaeumStandardProfile != null){
					Stereotype modelStereotype = opaeumStandardProfile.getOwnedStereotype(StereotypeNames.MODEL);
					context.executeAndForget(new ApplyStereotypeCommand(model, modelStereotype));
					// if(model.getValue(modelStereotype, "mappedImplementationPackage") == null){
					// dirty = true;
					// model.setValue(modelStereotype, "mappedImplementationPackage", context.getConfig().getMavenGroupId() + "."
					// + model.getName().toLowerCase());
				}
			}
		}
	}
	public OpaeumModelPreparation(OpenUmlFile context){
		super();
		this.context = context;
	}
}
