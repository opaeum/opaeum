package org.opaeum.uimodeler.actionbar.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.opaeum.uimodeler.actionbar.diagram.edit.commands.BuiltInActionButtonCreateCommand;
import org.opaeum.uimodeler.actionbar.diagram.edit.commands.BuiltInLinkCreateCommand;
import org.opaeum.uimodeler.actionbar.diagram.edit.commands.InvocationButtonCreateCommand;
import org.opaeum.uimodeler.actionbar.diagram.edit.commands.LinkToQueryCreateCommand;
import org.opaeum.uimodeler.actionbar.diagram.edit.commands.TransitionButtonCreateCommand;
import org.opaeum.uimodeler.actionbar.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class ActionBarActionBarChildrenCompartmentItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public ActionBarActionBarChildrenCompartmentItemSemanticEditPolicy(){
		super(UimElementTypes.ActionBar_2011);
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
		if(UimElementTypes.InvocationButton_3003 == req.getElementType()){
			return getGEFWrapper(new InvocationButtonCreateCommand(req));
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
