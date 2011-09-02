package org.nakeduml.topcased.propertysections;

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
				Label lbl = getWidgetFactory().createLabel(slotsGroup, slot.getDefiningFeature().getName());
				lbl.setLayoutData(new GridData(STANDARD_LABEL_WIDTH+55, 25));
				SlotComposite sc = new SlotComposite(slotsGroup, SWT.BORDER, getWidgetFactory(), getEditingDomain());
				GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, false);
				layoutData.minimumHeight=25;
				sc.setLayoutData(layoutData);
				sc.setInput(slot);
				if(last != null && sc.getCurrentOclText() != null){
					last.getCurrentOclText().setTabTo(sc.getCurrentOclText().getTextControl());
				}
				if(last == null){
					first = sc;
				}
				last = sc;
			}
			if(last != null && first != null){
				last.getCurrentOclText().setTabTo(first.getCurrentOclText().getTextControl());
			}
			slotsGroup.layout();
			int height = slotsGroup.getChildren().length*35;
			if(height==0){
				height=25;
			}
			slotsGroup.getParent().setSize(slotsGroup.getParent().getSize().x, height+5);
			slotsGroup.getParent().getParent().getParent().layout();
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
