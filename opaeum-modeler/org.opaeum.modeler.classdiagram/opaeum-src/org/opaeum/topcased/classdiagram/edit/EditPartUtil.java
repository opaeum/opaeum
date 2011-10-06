package org.opaeum.topcased.classdiagram.edit;

import org.eclipse.gef.EditPart;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;

public class EditPartUtil{
	public static void installEditPolicies(EditPart e){
		e.installEditPolicy(ClassEditPolicyConstants.ASSOCIATION_EDITPOLICY, new FixedAssociationEdgeCreationEditPolicy());
		e.installEditPolicy(ClassEditPolicyConstants.ASSOCIATIONCLASS_EDITPOLICY, new FixedAssociationClassEdgeCreationEditPolicy());
		e.installEditPolicy("ElementImportEditPolicy", new ElementImportEdgeCreationEditPolicy());
	}
}
