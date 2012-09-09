package org.opaeum.eclipse.newchild;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public interface ICreateChildAction{
	public boolean isPotentialParent(EObject e);
	public CreateChildAction createAction(IWorkbenchPart workbenchPart,IStructuredSelection selection, OpaeumLibrary library);
}
