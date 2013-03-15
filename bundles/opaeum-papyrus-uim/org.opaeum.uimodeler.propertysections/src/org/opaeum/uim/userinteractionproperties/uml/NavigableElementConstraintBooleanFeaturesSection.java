package org.opaeum.uim.userinteractionproperties.uml;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.perspective.NavigationConstraint;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.userinteractionproperties.common.AbstractUserInteractionConstraintBooleanFeaturesSection;
import org.opaeum.uimodeler.common.IUIElementMapMap;

public class NavigableElementConstraintBooleanFeaturesSection extends AbstractUserInteractionConstraintBooleanFeaturesSection{
	@Override
	protected void createBooleanSubsections(){
		createBoolean(PerspectivePackage.eINSTANCE.getNavigationConstraint_Hidden(), "Hide", 60).setDefaultValue(false);
		super.createBooleanSubsections();
	}
	@Override
	protected EReference getConstraintContainingFeature(){
		throw new IllegalStateException();
	}
	@Override
	public UserInteractionConstraint getFeatureOwner(EObject nextObject){
		return ((IUIElementMapMap) nextObject.eResource().getResourceSet()).getElementFor((Element) nextObject);
	}
	@Override
	public void populateControls(){
		super.populateControls();
		if(getFeatureOwner(getSelectedObject()) != null){
			NavigationConstraint nc = (NavigationConstraint) getFeatureOwner(getSelectedObject());
			if(nc.isHidden()){
System.out.println();				
			}
			inheritFromParent.setEnabled(!nc.isHidden());
			requiresGroupOwnership.setEnabled(!(nc.isInheritFromParent() || nc.isHidden()));
			requiresOwnership.setEnabled(!(nc.isInheritFromParent() || nc.isHidden()));
		}
	}
	@Override
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		if(msg.getFeatureID(NavigationConstraint.class) == PerspectivePackage.NAVIGATION_CONSTRAINT__HIDDEN
				&& msg.getNotifier().equals(getFeatureOwner(getSelectedObject()))){
			isRefreshingControls = true;
			populateControls();
			isRefreshingControls = false;
		}
	}
}
