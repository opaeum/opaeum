package org.opeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.opeum.topcased.propertysections.AbstractOpaqueExpressionSection;
import org.opeum.topcased.propertysections.ocl.OclBodyComposite;

public class OclPinValueSection extends AbstractOpaqueExpressionSection{
	@Override
	protected String getLabelText(){
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
