package org.nakeduml.topcased.statemachinediagram;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.propertysections.AbstractOpaqueExpressionSection;
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;

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
	protected NamedElement beforeOclChanged(String text){
		if(OclBodyComposite.containsExpression(text)){
			if(getTransition().getGuard() == null){
				Constraint createConstraint = UMLFactory.eINSTANCE.createConstraint();
				createConstraint.setName(getTransition().getName() + "Guard");
				Command command = SetCommand.create(getEditingDomain(), getTransition(), UMLPackage.eINSTANCE.getTransition_Guard(), createConstraint);
				getEditingDomain().getCommandStack().execute(command);
			}
		}else if(getTransition().getGuard() != null){
			Command command = RemoveCommand.create(getEditingDomain(), getTransition(), UMLPackage.eINSTANCE.getTransition_Guard(), getTransition().getGuard());
			getEditingDomain().getCommandStack().execute(command);
		}
		return getTransition().getGuard();
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getConstraint_Specification();
	}
	@Override
	protected String getLabelText(){
		return "Guard condition";
	}
}
