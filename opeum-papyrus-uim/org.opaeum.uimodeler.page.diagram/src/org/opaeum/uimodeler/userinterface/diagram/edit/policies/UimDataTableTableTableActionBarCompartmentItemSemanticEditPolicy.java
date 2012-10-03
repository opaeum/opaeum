package org.opaeum.uimodeler.userinterface.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.BuiltInActionButton3CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.InvocationButton3CreateCommand;
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
		if(UimElementTypes.BuiltInActionButton_3025 == req.getElementType()){
			return getGEFWrapper(new BuiltInActionButton3CreateCommand(req));
		}
		if(UimElementTypes.InvocationButton_3026 == req.getElementType()){
			return getGEFWrapper(new InvocationButton3CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
