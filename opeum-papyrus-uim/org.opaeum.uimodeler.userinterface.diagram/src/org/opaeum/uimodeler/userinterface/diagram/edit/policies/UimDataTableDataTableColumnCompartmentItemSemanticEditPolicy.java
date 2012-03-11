package org.opaeum.uimodeler.userinterface.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.BuiltInAction2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.LinkToEntity2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.OperationAction2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.UimField2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimDataTableDataTableColumnCompartmentItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public UimDataTableDataTableColumnCompartmentItemSemanticEditPolicy(){
		super(UimElementTypes.UimDataTable_3009);
	}
	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req){
		if(UimElementTypes.UimField_3010 == req.getElementType()){
			return getGEFWrapper(new UimField2CreateCommand(req));
		}
		if(UimElementTypes.LinkToEntity_3011 == req.getElementType()){
			return getGEFWrapper(new LinkToEntity2CreateCommand(req));
		}
		if(UimElementTypes.BuiltInAction_3012 == req.getElementType()){
			return getGEFWrapper(new BuiltInAction2CreateCommand(req));
		}
		if(UimElementTypes.OperationAction_3014 == req.getElementType()){
			return getGEFWrapper(new OperationAction2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
