package org.opaeum.uimodeler.userinterface.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.BuiltInActionButton2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.OperationButton2CreateCommand;
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
		if(UimElementTypes.BuiltInActionButton_3023 == req.getElementType()){
			return getGEFWrapper(new BuiltInActionButton2CreateCommand(req));
		}
		if(UimElementTypes.OperationButton_3024 == req.getElementType()){
			return getGEFWrapper(new OperationButton2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
