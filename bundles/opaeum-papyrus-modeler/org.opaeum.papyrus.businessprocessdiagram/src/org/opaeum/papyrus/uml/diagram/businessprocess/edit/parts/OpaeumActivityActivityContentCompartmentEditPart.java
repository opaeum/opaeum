package org.opaeum.papyrus.uml.diagram.businessprocess.edit.parts;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.extendedtypes.types.IExtendedHintedElementType;
import org.eclipse.papyrus.infra.extendedtypes.util.ElementTypeUtils;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityActivityContentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.policies.CustomActivityActivityContentCompartmentItemSemanticEditPolicy;
import org.eclipse.papyrus.uml.diagram.activity.providers.UMLElementTypes;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.opaeum.papyrus.uml.diagram.businessprocess.edit.commands.OpaeumCallBehaviorActionCreateCommand;

public final class OpaeumActivityActivityContentCompartmentEditPart extends ActivityActivityContentCompartmentEditPart{
	public OpaeumActivityActivityContentCompartmentEditPart(View view){
		super(view);
	}
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CustomActivityActivityContentCompartmentItemSemanticEditPolicy(){
			@Override
			protected Command getCreateCommand(CreateElementRequest req){
				IElementType requestElementType = req.getElementType();
				if(requestElementType == null){
					return super.getCreateCommand(req);
				}
				IElementType baseElementType = requestElementType;
				boolean isExtendedType = false;
				if(requestElementType instanceof IExtendedHintedElementType){
					baseElementType = ElementTypeUtils.getClosestDiagramType(requestElementType);
					if(baseElementType != null){
						isExtendedType = true;
					}else{
						// no reference element type ID. using the closest super element type to give more opportunities, but can lead to bugs.
						baseElementType = ElementTypeUtils.findClosestNonExtendedElementType((IExtendedHintedElementType) requestElementType);
						isExtendedType = true;
					}
				}
				if(!isExtendedType && baseElementType == UMLElementTypes.CallBehaviorAction_3008){
					return getGEFWrapper(new OpaeumCallBehaviorActionCreateCommand(req));
				}
				//TODO calOperation,sendSignal etc.
				return super.getCreateCommand(req);
			}
		});
	}
}