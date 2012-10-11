package org.opaeum.eclipse.uml.propertysections.statemachine;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaqueExpressionSection;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;

public class TransitionGuardSection extends AbstractOpaqueExpressionSection{
	@Override
	protected String getExpressionLabel(){
		return "Guard condition expression";
	}
	@Override
	protected NamedElement getOclContext(){
		return getTransition();
	}
	private Transition getTransition(){
		return (Transition) getEObject();
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getTransition().getGuard();
	}
	@Override
	protected OpaqueExpression beforeOclChanged(String text){
		if(OclBodyComposite.containsExpression(text)){
			if(getTransition().getGuard() == null){
				Constraint guard = UMLFactory.eINSTANCE.createConstraint();
				guard.setName(getTransition().getName() + "Guard");
				OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
				guard.setSpecification(oe);
				oe.setName(guard.getName()+"Specification");
				Command command = SetCommand.create(getEditingDomain(), getTransition(), UMLPackage.eINSTANCE.getTransition_Guard(), guard);
				getEditingDomain().getCommandStack().execute(command);
			}
		}else if(getTransition().getGuard() != null){
			Command command = RemoveCommand.create(getEditingDomain(), getTransition(), UMLPackage.eINSTANCE.getNamespace_OwnedRule(), getTransition().getGuard());
			getEditingDomain().getCommandStack().execute(command);
			return null;
		}
		return (OpaqueExpression) getTransition().getGuard().getSpecification();
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getConstraint_Specification();
	}
	@Override
	public String getLabelText(){
		return "Guard condition";
	}
}
