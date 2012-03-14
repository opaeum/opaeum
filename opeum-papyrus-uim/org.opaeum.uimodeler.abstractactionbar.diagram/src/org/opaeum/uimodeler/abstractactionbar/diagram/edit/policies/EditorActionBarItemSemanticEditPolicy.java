package org.opaeum.uimodeler.abstractactionbar.diagram.edit.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.commands.wrappers.EMFtoGMFCommandWrapper;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.EditorActionBarActionBarChildrenCompartmentEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.OperationButtonEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.part.UimVisualIDRegistry;
import org.opaeum.uimodeler.abstractactionbar.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class EditorActionBarItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public EditorActionBarItemSemanticEditPolicy(){
		super(UimElementTypes.EditorActionBar_2011);
	}
	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req){
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(true);
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if(annotation == null){
			// there are indirectly referenced children, need extra commands: false
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			List<EObject> todestroy = new ArrayList<EObject>();
			todestroy.add(req.getElementToDestroy());
			//cmd.add(new org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand(req));
			cmd.add(new EMFtoGMFCommandWrapper(new DeleteCommand(getEditingDomain(), todestroy)));
		}else{
			cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}
	/**
	 * @generated
	 */
	protected void addDestroyChildNodesCommand(ICompositeCommand cmd){
		View view = (View) getHost().getModel();
		for(Iterator<?> nit = view.getChildren().iterator();nit.hasNext();){
			Node node = (Node) nit.next();
			switch(UimVisualIDRegistry.getVisualID(node)){
			case EditorActionBarActionBarChildrenCompartmentEditPart.VISUAL_ID:
				for(Iterator<?> cit = node.getChildren().iterator();cit.hasNext();){
					Node cnode = (Node) cit.next();
					switch(UimVisualIDRegistry.getVisualID(cnode)){
					case BuiltInLinkEditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case LinkToQueryEditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case OperationButtonEditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case BuiltInActionButtonEditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case TransitionButtonEditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					}
				}
				break;
			}
		}
	}
}
