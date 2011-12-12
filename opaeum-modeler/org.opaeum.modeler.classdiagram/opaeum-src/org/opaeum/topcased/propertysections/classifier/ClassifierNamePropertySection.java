package org.opaeum.topcased.propertysections.classifier;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.ProfileApplier;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class ClassifierNamePropertySection extends AbstractTabbedPropertySection{
	private Stereotype stereotype;
	private CCombo combo;
	List<Property> properties = new ArrayList<Property>();
	private Label label;
	public abstract String getStereotypeName();
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected String getLabelText(){
		return "Name Property";
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		Profile applyNakedUmlProfile = ProfileApplier.applyNakedUmlProfile(getClassifier().getModel());
		Stereotype propertyStereotype = applyNakedUmlProfile.getOwnedStereotype(getStereotypeName());
		this.stereotype = propertyStereotype;
		properties.clear();
		for(Property property:getClassifier().getAllAttributes()){
			if(property.getType()!=null && EmfClassifierUtil.comformsToLibraryType(property.getType(), "String")){
				properties.add(property);
			}
		}
	}
	private Classifier getClassifier(){
		return (Classifier) getEObject();
	}
	@Override
	public void refresh(){
		super.refresh();
		String[] items = new String[properties.size()];
		for(int i = 0;i < items.length;i++){
			items[i] = properties.get(i).getName();
		}
		combo.setItems(items);
		if(getClassifier().isStereotypeApplied(stereotype)){
			Property nameProperty = (Property) getClassifier().getValue(stereotype, "nameProperty");
			combo.select(properties.indexOf(nameProperty));
		}
		combo.layout();
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{
			getLabelText()
		}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		data.height = 18;
		combo.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(combo, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(combo, 0, SWT.TOP);
		data.height = 15;
		label.setLayoutData(data);
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		this.label = getWidgetFactory().createLabel(composite, getLabelText());
		this.combo = getWidgetFactory().createCCombo(composite, SWT.READ_ONLY);
		this.combo.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				Property property = properties.get(combo.getSelectionIndex());
				if(!getClassifier().isStereotypeApplied(stereotype)){
					getClassifier().applyStereotype(stereotype);
				}
				Command cmd = SetCommand.create(getEditingDomain(), getClassifier().getStereotypeApplication(stereotype), stereotype.getDefinition().getEStructuralFeature("nameProperty"), property);
				getEditingDomain().getCommandStack().execute(cmd);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
				// TODO Auto-generated method stub
			}
		});
	}
}
