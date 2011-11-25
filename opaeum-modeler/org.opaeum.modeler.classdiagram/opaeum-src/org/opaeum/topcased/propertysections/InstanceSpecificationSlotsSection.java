package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.name.NameConverter;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class InstanceSpecificationSlotsSection extends AbstractTabbedPropertySection{
	private Group slotsGroup;
	@Override
	protected void createWidgets(Composite composite){
		this.slotsGroup = getWidgetFactory().createGroup(composite, getLabelText());
		FormData fd = new FormData();
		fd.top = new FormAttachment();
		fd.left = new FormAttachment();
		fd.bottom = new FormAttachment(100);
		fd.right = new FormAttachment(100);
		this.slotsGroup.setLayoutData(fd);
		this.slotsGroup.setLayout(new GridLayout(2, false));
	}
	@Override
	public void refresh(){
		super.refresh();
		InstanceSpecification is = getInstanceSpecification();
		for(Control control:slotsGroup.getChildren()){
			control.dispose();
		}
		if(is != null){
			SlotComposite last = null;
			SlotComposite first = null;
			for(Slot slot:is.getSlots()){
				Label lbl = getWidgetFactory().createLabel(slotsGroup, NameConverter.separateWords(NameConverter.capitalize(slot.getDefiningFeature().getName())));
				lbl.setAlignment(SWT.TOP|SWT.LEFT);
				lbl.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
				lbl.setLayoutData(new GridData(STANDARD_LABEL_WIDTH + 55, 25));
				SlotComposite sc = new SlotComposite(slotsGroup, SWT.BORDER, getWidgetFactory(), getEditingDomain());
				sc.setInput(slot);
				if(last != null && last.getCurrentOclText()!=null&& sc.getCurrentOclText() != null){
					last.getCurrentOclText().setTabTo(sc.getCurrentOclText().getTextControl());
				}
				if(last == null){
					first = sc;
				}
				last = sc;
			}
			if(last != null && first != null && last.getCurrentOclText() != null){
				last.getCurrentOclText().setTabTo(first.getControl());
			}
			Control[] children = slotsGroup.getChildren();
			for(Control control:children){
				if(control instanceof SlotComposite){
					final SlotComposite slotComposite = (SlotComposite) control;
					slotComposite.pack();
					GridData layoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
					slotComposite.setLayoutData(layoutData);
				}
			}
			slotsGroup.pack();
			slotsGroup.getParent().layout();
		}
	}
	protected InstanceSpecification getInstanceSpecification(){
		return (InstanceSpecification) getEObject();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getInstanceSpecification_Slot();
	}
	@Override
	protected String getLabelText(){
		return "Slots";
	}
}
