package org.opaeum.topcased.propertysections.property;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.topcased.propertysections.AbstractMultiFeaturePropertySection;

public class PropertyBooleanFeaturesSection extends AbstractMultiFeaturePropertySection{
	private Button isReadOnly;
	private Button isStatic;
	private Button isDerivedUnion;
	private Button isDerived;
	private Button isComposition;
	protected void setSectionData(Composite composite){
		layout(null, isComposition, 130);
		layout(isComposition, isReadOnly, 130);
		layout(isReadOnly, isStatic, 130);
		layout(isStatic, isDerived, 130);
		layout(isDerived, isDerivedUnion, 130);
	}
	protected Element getFeatureOwner(){
		return getProperty();
	}
	protected Property getProperty(){
		return (Property) getEObject();
	}
	@Override
	protected void handleModelChanged(Notification msg){
		Object notifier = msg.getNotifier();
		if(notifier.equals(getEObject()) && getFeature() != null){
			refresh();
		}
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		isComposition = getWidgetFactory().createButton(composite, "Is Composition", SWT.CHECK);
		isReadOnly = getWidgetFactory().createButton(composite, "Is Read Only", SWT.CHECK);
		isStatic = getWidgetFactory().createButton(composite, "Is Static", SWT.CHECK);
		isDerived = getWidgetFactory().createButton(composite, "Is Derived", SWT.CHECK);
		isDerivedUnion = getWidgetFactory().createButton(composite, "Is Derived Union", SWT.CHECK);
	}
	protected void hookListeners(){
		isComposition.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion()));
		isReadOnly.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly()));
		isStatic.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getFeature_IsStatic()));
		isDerived.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getProperty_IsDerived()));
		isDerivedUnion.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion()));
	}
	@Override
	protected String getLabelText(){
		return "";
	}
	@Override
	public void refresh(){
		isReadOnly.setSelection(getProperty().isReadOnly());
		isStatic.setSelection(getProperty().isStatic());
		isStatic.setEnabled(!getProperty().isComposite());
		isDerived.setSelection(getProperty().isDerived());
		isDerivedUnion.setSelection(getProperty().isDerivedUnion());
		isComposition.setEnabled((isAssociationEnd() || isInProfile()) && !getProperty().isStatic());
	}
	private boolean isInProfile(){
		return EmfElementFinder.getRootObject(getProperty()) instanceof Profile;
	}
	private boolean isAssociationEnd(){
		return getProperty().getType() instanceof org.eclipse.uml2.uml.Class && getProperty().getAssociation() != null;
	}
}
