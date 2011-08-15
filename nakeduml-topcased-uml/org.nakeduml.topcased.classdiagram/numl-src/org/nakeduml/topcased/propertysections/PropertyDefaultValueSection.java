package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;

public class PropertyDefaultValueSection extends AbstractOpaqueExpressionSection{
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		Object notifier = msg.getNotifier();
		if(notifier.equals(getProperty())){
			if(msg.getFeatureID(Property.class) == UMLPackage.eINSTANCE.getProperty_IsDerived().getFeatureID()){
				label.setText(getAppropriateLabelText());
			}
		}
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		label.setText(getAppropriateLabelText());
	}
	public String getAppropriateLabelText(){
		return getProperty().isDerived()?"Derived Value":"Initial Value";
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
