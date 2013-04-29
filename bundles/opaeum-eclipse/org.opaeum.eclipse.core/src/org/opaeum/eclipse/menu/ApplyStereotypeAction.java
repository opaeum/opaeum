package org.opaeum.eclipse.menu;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;

public class ApplyStereotypeAction extends StaticSelectionCommandAction {
	Stereotype steretype;

	public ApplyStereotypeAction(Stereotype steretype) {
		super();
		this.steretype = steretype;
		setText(steretype.getQualifiedName());
		super.editingDomain = (EditingDomain) PlatformUI.getWorkbench().  getActiveWorkbenchWindow().getActivePage().getActiveEditor().getAdapter(EditingDomain.class);

	}

	@Override
	protected Command createActionCommand(EditingDomain editingDomain,
			Collection<?> collection) {
		CompoundCommand cc = new CompoundCommand();
		for (Object object : collection) {
			EObject adaptObject = EmfElementFinder.adaptObject(object);
			if (adaptObject instanceof Element) {
				cc.append(new ApplyStereotypeCommand((Element) adaptObject,
						steretype));
			}
		}
		return cc;
	}

}
