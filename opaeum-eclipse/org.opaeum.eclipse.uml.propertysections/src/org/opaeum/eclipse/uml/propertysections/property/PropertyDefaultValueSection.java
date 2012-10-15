package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.uml.propertysections.base.RecreatingOpaqueExpressionSection;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;

public class PropertyDefaultValueSection extends RecreatingOpaqueExpressionSection{
	protected void handleModelChanged(Notification msg){
		Object notifier = msg.getNotifier();
		if(notifier.equals(safeGetProperty())){
			if(labelCombo.isDisposed()){
				removeListener();
			}else{
				if(msg.getFeatureID(Property.class) == UMLPackage.PROPERTY__IS_DERIVED){
					labelCombo.setText(getAppropriateLabelText());
				}
				if(msg.getFeatureID(Property.class) != UMLPackage.PROPERTY__DEFAULT_VALUE){
					if(requiresDefaultValue() && !(safeGetProperty().getDefaultValue() instanceof OpaqueExpression)){
						forceCreateOpaqueExpression();
					}
					super.handleModelChanged(msg);
				}
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
	private void forceCreateOpaqueExpression(){
		OpaqueExpression oe = EmfValueSpecificationUtil.buildOpaqueExpression(safeGetProperty(),"DefaultValue",  OclBodyComposite.REQUIRED_TEXT);
		getEditingDomain().getCommandStack().execute(
				SetCommand.create(getEditingDomain(), safeGetProperty(), getFeature(), oe));
	}

	public String getAppropriateLabelText(){
		return safeGetProperty().isDerived() ? "Derived Value" : "Initial Value";
	}
	@Override
	public void populateControls(){
		super.populateControls();
		labelCombo.setText(getAppropriateLabelText());
	}
	public String getLabelText(){
		return "Default Value";
	}
	public Property safeGetProperty(){
		if(getSelectedObject() instanceof Association){
			Association a = (Association) getSelectedObject();
			if(a.getMemberEnds().size() < 2){
				return null;
			}
		}
		return getProperty(getSelectedObject());
	}
	protected Property getProperty(EObject e){
		return (Property) e;
	}
	@Override
	protected EReference getFeature(){
		return UMLPackage.eINSTANCE.getProperty_DefaultValue();
	}
}
