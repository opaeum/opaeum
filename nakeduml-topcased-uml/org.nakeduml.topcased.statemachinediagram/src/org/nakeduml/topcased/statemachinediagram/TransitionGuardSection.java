package org.nakeduml.topcased.statemachinediagram;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.nakeduml.topcased.propertysections.AbstractOpaqueExpressionSection;
import org.nakeduml.topcased.propertysections.OclValueComposite;

public class TransitionGuardSection extends AbstractOpaqueExpressionSection{
	@Override
	protected String getExpressionLabel(){
		return "Guard condition expression";
	}
	@Override
	protected Element getOclContext(){
		return getTransition();
	}
	@Override
	protected OpaqueExpression getExpression(EObject e){
		Constraint guard = getGuard(e);
		if(guard != null){
			return (OpaqueExpression) guard.getSpecification();
		}else{
			return null;
		}
	}
	private Constraint getGuard(EObject e){
		return ((Transition) e).getGuard();
	}
	private Transition getTransition(){
		return (Transition) getEObject();
	}
	@Override
	protected NamedElement getOwner(){
		return getTransition().getGuard();
	}
	@Override
	protected ValueSpecification getValueSpecification(){
		if(getTransition().getGuard() != null){
			return getTransition().getGuard().getSpecification();
		}else{
			return null;
		}
	}
	@Override
	protected void handleOclChanged(String text){
		if(text.trim().length()>0){
			if(getTransition().getGuard()==null){
				Constraint createConstraint = UMLFactory.eINSTANCE.createConstraint();
				createConstraint.setName(getTransition().getName()+"Guard");
				Command command = SetCommand.create(getEditingDomain(), getTransition(), UMLPackage.eINSTANCE.getTransition_Guard(), createConstraint);
				getEditingDomain().getCommandStack().execute(command);
			}
		}
		super.handleOclChanged(text);
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getConstraint_Specification();
	}
	@Override
	protected String getLabelText(){
		return "Guard condition";
	}
}
