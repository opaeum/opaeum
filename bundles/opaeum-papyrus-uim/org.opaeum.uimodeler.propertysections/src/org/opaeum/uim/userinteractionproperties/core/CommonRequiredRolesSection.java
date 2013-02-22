package org.opaeum.uim.userinteractionproperties.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.util.UmlUimLinks;

public abstract class CommonRequiredRolesSection extends AbstractUmlReferenceLookupSection{
	public CommonRequiredRolesSection(){
		super();
	}

	@Override
	protected List<? extends EObject> getAvailableChoices(){
		UserInteractionElement ui = (UserInteractionElement) getFeatureOwner(getSelectedObject());
		Element umlElement = UmlUimLinks.getCurrentUmlLinks(ui).getNearestUmlElement(ui);
		Collection<EObject> types = OpaeumEclipseContext.getReachableObjectsOfType(umlElement, UMLPackage.eINSTANCE.getClass_());
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
		if(getFeatureOwner(getSelectedObject()) != null){
			return ((RootUserInteractionConstraint) getFeatureOwner(getSelectedObject())).getRequiredRoles();
		}else{
			return Collections.emptyList();
		}
	}

	@Override
	protected EStructuralFeature getFeature(){
		return ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiredRoles();
	}

	@Override
	public String getLabelText(){
		return "Required Roles:";
	}
}