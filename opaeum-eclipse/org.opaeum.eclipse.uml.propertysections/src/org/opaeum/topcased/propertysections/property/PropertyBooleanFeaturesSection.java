package org.opaeum.topcased.propertysections.property;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.topcased.propertysections.base.AbstractMultiFeaturePropertySection;

public class PropertyBooleanFeaturesSection extends AbstractMultiFeaturePropertySection{
	private Button isReadOnly;
	private Button isStatic;
	private Button isDerivedUnion;
	private Button isDerived;
	private Button isComposition;
	protected void setSectionData(Composite composite){
		layout(null, isReadOnly, 130);
		layout(isReadOnly, isStatic, 130);
		layout(isStatic, isDerived, 130);
		layout(isDerived, isDerivedUnion, 130);
		layout(isDerivedUnion, isComposition, 130);
	}
	@Override
	protected void addListener(){
		super.addListener();
		if(getEObject() != null){
			safeGetProperty().eAdapters().add(getModelListener());
		}
	}
	@Override
	protected void removeListener(){
		super.removeListener();
		if(getEObject() != null){
			safeGetProperty().eAdapters().remove(getModelListener());
		}
	}
	protected Element getFeatureOwner(){
		return safeGetProperty();
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
		isReadOnly = getWidgetFactory().createButton(composite, "Is Read Only", SWT.CHECK);
		isStatic = getWidgetFactory().createButton(composite, "Is Static", SWT.CHECK);
		isDerived = getWidgetFactory().createButton(composite, "Is Derived", SWT.CHECK);
		isDerivedUnion = getWidgetFactory().createButton(composite, "Is Derived Union", SWT.CHECK);
		isComposition = getWidgetFactory().createButton(composite, "Is Composite", SWT.CHECK);
	}
	protected void hookListeners(){
		isReadOnly.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly()));
		isStatic.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getFeature_IsStatic()));
		isDerived.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getProperty_IsDerived()));
		isDerivedUnion.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion()));
		isComposition.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getProperty_IsComposite()));
	}
	@Override
	protected String getLabelText(){
		return "";
	}
	@Override
	public void refresh(){
		if(safeGetProperty() != null){
			isReadOnly.setSelection(safeGetProperty().isReadOnly());
			isStatic.setSelection(safeGetProperty().isStatic());
			isStatic.setEnabled(!safeGetProperty().isComposite());
			isDerived.setSelection(safeGetProperty().isDerived());
			isDerivedUnion.setSelection(safeGetProperty().isDerivedUnion());
			if(isInProfile()){
				isComposition.setEnabled(!safeGetProperty().isStatic());
			}else{
				isComposition.setVisible(false);
			}
		}
	}
	private boolean isInProfile(){
		return EmfElementFinder.getRootObject(safeGetProperty()) instanceof Profile;
	}
	public Property safeGetProperty(){
		if(getEObject() instanceof Association){
			Association a = (Association) getEObject();
			if(a.getMemberEnds().size() < 2){
				return null;
			}
		}
		return getProperty();
	}
}