package org.opaeum.uimodeler.userinterface.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.BuiltInActionCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.GridPanel2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.HorizontalPanel2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.LinkToEntityCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.LinkToOperationCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.OperationActionCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.TransitionActionCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.UimDataTableCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.UimFieldCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.VerticalPanel2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.providers.UimElementTypes;

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
		if(UimElementTypes.HorizontalPanel_3003 == req.getElementType()){
			return getGEFWrapper(new HorizontalPanel2CreateCommand(req));
		}
		if(UimElementTypes.VerticalPanel_3004 == req.getElementType()){
			return getGEFWrapper(new VerticalPanel2CreateCommand(req));
		}
		if(UimElementTypes.TransitionAction_3005 == req.getElementType()){
			return getGEFWrapper(new TransitionActionCreateCommand(req));
		}
		if(UimElementTypes.OperationAction_3006 == req.getElementType()){
			return getGEFWrapper(new OperationActionCreateCommand(req));
		}
		if(UimElementTypes.LinkToOperation_3007 == req.getElementType()){
			return getGEFWrapper(new LinkToOperationCreateCommand(req));
		}
		if(UimElementTypes.LinkToEntity_3008 == req.getElementType()){
			return getGEFWrapper(new LinkToEntityCreateCommand(req));
		}
		if(UimElementTypes.UimDataTable_3009 == req.getElementType()){
			return getGEFWrapper(new UimDataTableCreateCommand(req));
		}
		if(UimElementTypes.GridPanel_3017 == req.getElementType()){
			return getGEFWrapper(new GridPanel2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
