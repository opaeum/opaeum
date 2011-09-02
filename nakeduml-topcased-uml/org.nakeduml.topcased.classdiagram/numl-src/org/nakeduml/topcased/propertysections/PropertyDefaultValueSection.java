package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;

public class PropertyDefaultValueSection extends AbstractOpaqueExpressionSection{
	protected void handleModelChanged(Notification msg){
		Object notifier = msg.getNotifier();
		if(notifier.equals(getProperty())){
			if(msg.getFeatureID(Property.class) == UMLPackage.PROPERTY__IS_DERIVED){
				label.setText(getAppropriateLabelText());
			}
			if(msg.getFeatureID(Property.class) != UMLPackage.PROPERTY__DEFAULT_VALUE){
				if(requiresDefaultValue() && !(getProperty().getDefaultValue() instanceof OpaqueExpression)){
					createOpaqueExpression();
				}
				super.handleModelChanged(msg);
			}
		}
	}
	private boolean requiresDefaultValue(){
		return getProperty().isDerived() && !getProperty().isDerivedUnion();
	}
	private void createOpaqueExpression(){
		OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
		oe.setName("abc");
		oe.getLanguages().add("OCL");
		oe.getBodies().add(OclBodyComposite.REQUIRED_TEXT);
		getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getProperty(), getValueSpecificationFeature(), oe));
	}
	@Override
	protected OpaqueExpression beforeOclChanged(String text){
		if(OclBodyComposite.containsExpression(text)){
			if(getProperty().getDefaultValue() == null){
				OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
				oe.setName(getProperty().getName() + getLabelText());
				getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getProperty(), getValueSpecificationFeature(), oe));
			}
		}else if(!requiresDefaultValue() && getProperty().getDefaultValue() != null){
			Command rm = SetCommand.create(getEditingDomain(), getProperty(), getValueSpecificationFeature(), null);
			getEditingDomain().getCommandStack().execute(rm);
		}
		return (OpaqueExpression) getProperty().getDefaultValue();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	public String getAppropriateLabelText(){
		return getProperty().isDerived() ? "Derived Value" : "Initial Value";
	}
	@Override
	public void refresh(){
		super.refresh();
		label.setText(getAppropriateLabelText());
	}
	protected String getLabelText(){
		return "Default Value";
	}
	public Property getProperty(){
		return getProperty(getEObject());
	}
	protected Property getProperty(EObject e){
		return (Property) e;
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getProperty();
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getProperty_DefaultValue();
	}
}
