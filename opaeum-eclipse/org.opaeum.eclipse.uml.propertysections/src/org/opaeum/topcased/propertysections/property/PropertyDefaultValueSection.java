package org.opaeum.topcased.propertysections.property;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.topcased.propertysections.base.AbstractOpaqueExpressionSection;
import org.opaeum.topcased.propertysections.ocl.OclBodyComposite;

public class PropertyDefaultValueSection extends AbstractOpaqueExpressionSection{
	protected void handleModelChanged(Notification msg){
		Object notifier = msg.getNotifier();
		if(notifier.equals(safeGetProperty())){
			if(msg.getFeatureID(Property.class) == UMLPackage.PROPERTY__IS_DERIVED){
				label.setText(getAppropriateLabelText());
			}
			if(msg.getFeatureID(Property.class) != UMLPackage.PROPERTY__DEFAULT_VALUE){
				if(requiresDefaultValue() && !(safeGetProperty().getDefaultValue() instanceof OpaqueExpression)){
					createOpaqueExpression();
				}
				super.handleModelChanged(msg);
			}
		}
	}
	@Override
	protected void addListener(){
		super.addListener();
		if(safeGetProperty() != null){
			safeGetProperty().eAdapters().add(getModelListener());
		}
	}
	@Override
	protected void removeListener(){
		super.removeListener();
		if(safeGetProperty() != null){
			safeGetProperty().eAdapters().remove(getModelListener());
		}
	}
	private boolean requiresDefaultValue(){
		return safeGetProperty().isDerived() && !safeGetProperty().isDerivedUnion();
	}
	private void createOpaqueExpression(){
		OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
		oe.setName("abc");
		oe.getLanguages().add("OCL");
		oe.getBodies().add(OclBodyComposite.REQUIRED_TEXT);
		getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), safeGetProperty(), getValueSpecificationFeature(), oe));
	}
	@Override
	protected OpaqueExpression beforeOclChanged(String text){
		if(OclBodyComposite.containsExpression(text)){
			if(safeGetProperty().getDefaultValue() == null){
				OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
				oe.setName(safeGetProperty().getName() + getLabelText());
				getEditingDomain().getCommandStack().execute(
						SetCommand.create(getEditingDomain(), safeGetProperty(), getValueSpecificationFeature(), oe));
			}
		}else if(!requiresDefaultValue() && safeGetProperty().getDefaultValue() != null){
			Command rm = SetCommand.create(getEditingDomain(), safeGetProperty(), getValueSpecificationFeature(), null);
			getEditingDomain().getCommandStack().execute(rm);
		}
		return (OpaqueExpression) safeGetProperty().getDefaultValue();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	public String getAppropriateLabelText(){
		return safeGetProperty().isDerived() ? "Derived Value" : "Initial Value";
	}
	@Override
	public void refresh(){
		super.refresh();
		label.setText(getAppropriateLabelText());
	}
	protected String getLabelText(){
		return "Default Value";
	}
	public Property safeGetProperty(){
		if(getEObject() instanceof Association){
			Association a=(Association) getEObject();
			if(a.getMemberEnds().size()<2){
				return null;
			}
		}			
		return getProperty(getEObject());
	}
	protected Property getProperty(EObject e){
		return (Property) e;
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return safeGetProperty();
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getProperty_DefaultValue();
	}
}
