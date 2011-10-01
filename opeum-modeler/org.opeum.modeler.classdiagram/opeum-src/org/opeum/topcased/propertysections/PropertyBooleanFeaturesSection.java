package org.opeum.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;

public class PropertyBooleanFeaturesSection extends AbstractMultiFeaturePropertySection{
	private Button isReadOnly;
	private Button isStatic;
	private Button isDerivedUnion;
	private Button isDerived;
	private Button isNavigable;
	protected void setSectionData(Composite composite){
		layout(null, isReadOnly, 120);
		layout(isReadOnly, isStatic, 120);
		layout(isStatic, isDerived, 120);
		layout(isDerived, isDerivedUnion, 120);
		layout(isDerivedUnion, isNavigable, 120);
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
		isNavigable = getWidgetFactory().createButton(composite, "Is Navigable", SWT.CHECK);
	}
	protected void hookListeners(){
		isReadOnly.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly()));
		isStatic.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getFeature_IsStatic()));
		isDerived.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getProperty_IsDerived()));
		isDerivedUnion.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getProperty_IsDerivedUnion()));
		isNavigable.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				Boolean navigable = Boolean.valueOf(((Button) e.getSource()).getSelection());
				Command cmd=null;
				if(navigable && !getProperty().isNavigable()){
					cmd = AddCommand.create(getEditingDomain(), getProperty().getAssociation(), UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), getProperty());
				}else if(!navigable && getProperty().isNavigable()){
					cmd =RemoveCommand.create(getEditingDomain(), getProperty().getAssociation(), UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), getProperty());
				}
				if(cmd!=null){
					getEditingDomain().getCommandStack().execute(cmd);
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
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
		isNavigable.setSelection(getProperty().isNavigable());
		isNavigable.setEnabled(getProperty().getAssociation()!=null);
		
	}
}
