package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;

public class OperationBodyConditionSection extends AbstractOpaqueExpressionSection{
	@Override
	protected NamedElement getOclContext(){
		return getOperation();
	}
	@Override
	protected NamedElement beforeOclChanged(String text){
		if(!OclBodyComposite.containsExpression(text)){
			removeBodyCondition();
			return null;
		}else if(getOperation().getBodyCondition()==null){
			createBodyCondition();
		}
		return getOperation().getBodyCondition();
	}
	@Override
	protected String getLabelText(){
		return "Query expression";
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getOperation().getBodyCondition();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		oclComposite.setOclContext(getOperation(),null,null);
	}
	@Override
	public void refresh(){
		super.refresh();
		super.oclComposite.getTextControl().setEnabled(getOperation().isQuery() && !getOperation().isAbstract());
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
				}else{
					oclComposite.getTextControl().setEnabled(false);
					removeBodyCondition();
				}
				break;
			case UMLPackage.OPERATION__IS_ABSTRACT:
				if(msg.getNewBooleanValue()){
					oclComposite.getTextControl().setEnabled(false);
				}else{
					oclComposite.getTextControl().setEnabled(true && getOperation().isQuery());
					removeBodyCondition();
				}
				break;
			}
		}
	}
	private void removeBodyCondition(){
		getEditingDomain().getCommandStack().execute(
				RemoveCommand.create(getEditingDomain(), getEObject(), UMLPackage.eINSTANCE.getNamespace_OwnedRule(), getOperation().getBodyCondition()));
	}
	private void createBodyCondition(){
		if(getOperation().getBodyCondition() == null){
			Constraint cnstr = UMLFactory.eINSTANCE.createConstraint();
			OpaqueExpression oe = (OpaqueExpression) cnstr.createSpecification("body", null, UMLPackage.eINSTANCE.getOpaqueExpression());
			getEditingDomain().getCommandStack().execute(
					SetCommand.create(getEditingDomain(), getEObject(), UMLPackage.eINSTANCE.getOperation_BodyCondition(),
							cnstr));
		}
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getConstraint_Specification();
	}
}
