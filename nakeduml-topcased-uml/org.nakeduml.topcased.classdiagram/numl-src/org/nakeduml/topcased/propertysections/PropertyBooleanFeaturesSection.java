package org.nakeduml.topcased.propertysections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.propertysections.AbstractMultiFeaturePropertySection.BooleanSelectionListener;

public class PropertyBooleanFeaturesSection extends AbstractMultiFeaturePropertySection{
	private Button isReadOnly;
	private Button isStatic;
	private Button isDerivedUnion;
	private Button isDerived;
	protected void setSectionData(Composite composite){
		layout(null, isReadOnly, 120);
		layout(isReadOnly, isStatic, 120);
		layout(isStatic, isDerived, 120);
		layout(isDerived, isDerivedUnion, 120);
	}
	protected Element getFeatureOwner(){
		return getProperty();
	}
	protected Property getProperty(){
		return (Property) getEObject();
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		isReadOnly = getWidgetFactory().createButton(composite, "Is Read Only", SWT.CHECK);
		isStatic = getWidgetFactory().createButton(composite, "Is Static", SWT.CHECK);
		isDerived = getWidgetFactory().createButton(composite, "Is Derived", SWT.CHECK);
		isDerivedUnion = getWidgetFactory().createButton(composite, "Is Derived Union", SWT.CHECK);
	}
	protected void hookListeners(){
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
		isDerived.setSelection(getProperty().isDerived());
		isDerivedUnion.setSelection(getProperty().isDerivedUnion());
	}
}
