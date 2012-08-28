package org.opaeum.eclipse.uml.propertysections.ocl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;

public abstract class AutoCreateOpaqueExpressionComposite extends OpaqueExpressionComposite{
	protected EObject valueSpecificationOwner;
	public AutoCreateOpaqueExpressionComposite(Composite parent,FormToolkit toolkit){
		super(parent, toolkit);
	}
	public abstract EReference getValueSpecificationFeature();
	@Override
	protected void fireOclChanged(String text){
		boolean hasOclExpression = containsExpression(text);
		if(valueSpecificationOwner != null && !hasOclExpression ){
			Object vs = valueSpecificationOwner.eGet(getValueSpecificationFeature());
			if(vs != null && getValueSpecificationFeature().getUpperBound() == 1){
				// Remove valueSpecification
				getEditingDomain().getCommandStack().execute(
						SetCommand.create(getEditingDomain(), valueSpecificationOwner, getValueSpecificationFeature(), null));
				this.oclBodyOwner = null;
			}
		}
		if(super.oclBodyOwner == null && hasOclExpression){

			Object vs = valueSpecificationOwner.eGet(getValueSpecificationFeature());
			if(vs instanceof OpaqueExpression){
				super.oclBodyOwner = (NamedElement) vs;
			}else if(getValueSpecificationFeature().getUpperBound() == 1){
				OpaqueExpression oe = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createOpaqueExpression();
				oe.setName("oclExpression");
				super.oclBodyOwner = oe;//NB!! BEFORE command otherwise the OclComposite reverts to DEFAULT_TEXT
				getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), valueSpecificationOwner, getValueSpecificationFeature(), oe));
			}else{
				throw new IllegalStateException(
						"Please override 'fireOclChanged(String text)' to populate the appropriate valueSpecificationOwner before the ocl is changed on the opaqueExpression");
			}
		}
		if(hasOclExpression ){
			super.fireOclChanged(text);
		}
	}
}
