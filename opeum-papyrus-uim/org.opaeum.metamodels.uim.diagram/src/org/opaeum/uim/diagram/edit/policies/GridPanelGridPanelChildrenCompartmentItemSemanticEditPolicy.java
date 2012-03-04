package org.opaeum.uim.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uim.diagram.edit.commands.BuiltInActionCreateCommand;
import org.opaeum.uim.diagram.edit.commands.UimFieldCreateCommand;
import org.opaeum.uim.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class GridPanelGridPanelChildrenCompartmentItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public GridPanelGridPanelChildrenCompartmentItemSemanticEditPolicy(){
		super(UimElementTypes.GridPanel_2001);
	}
	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req){
		if(UimElementTypes.UimField_3001 == req.getElementType()){
			return getGEFWrapper(new UimFieldCreateCommand(req));
		}
		if(UimElementTypes.BuiltInAction_3002 == req.getElementType()){
			return getGEFWrapper(new BuiltInActionCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
