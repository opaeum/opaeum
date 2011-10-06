package org.opaeum.topcased.propertysections.ocl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;

public abstract class OpaqueExpressionComposite extends OclBodyComposite{
	public OpaqueExpressionComposite(Composite parent,FormToolkit toolkit){
		super(parent, toolkit);
	}

	public OpaqueExpressionComposite(Composite parent,FormToolkit toolkit,int border){
		super(parent,toolkit,border);
	}

	@Override
	public EStructuralFeature getBodiesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueExpression_Body();
	}

	@Override
	public EStructuralFeature getLanguagesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueExpression_Language();
	}

	public void setOclContext(NamedElement context,final OpaqueExpression vp){
		EObject container = context;
		while(!(isOclContext(container = EmfElementFinder.getContainer(container)))){
		}
		setOclContextImpl((NamedElement) container, vp);
	}
}