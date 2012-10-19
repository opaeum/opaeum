package org.opaeum.eclipse.commands;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public abstract class StereotypeValueCommand extends CompoundCommand{
	private String featureName;
	private String stereotypeName;
	private String profileName;
	private EStructuralFeature feature;
	private EObject stereotypeApplication;
	protected Element owner;
	protected EditingDomain editingDomain;
	public StereotypeValueCommand(EditingDomain ed,Element eOwner,String featureName2){
		this.owner = eOwner;
		this.editingDomain = ed;
		featureName=featureName2;
	}
	@Override
	protected boolean prepare(){
		if(getFeature() == null){
			maybeAppendApplyStereotypeCommand();
		}
		if(getFeature() == null){
			return false;
		}
		return prepareImpl() && super.prepare();
	}
	protected abstract boolean prepareImpl();
	protected void maybeAppendApplyStereotypeCommand(){
		if(getStereotypeApplication() == null){
			ApplyStereotypeCommand st = null;
			if(getProfileName() != null && stereotypeName != null){
				Profile p = ProfileApplier.getProfile(owner, getProfileName());
				st = new ApplyStereotypeCommand(owner, p.getOwnedStereotype(stereotypeName));
			}else if(stereotypeName != null){
				st = new ApplyStereotypeCommand(owner, stereotypeName);
			}
			if(st != null){
				append(st);
				feature = st.getStereotypes().get(0).getDefinition().getEStructuralFeature(featureName);
			}
		}
	}
	protected void maybeRemoveFromAnnotation(EditingDomain editingDomain,Object oldValue){
		if(oldValue != null){
			EAnnotation ann = StereotypesHelper.getNumlAnnotation(owner);
			if(ann != null && ann.getContents().contains(oldValue)){
				append(RemoveCommand.create(editingDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), oldValue));
			}
		}
	}
	protected EObject getStereotypeApplication(){
		if(stereotypeApplication == null){
			resolve();
		}
		return this.stereotypeApplication;
	}
	protected EStructuralFeature getFeature(){
		if(feature == null){
			resolve();
		}
		return feature;
	}
	private void resolve(){
		for(EObject csa:owner.getStereotypeApplications()){
			EStructuralFeature csf = csa.eClass().getEStructuralFeature(featureName);
			if(csf != null){
				feature = csf;
				stereotypeApplication = csa;
				break;
			}
		}
	}
	public String getStereotypeName(){
		return stereotypeName;
	}
	public void setStereotypeName(String stereotypeName){
		this.stereotypeName = stereotypeName;
	}
	public String getFeatureName(){
		return featureName;
	}
	public void setFeatureName(String featureName){
		this.featureName = featureName;
	}
	protected void maybeAddToAnnotation(EditingDomain editingDomain,Object value,boolean isCompositeFeature){
		if(isCompositeFeature && value instanceof EObject && !(value instanceof EEnumLiteral)){
			// Add to the containment tree
			EAnnotation ann = StereotypesHelper.getNumlAnnotation(owner);
			if(ann == null){
				ann = EcoreFactory.eINSTANCE.createEAnnotation();
				ann.setSource(StereotypeNames.NUML_ANNOTATION);
				append(AddCommand.create(editingDomain, owner, EcorePackage.eINSTANCE.getEModelElement_EAnnotations(), ann));
			}
			if(!ann.getContents().contains(value)){
				append(AddCommand.create(editingDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), value));
			}
		}
	}
	public String getProfileName(){
		return profileName;
	}
	public void setProfileName(String profileName){
		this.profileName = profileName;
	}
}
