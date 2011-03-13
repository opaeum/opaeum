package org.nakeduml.eclipse;

import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;

public class ImportSimpleTypesAction  implements IObjectActionDelegate {

	private IStructuredSelection selection;

	@Override
	public void run(IAction arg0) {
		for (Iterator<?> it = selection.iterator(); it.hasNext();) {
			Object element = it.next();
			if (element instanceof Model) {
				Model model = (Model) element;
				Resource resource = model.eResource().getResourceSet().getResource(URI.createURI("pathmap://NAKEDUML_LIBRARIES/NakedUMLSimpleTypes.library.uml"), true);
				Model library=(Model) resource.getContents().get(0);
				model.createPackageImport(library);
			}
		}
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection selection) {
		this.selection = (IStructuredSelection) selection;

	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {

	}

}
