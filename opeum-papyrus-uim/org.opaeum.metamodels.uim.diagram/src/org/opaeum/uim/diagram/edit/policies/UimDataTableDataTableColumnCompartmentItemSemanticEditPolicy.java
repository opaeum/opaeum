package org.opaeum.uim.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uim.diagram.edit.commands.UimField2CreateCommand;
import org.opaeum.uim.diagram.providers.UimElementTypes;

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
		return super.getCreateCommand(req);
	}
}
