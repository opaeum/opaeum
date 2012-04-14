package org.opaeum.uimodeler.cubequery.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.uml.diagram.common.commands.DuplicateNamedElementCommand;
import org.opaeum.uimodeler.cubequery.diagram.edit.commands.ColumnAxisEntryCreateCommand;
import org.opaeum.uimodeler.cubequery.diagram.edit.commands.RowAxisEntryCreateCommand;
import org.opaeum.uimodeler.cubequery.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class CubeQueryItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public CubeQueryItemSemanticEditPolicy(){
		super(UimElementTypes.CubeQuery_1000);
	}
	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req){
		if(UimElementTypes.ColumnAxisEntry_2001 == req.getElementType()){
			return getGEFWrapper(new ColumnAxisEntryCreateCommand(req));
		}
		if(UimElementTypes.RowAxisEntry_2002 == req.getElementType()){
			return getGEFWrapper(new RowAxisEntryCreateCommand(req));
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
