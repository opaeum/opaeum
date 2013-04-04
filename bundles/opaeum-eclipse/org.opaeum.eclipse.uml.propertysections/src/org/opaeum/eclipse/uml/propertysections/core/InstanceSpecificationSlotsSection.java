package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.eclipse.uml.propertysections.core.SlotComposite.SlotValueListener;
import org.opaeum.name.NameConverter;

public class InstanceSpecificationSlotsSection extends AbstractOpaeumPropertySection{
	protected Group slotsGroup;
	@Override
	protected void createWidgets(Composite composite){
		this.slotsGroup = getWidgetFactory().createGroup(composite, getLabelText());
		FormData fd = new FormData();
		fd.top = new FormAttachment();
		fd.left = new FormAttachment(getLabelCombo());
		fd.bottom = new FormAttachment(100);
		fd.right = new FormAttachment(100);
		this.slotsGroup.setLayoutData(fd);
		this.slotsGroup.setLayout(new GridLayout(2, false));
	}
	@Override
	public Control getPrimaryInput(){
		throw new IllegalStateException();
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	protected static List<Slot> collectSlots(List<InstanceSpecification> instances,final StructuralFeature feature){
		final List<Slot> slots = new ArrayList<Slot>();
		for(InstanceSpecification is:instances){
			for(Slot slot:is.getSlots()){
				if(feature.equals(slot.getDefiningFeature())){
					slots.add(slot);
				}
			}
		}
		return slots;
	}
	@Override
	public void populateControls(){
		super.populateControls();

		populateSlots(getInstanceSpecifications(), slotsGroup, getWidgetFactory(), getEditingDomain(), null);
	}
	public static void populateSlots(List<InstanceSpecification> iss,Group slotsGroup2,TabbedPropertySheetWidgetFactory widgetFactory,
			EditingDomain editingDomain,SlotValueListener listener){
		for(Control control:slotsGroup2.getChildren()){
			control.dispose();
		}
		if(iss.size() > 0){
			Classifier classifier = findCommonSuperclass(iss);
			if(classifier != null){
				SlotComposite last = null;
				SlotComposite first = null;
				for(Property prop:EmfPropertyUtil.getEffectiveProperties(classifier)){
					if(!(prop.isDerived() || prop.isDerivedUnion() || prop.isReadOnly())){
						Label lbl = widgetFactory.createLabel(slotsGroup2, NameConverter.separateWords(NameConverter.capitalize(prop.getName())));
						lbl.setAlignment(SWT.TOP | SWT.LEFT);
						lbl.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
						lbl.setLayoutData(new GridData(STANDARD_LABEL_WIDTH + 55, 25));
						SlotComposite sc = new SlotComposite(slotsGroup2, SWT.BORDER, widgetFactory, editingDomain, listener);
						sc.setInput(collectSlots(iss, prop), prop);
						if(last != null && last.getCurrentOclText() != null && sc.getCurrentOclText() != null){
							last.getCurrentOclText().setTabTo(sc.getCurrentOclText().getTextControl());
						}
						if(last == null){
							first = sc;
						}
						last = sc;
					}
				}
				if(last != null && first != null && last.getCurrentOclText() != null){
					last.getCurrentOclText().setTabTo(first.getControl());
				}
				Control[] children = slotsGroup2.getChildren();
				for(Control control:children){
					if(control instanceof SlotComposite){
						final SlotComposite slotComposite = (SlotComposite) control;
						slotComposite.pack();
						GridData layoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
						slotComposite.setLayoutData(layoutData);
					}
				}
				slotsGroup2.pack();
				slotsGroup2.getParent().layout();
				slotsGroup2.layout();
			}
		}
	}
	protected static Classifier findCommonSuperclass(List<InstanceSpecification> iss){
		InstanceSpecification first = iss.get(0);
		if(first.getClassifiers().isEmpty()){
			return null;
		}
		Classifier classifier = getClassifier(first);
		for(InstanceSpecification is:iss){
			if(is.getClassifiers().size() != 1 && !(is instanceof EnumerationLiteral)){
				classifier = null;
				break;
			}else if(!getClassifier(is).equals(classifier)){
				if(getClassifier(is).conformsTo(classifier)){
					// fine
				}else if(classifier.conformsTo(getClassifier(is))){
					classifier = getClassifier(is);
				}else{
					classifier = null;
					break;
				}
			}
		}
		return classifier;
	}
	protected static Classifier getClassifier(InstanceSpecification first){
		return first instanceof EnumerationLiteral ? ((EnumerationLiteral) first).getEnumeration() : first.getClassifiers().get(0);
	}
	protected List<InstanceSpecification> getInstanceSpecifications(){
		List<InstanceSpecification> result = new ArrayList<InstanceSpecification>();
		for(EObject o:getEObjectList()){
			result.add((InstanceSpecification) o);
		}
		return result;
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getInstanceSpecification_Slot();
	}
	@Override
	public String getLabelText(){
		return "Slots";
	}
}
