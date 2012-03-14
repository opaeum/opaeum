package org.opaeum.uimodeler.userinterface.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.BuiltInActionButtonCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.BuiltInLinkCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.GridPanel2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.HorizontalPanel2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.LinkToQueryCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.OperationButtonCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.TransitionButtonCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.UimDataTableCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.UimFieldCreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.edit.commands.VerticalPanel2CreateCommand;
import org.opaeum.uimodeler.userinterface.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class GridPanelGridPanelChildrenCompartment2ItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public GridPanelGridPanelChildrenCompartment2ItemSemanticEditPolicy(){
		super(UimElementTypes.GridPanel_3017);
	}
	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req){
		if(UimElementTypes.UimField_3001 == req.getElementType()){
			return getGEFWrapper(new UimFieldCreateCommand(req));
		}
		if(UimElementTypes.BuiltInActionButton_3018 == req.getElementType()){
			return getGEFWrapper(new BuiltInActionButtonCreateCommand(req));
		}
		if(UimElementTypes.HorizontalPanel_3003 == req.getElementType()){
			return getGEFWrapper(new HorizontalPanel2CreateCommand(req));
		}
		if(UimElementTypes.VerticalPanel_3004 == req.getElementType()){
			return getGEFWrapper(new VerticalPanel2CreateCommand(req));
		}
		if(UimElementTypes.TransitionButton_3019 == req.getElementType()){
			return getGEFWrapper(new TransitionButtonCreateCommand(req));
		}
		if(UimElementTypes.OperationButton_3020 == req.getElementType()){
			return getGEFWrapper(new OperationButtonCreateCommand(req));
		}
		if(UimElementTypes.LinkToQuery_3021 == req.getElementType()){
			return getGEFWrapper(new LinkToQueryCreateCommand(req));
		}
		if(UimElementTypes.BuiltInLink_3022 == req.getElementType()){
			return getGEFWrapper(new BuiltInLinkCreateCommand(req));
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
