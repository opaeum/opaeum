package org.opaeum.eclipse.javasync;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Model;

public class RecompileModelAction extends AbstractRecompileModelAction{
	public RecompileModelAction(IStructuredSelection selection){
		super(selection, "Recompile Model");
	}
	@Override
	protected Model retrieveModel(Object element){
		if(element instanceof Model){
		}else if(element instanceof IAdaptable){
			element = ((IAdaptable) element).getAdapter(EObject.class);
		}
		if(element instanceof Model){
			return (Model) element;
		}
		return null;
	}
}
