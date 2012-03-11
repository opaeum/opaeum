package org.opaeum.uimodeler.userinterface.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.BuiltInAction3CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.OperationAction3CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimDataTableTableTableActionBarCompartmentItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public UimDataTableTableTableActionBarCompartmentItemSemanticEditPolicy(){
		super(UimElementTypes.UimDataTable_3009);
	}
	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req){
		if(UimElementTypes.BuiltInAction_3015 == req.getElementType()){
			return getGEFWrapper(new BuiltInAction3CreateCommand(req));
		}
		if(UimElementTypes.OperationAction_3016 == req.getElementType()){
			return getGEFWrapper(new OperationAction3CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
