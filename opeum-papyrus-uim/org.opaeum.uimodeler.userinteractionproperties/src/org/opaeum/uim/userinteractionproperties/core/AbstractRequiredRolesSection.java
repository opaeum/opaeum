package org.opaeum.uim.userinteractionproperties.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public abstract class AbstractRequiredRolesSection extends AbstractUmlReferenceLookupSection{
	protected abstract EReference getConstraintFeature();
	protected abstract UserInteractionConstraint getUserInteractionConstraint();
	@Override
	protected EObject getFeatureOwner(){
		return getUserInteractionConstraint();
	}
	@Override
	protected EObject getFeatureOwnerOnLookup(){
		if(getUserInteractionConstraint() == null){
			Command cmd = SetCommand.create(getEditingDomain(), getEObject(), getConstraintFeature(),
					ConstraintFactory.eINSTANCE.createUserInteractionConstraint());
			getEditingDomain().getCommandStack().execute(cmd);
		}
		return getUserInteractionConstraint();
	}
	@Override
	protected List<? extends EObject> getAvailableChoices(){
		UserInteractionElement ui = (UserInteractionElement) getEObject();
		Element umlElement = UmlUimLinks.getCurrentUmlLinks(ui).getNearestUmlElement(ui);
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(umlElement);
		Collection<EObject> types = typeCacheAdapter.getReachableObjectsOfType(umlElement, UMLPackage.eINSTANCE.getClass_());
		List<EObject> result = new ArrayList<EObject>();
		for(EObject eObject:types){
			if(StereotypesHelper.hasStereotype((Element) eObject, "BusinessRole")){
				result.add(eObject);
			}
		}
		return result;
	}
	@Override
	protected UmlReference createNewReference(){
		return ConstraintFactory.eINSTANCE.createRequiredRole();
	}
	protected List<? extends UmlReference> getCurrentUmlReferences(){
		if(getUserInteractionConstraint() != null){
			return getUserInteractionConstraint().getRequiredRoles();
		}else{
			return Collections.emptyList();
		}
	}
	@Override
	protected EStructuralFeature getFeature(){
		return ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiredRoles();
	}
	@Override
	protected String getLabelText(){
		return "Required Roles:";
	}
}
