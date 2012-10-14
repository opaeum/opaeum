package org.opaeum.eclipse.uml.propertysections.core;

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
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaqueExpressionSection;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;

public class OperationBodyConditionSection extends AbstractOpaqueExpressionSection{
	@Override
	protected NamedElement getOclContext(){
		return getOperation();
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	@Override
	protected OpaqueExpression beforeOclChanged(String text){
		if(OclBodyComposite.containsExpression(text) && (getOperation().getBodyCondition() == null||getOperation().getBodyCondition().getSpecification()==null)){
			createBodyCondition();
		}
		return getSpecification();
	}
	protected OpaqueExpression getSpecification(){
		if(getOperation().getBodyCondition() == null){
			return null;
		}else{
			return (OpaqueExpression) getOperation().getBodyCondition().getSpecification();
		}
	}
	@Override
	public String getLabelText(){
		return "Query expression";
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getOperation().getBodyCondition();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		((OpaqueExpressionComposite) oclComposite).setOclContext(getOperation(), getSpecification());
	}
	@Override
	public void populateControls(){
		super.populateControls();

		super.oclComposite.getTextControl().setEnabled(requiresBody());
	}
	private boolean requiresBody(){
		return getOperation().isQuery() && !getOperation().isAbstract();
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
					// TODO do in OpaeumModelElementLinker
					removeBodyCondition();
				}
				break;
			case UMLPackage.OPERATION__IS_ABSTRACT:
				if(msg.getNewBooleanValue()){
					oclComposite.getTextControl().setEnabled(false);
				}else{
					oclComposite.getTextControl().setEnabled(true && getOperation().isQuery());
					// TODO do in OpaeumModelElementLinker
					removeBodyCondition();
				}
				break;
			}
		}
	}
	private void removeBodyCondition(){
		getEditingDomain().getCommandStack().execute(
		 RemoveCommand.create(getEditingDomain(), getEObject(), UMLPackage.eINSTANCE.getNamespace_OwnedRule(),
		 getOperation().getBodyCondition()));
	}
	private void createBodyCondition(){
		if(getOperation().getBodyCondition() == null){
			Constraint cnstr = UMLFactory.eINSTANCE.createConstraint();
			// Specification created by OpaeumElementLinker//This does not always work
			cnstr.setName(getOperation().getName() + "Body");
			getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getEObject(), UMLPackage.eINSTANCE.getOperation_BodyCondition(), cnstr));
		}
		if(getOperation().getBodyCondition().getSpecification() == null){
			OpaqueExpression cnstr = UMLFactory.eINSTANCE.createOpaqueExpression();
			// Specification created by OpaeumElementLinker
			cnstr.setName(getOperation().getName() + "BodySpecification");
			getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getOperation().getBodyCondition(), UMLPackage.eINSTANCE.getConstraint_Specification(), cnstr));
		}
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getConstraint_Specification();
	}
}
