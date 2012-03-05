package org.opaeum.uim.diagram.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.commands.wrappers.EMFtoGMFCommandWrapper;
import org.opaeum.uim.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class VerticalPanelItemSemanticEditPolicy extends UimBaseItemSemanticEditPolicy{
	/**
	 * @generated
	 */
	public VerticalPanelItemSemanticEditPolicy(){
		super(UimElementTypes.VerticalPanel_2006);
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
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			List<EObject> todestroy = new ArrayList<EObject>();
			todestroy.add(req.getElementToDestroy());
			//cmd.add(new org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand(req));
			cmd.add(new EMFtoGMFCommandWrapper(new org.eclipse.emf.edit.command.DeleteCommand(getEditingDomain(), todestroy)));
		}else{
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}
}
