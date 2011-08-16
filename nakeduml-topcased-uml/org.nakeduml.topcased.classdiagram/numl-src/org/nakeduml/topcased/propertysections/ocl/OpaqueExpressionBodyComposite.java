package org.nakeduml.topcased.propertysections.ocl;

import java.applet.AudioClip;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.commands.SetOclBodyCommand;

public abstract class OpaqueExpressionBodyComposite extends OclBodyComposite{
	protected EObject valueSpecificationOwner;
	private boolean autoDeleteOpaqueExpression;
	public OpaqueExpressionBodyComposite(Composite parent,FormToolkit toolkit){
		super(parent, toolkit);
	}
	public abstract EReference getValueSpecificationFeature();
	@Override
	protected void fireOclChanged(String text){
		boolean hasOclExpression = containsExpression(text);
		if(valueSpecificationOwner != null && !hasOclExpression && autoDeleteOpaqueExpression){
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
				getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), valueSpecificationOwner, getValueSpecificationFeature(), oe));
				super.oclBodyOwner = oe;
			}else{
				throw new IllegalStateException(
						"Please override 'fireOclChanged(String text)' to populate the appropriate valueSpecificationOwner before the ocl is changed on the opaqueExpression");
			}
		}
		if(hasOclExpression || !autoDeleteOpaqueExpression){
			super.fireOclChanged(text);
		}
	}
	@Override
	public EStructuralFeature getBodiesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueExpression_Body();
	}
	@Override
	public EStructuralFeature getLanguagesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueExpression_Language();
	}
	// VP can be null (created on demand), but context must be not-null, vaueSpecificationContainer may also be null - created on fireOclChanged
	public void setOclContext(NamedElement context, EObject valueSpecificationContainer,final OpaqueExpression vp){
		this.valueSpecificationOwner = valueSpecificationContainer;
		EObject s = context;
		EObject container = null;
		while(!(isOclContext(container = getContainer(s)))){
		}
		setOclContextImpl((NamedElement) container, vp);
	}
	public void setAutoDeleteOpaqueExpression(boolean b){
		this.autoDeleteOpaqueExpression=b;
		
	}
}
