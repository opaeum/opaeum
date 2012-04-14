package org.opaeum.uimodeler.perspective.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.uml.diagram.common.commands.DuplicateNamedElementCommand;
import org.opaeum.uimodeler.perspective.diagram.edit.commands.EditorConfigurationCreateCommand;
import org.opaeum.uimodeler.perspective.diagram.edit.commands.ExplorerConfigurationCreateCommand;
import org.opaeum.uimodeler.perspective.diagram.edit.commands.PropertiesConfigurationCreateCommand;
import org.opaeum.uimodeler.perspective.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimPerspectiveItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public UimPerspectiveItemSemanticEditPolicy(){
		super(UimElementTypes.UimPerspective_1000);
	}
	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req){
		if(UimElementTypes.EditorConfiguration_2001 == req.getElementType()){
			return getGEFWrapper(new EditorConfigurationCreateCommand(req));
		}
		if(UimElementTypes.PropertiesConfiguration_2002 == req.getElementType()){
			return getGEFWrapper(new PropertiesConfigurationCreateCommand(req));
		}
		if(UimElementTypes.ExplorerConfiguration_2003 == req.getElementType()){
			return getGEFWrapper(new ExplorerConfigurationCreateCommand(req));
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
