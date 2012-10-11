package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaqueExpressionSection;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;

public class OclPinValueSection extends AbstractOpaqueExpressionSection{
	@Override
	public String getLabelText(){
		return "Value";
	}
	public ValuePin getValuePin(){
		return (ValuePin) getEObject();
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getValuePin();
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getValuePin_Value();
	}
	@Override
	protected OpaqueExpression beforeOclChanged(String text){
		if(!(getValuePin().getValue() instanceof OpaqueExpression)){
			OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
			oe.getBodies().add(OclBodyComposite.REQUIRED_TEXT);
			oe.getLanguages().add("OCL");
			getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getValuePin(), getValueSpecificationFeature(), oe));
		}
		return (OpaqueExpression) getValuePin().getValue();
	}
}
