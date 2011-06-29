package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;

public class OperationBodyConditionSection extends AbstractOpaqueExpressionSection{
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getOperation_BodyCondition();
	}
	@Override
	protected String getLabelText(){
		return "Query expression";
	}
	@Override
	protected OpaqueExpression getExpression(EObject e){
		Operation oper = (Operation) e;
		if(oper.getBodyCondition() == null){
			return null;
		}
		return (OpaqueExpression) oper.getBodyCondition().getSpecification();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		// TODO Auto-generated method stub
		super.setInput(part, selection);
	}
	@Override
	public void refresh(){
		super.refresh();
		super.oclComposite.getTextControl().setEnabled(getOperation().isQuery());
	}
	@Override
	protected NamedElement getOwner(){
		return (NamedElement) getEObject();
	}
	@Override
	protected ValueSpecification getValueSpecification(){
		return getExpression(getEObject());
	}
	private Operation getOperation(){
		return (Operation) getEObject();
	}
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		Object notifier = msg.getNotifier();
		if(notifier.equals(getEObject())){
			switch(msg.getFeatureID(getEObject().getClass())){
			case UMLPackage.OPERATION__IS_QUERY:
				if(msg.getNewBooleanValue()){
					oclComposite.getTextControl().setEnabled(true && !getOperation().isAbstract());
					createBodyCondition();
				}else{
					oclComposite.getTextControl().setEnabled(false);
					removeBodyCondition();
				}
				break;
			case UMLPackage.OPERATION__IS_ABSTRACT:
				if(msg.getNewBooleanValue()){
					oclComposite.getTextControl().setEnabled(true && getOperation().isQuery());
					createBodyCondition();
				}else{
					oclComposite.getTextControl().setEnabled(false);
					removeBodyCondition();
				}
				break;
			}
		}
	}
	private void removeBodyCondition(){
		getEditingDomain().getCommandStack().execute(
				SetCommand.create(getEditingDomain(), getValueSpecification(), UMLPackage.eINSTANCE.getOperation_BodyCondition(), null));
	}
	private void createBodyCondition(){
		if(getOperation().getBodyCondition() == null){
			getEditingDomain().getCommandStack().execute(
					SetCommand.create(getEditingDomain(), getEObject(), UMLPackage.eINSTANCE.getOperation_BodyCondition(),
							UMLFactory.eINSTANCE.createConstraint()));
		}
		forceOpaqueExpression();
	}
}
