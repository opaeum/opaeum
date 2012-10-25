package org.opaeum.eclipse.javasync;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageImport;

public class RecompileModelLibraryAction extends AbstractRecompileModelAction{
	public RecompileModelLibraryAction(IStructuredSelection selection){
		super(selection, "Recompile Library");
	}

	@Override
	protected Model retrieveModel(Object element){
		if(!(element instanceof PackageImport) &&  element instanceof IAdaptable){
			element = ((IAdaptable) element).getAdapter(EObject.class);
		}
		if(element instanceof PackageImport && ((PackageImport) element).getImportedPackage() instanceof Model){
			return (Model) ((PackageImport) element).getImportedPackage();
		}
		return null;

	}
}
