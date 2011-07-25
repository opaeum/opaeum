package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.nakeduml.topcased.commands.SetOclExpressionCommand;

public class OpaqueExpressionBodySection extends AbstractOpaqueExpressionSection{
	@Override
	protected OpaqueExpression getExpression(EObject e){
		return (OpaqueExpression) e;
	}
	@Override
	protected NamedElement getOwner(){
		EObject eContainer = getEObject().eContainer();
		while(!(eContainer instanceof NamedElement)){
			eContainer=eContainer.eContainer();
		}
		return (NamedElement) eContainer;
	}
	@Override
	protected ValueSpecification getValueSpecification(){
		return (ValueSpecification) getEObject();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		throw new IllegalStateException("No valueSpecificationFeature on OpaqueExpression");
	}
	protected void handleOclChanged(String oclText){
		if(oclText.trim().length() > 0){
			OpaqueExpression vs = (OpaqueExpression) getValueSpecification();
			if(vs.getBodies().isEmpty()){
				CompoundCommand cmd = new CompoundCommand();
				cmd.append(AddCommand.create(getEditingDomain(), vs, UMLPackage.eINSTANCE.getOpaqueExpression_Body(), oclText));
				cmd.append(AddCommand.create(getEditingDomain(), vs, UMLPackage.eINSTANCE.getOpaqueExpression_Language(), "OCL",0));
				getEditingDomain().getCommandStack().execute(cmd);
			}
		}
	}

	@Override
	protected String getLabelText(){
		return "Body";
	}
}
