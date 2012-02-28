package org.opaeum.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.topcased.propertysections.base.AbstractMultiFeaturePropertySection;

public class AssociationEndNavigabilityAndCompositionSection extends AbstractMultiFeaturePropertySection{
	private Button isComposite;
	private Button isNavigable;
	protected EStructuralFeature getFeature(){
		return null;
	}
	protected void setSectionData(Composite composite){
		layout(null, isNavigable, 120);
		layout(isNavigable, isComposite, 120);
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
		isNavigable = getWidgetFactory().createButton(composite, "Is Navigable", SWT.CHECK);
		isComposite = getWidgetFactory().createButton(composite, "Is Composite", SWT.CHECK);
	}
	protected void hookListeners(){
		isComposite.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				if(isComposite.getSelection()){
					getEditingDomain().getCommandStack().execute(
							SetCommand.create(getEditingDomain(), getProperty(), UMLPackage.eINSTANCE.getProperty_Aggregation(), AggregationKind.COMPOSITE_LITERAL));
				}else{
					getEditingDomain().getCommandStack().execute(
							SetCommand.create(getEditingDomain(), getProperty(), UMLPackage.eINSTANCE.getProperty_Aggregation(), AggregationKind.NONE_LITERAL));
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		isNavigable.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				Boolean navigable = Boolean.valueOf(((Button) e.getSource()).getSelection());
				Command cmd = null;
				if(navigable && !getProperty().isNavigable()){
					cmd = AddCommand.create(getEditingDomain(), getProperty().getAssociation(), UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), getProperty());
				}else if(!navigable && getProperty().isNavigable()){
					cmd = RemoveCommand.create(getEditingDomain(), getProperty().getAssociation(), UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), getProperty());
				}
				if(cmd != null){
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
		isNavigable.setSelection(getProperty().isNavigable());
		isComposite.setSelection(getProperty().isComposite());
	}
}