package org.opaeum.uimodeler.abstractactionbar.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.commands.BuiltInActionButtonCreateCommand;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.commands.BuiltInLinkCreateCommand;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.commands.LinkToQueryCreateCommand;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.commands.OperationButtonCreateCommand;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.commands.TransitionButtonCreateCommand;
import org.opaeum.uimodeler.abstractactionbar.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class EditorActionBarActionBarChildrenCompartmentItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public EditorActionBarActionBarChildrenCompartmentItemSemanticEditPolicy(){
		super(UimElementTypes.EditorActionBar_2011);
	}
	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req){
		if(UimElementTypes.BuiltInLink_3001 == req.getElementType()){
			return getGEFWrapper(new BuiltInLinkCreateCommand(req));
		}
		if(UimElementTypes.LinkToQuery_3002 == req.getElementType()){
			return getGEFWrapper(new LinkToQueryCreateCommand(req));
		}
		if(UimElementTypes.OperationButton_3003 == req.getElementType()){
			return getGEFWrapper(new OperationButtonCreateCommand(req));
		}
		if(UimElementTypes.BuiltInActionButton_3004 == req.getElementType()){
			return getGEFWrapper(new BuiltInActionButtonCreateCommand(req));
		}
		if(UimElementTypes.TransitionButton_3005 == req.getElementType()){
			return getGEFWrapper(new TransitionButtonCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
