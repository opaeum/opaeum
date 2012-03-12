package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class VisibilityUserInteractionConstraintBooleanFeaturesSection extends AbstractUserInteractionConstraintBooleanFeaturesSection{
	@Override
	protected EReference getConstraintContainingFeature(){
		EReference v = ConstraintPackage.eINSTANCE.getConstrainedObject_Visibility();
		return v;
	}
}
