package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;

public class PropertyDefaultValueSection extends AbstractOpaqueExpressionSection{
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		Object notifier = msg.getNotifier();
		if(notifier.equals(getEObject())){
			if(msg.getFeatureID(getEObject().getClass()) == UMLPackage.eINSTANCE.getProperty_IsDerived().getFeatureID()){
				label.setText(getLabelText());
			}
		}
	}
	protected String getLabelText(){
		return getProperty()==null?"Default Value":(getProperty().isDerived()?"Derived Value":"Initial Value");
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getProperty_DefaultValue();
	}
	
	public Property getProperty(){
		return (Property) getEObject();
	}
	protected OpaqueExpression getExpression(EObject e){
		return (OpaqueExpression) ((Property) e).getDefaultValue();
	}
	@Override
	protected NamedElement getOwner(){
		return getProperty();
	}
	@Override
	protected ValueSpecification getValueSpecification(){
		return getProperty().getDefaultValue();
	}
}
