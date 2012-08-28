package org.opaeum.eclipse.uml.propertysections.event;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractAutoCreatingOclExpressionSection;

public class TimeEventWhenSection extends AbstractAutoCreatingOclExpressionSection{
	protected TimeEvent getTimeEvent(){
		return (TimeEvent) getEObject();
	}
	
	@Override
	protected NamedElement getValueSpecificationOwner(){
		if(getTimeEvent().getWhen()==null){
			TimeExpression te = UMLFactory.eINSTANCE.createTimeExpression();
			te.setName("when" +getTimeEvent().getName());
			getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getTimeEvent(), UMLPackage.eINSTANCE.getTimeEvent_When(), te));
		}
		return getTimeEvent().getWhen();
	}

	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getTimeExpression_Expr();
	}

	@Override
	protected String getLabelText(){
		return "When:";
	}
}
