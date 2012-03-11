package org.opaeum.uimodeler.abstractactionbar.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.uml.diagram.common.commands.DuplicateNamedElementCommand;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.commands.BuiltInActionCreateCommand;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.commands.OperationActionCreateCommand;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.commands.TransitionActionCreateCommand;
import org.opaeum.uimodeler.abstractactionbar.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class AbstractActionBarItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public AbstractActionBarItemSemanticEditPolicy(){
		super(UimElementTypes.AbstractActionBar_1000);
	}
	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req){
		if(UimElementTypes.BuiltInAction_2001 == req.getElementType()){
			return getGEFWrapper(new BuiltInActionCreateCommand(req));
		}
		if(UimElementTypes.TransitionAction_2002 == req.getElementType()){
			return getGEFWrapper(new TransitionActionCreateCommand(req));
		}
		if(UimElementTypes.OperationAction_2003 == req.getElementType()){
			return getGEFWrapper(new OperationActionCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req){
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		Diagram currentDiagram = null;
		if(getHost() instanceof IGraphicalEditPart){
			currentDiagram = ((IGraphicalEditPart) getHost()).getNotationView().getDiagram();
		}
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req, currentDiagram));
	}
	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends DuplicateNamedElementCommand{
		/**
		 * @generated
		 */
		private Diagram diagram;
		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain,DuplicateElementsRequest req,Diagram currentDiagram){
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap(), currentDiagram);
			this.diagram = currentDiagram;
		}
	}
}
