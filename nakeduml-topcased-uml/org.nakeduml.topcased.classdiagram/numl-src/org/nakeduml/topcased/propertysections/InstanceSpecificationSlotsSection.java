package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class InstanceSpecificationSlotsSection extends AbstractTabbedPropertySection{
	private Group slotsGroup;
	@Override
	protected void createWidgets(Composite composite){
		this.slotsGroup = getWidgetFactory().createGroup(composite, "Slots");
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
		InstanceSpecification is = (InstanceSpecification) getEObject();
		for(Control control:slotsGroup.getChildren()){
			control.dispose();
		}
		SlotComposite last=null;
		SlotComposite first=null;
		for(Slot slot:is.getSlots()){
			getWidgetFactory().createLabel(slotsGroup, slot.getDefiningFeature().getName());
			SlotComposite sc = new SlotComposite(slotsGroup, SWT.BORDER,getWidgetFactory(),getEditingDomain());
			sc.setLayoutData(new GridData(SWT.FILL,SWT.NONE,true,false));
			sc.setInput(slot);
			if(last!=null && sc.getCurrentOclText()!=null){
				last.getCurrentOclText().setTabTo(sc.getCurrentOclText().getTextControl());
			}
			if(last==null){
				first=sc;
			}
			last=sc;
		}
		if(last!=null && first!=null){
			last.getCurrentOclText().setTabTo(first.getCurrentOclText().getTextControl());
		}
		slotsGroup.layout();
		getSectionComposite().layout();
		getSectionComposite().getParent().layout();
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
