package org.opaeum.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.uml2.uml.Association;
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
		if(safeGetProperty()!=null){
			safeGetProperty().eAdapters().remove(getModelListener());
		}
	}
	public Property safeGetProperty(){
		if(getEObject() instanceof Association){
			Association a=(Association) getEObject();
			if(a.getMemberEnds().size()<2){
				return null;
			}
		}			
		if(getEObject()==null){
			return null;
		}
		return getProperty();
	}
	protected void setSectionData(Composite composite){
		layout(null, isNavigable, 120);
		layout(isNavigable, isComposite, 120);
	}
	protected Element getFeatureOwner(){
		return safeGetProperty();
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
							SetCommand.create(getEditingDomain(), safeGetProperty(), UMLPackage.eINSTANCE.getProperty_Aggregation(),
									AggregationKind.COMPOSITE_LITERAL));
				}else{
					getEditingDomain().getCommandStack().execute(
							SetCommand.create(getEditingDomain(), safeGetProperty(), UMLPackage.eINSTANCE.getProperty_Aggregation(),
									AggregationKind.NONE_LITERAL));
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
				if(navigable && !safeGetProperty().isNavigable()){
					cmd = AddCommand.create(getEditingDomain(), safeGetProperty().getAssociation(),
							UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), safeGetProperty());
				}else if(!navigable && safeGetProperty().isNavigable()){
					cmd = RemoveCommand.create(getEditingDomain(), safeGetProperty().getAssociation(),
							UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), safeGetProperty());
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
		if(getEObject() != null && safeGetProperty()!=null){
			isNavigable.setSelection(safeGetProperty().isNavigable());
			isComposite.setSelection(safeGetProperty().isComposite());
		}
	}
}