package org.opaeum.eclipse.uml.propertysections.ocl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;

public abstract class AutoCreateOpaqueExpressionComposite extends OpaqueExpressionComposite{
	public AutoCreateOpaqueExpressionComposite(Composite parent,FormToolkit toolkit){
		super(parent, toolkit);
	}
	public AutoCreateOpaqueExpressionComposite(Composite parent,FormToolkit toolkit, int style){
		super(parent, toolkit,style);
	}
	public abstract EReference getValueSpecificationFeature();
	@Override
	protected void fireOclChanged(String text){
		boolean hasOclExpression = containsExpression(text);
		if(getValueSpecificatonOwner() != null && !hasOclExpression ){
			Object vs = getValueSpecificatonOwner().eGet(getValueSpecificationFeature());
			if(vs != null && getValueSpecificationFeature().getUpperBound() == 1){
				// Remove valueSpecification
				getEditingDomain().getCommandStack().execute(
						SetCommand.create(getEditingDomain(), getValueSpecificatonOwner(), getValueSpecificationFeature(), null));
				this.oclBodyOwner = null;
			}
		}
		if(super.oclBodyOwner == null && hasOclExpression){

			Object vs = getValueSpecificatonOwner().eGet(getValueSpecificationFeature());
			if(vs instanceof OpaqueExpression){
				super.oclBodyOwner = (NamedElement) vs;
			}else if(getValueSpecificationFeature().getUpperBound() == 1){
				OpaqueExpression oe = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createOpaqueExpression();
				oe.setName("oclExpression");
				super.oclBodyOwner = oe;//NB!! BEFORE command otherwise the OclComposite reverts to DEFAULT_TEXT
				getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getValueSpecificatonOwner(), getValueSpecificationFeature(), oe));
			}else{
				throw new IllegalStateException(
						"Please override 'fireOclChanged(String text)' to populate the appropriate valueSpecificatonOwner before the ocl is changed on the opaqueExpression");
			}
		}
		if(hasOclExpression ){
			super.fireOclChanged(text);
		}
	}
	public abstract EObject getValueSpecificatonOwner();
}
