package org.opaeum.eclipse.uml.propertysections.subsections;

import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.uml2.uml.InstanceValue;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.core.InstanceSpecificationSlotsSection;

/**
 * NB!! this class considers the opaqueExpression to be the value. As text changes, new OpaqueExpression will be created
 * 
 * @author ampie
 * 
 */
public class InstanceValueSubsection extends AbstractTabbedPropertySubsection<ScrolledComposite,InstanceValue>{
	public InstanceValueSubsection(IMultiPropertySection section){
		super(section);
	}
	private Group slotGroup;
	private ScrolledComposite scrolledComposite;
	@Override
	protected InstanceValue getNewValue(){
		if(getCurrentValue().eClass().getEStructuralFeatures().contains(getFeature())){
			return (InstanceValue) getCurrentValue().eGet(getFeature());
		}else{
			return null;
		}
	}
	@Override
	protected int getModelSubscriptionLevel(){
		return 2;
	}
	@Override
	protected void populateControls(){
		if(getCurrentValue() instanceof InstanceValue){
			getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			getControl().getParent().setBackground(new org.eclipse.swt.graphics.Color(Display.getCurrent(), new RGB(127, 255, 64)));
			setVisible(true);
			InstanceSpecificationSlotsSection.populateSlots(Collections.singletonList(getCurrentValue().getInstance()), slotGroup, getWidgetFactory(), section.getEditingDomain(), null);
			getControl().getParent().layout();
		}else{
			setVisible(false);
		}
	}
	@Override
	public void hookControlListener(){
	}
	@Override
	protected ScrolledComposite createControl(Composite parent){
		this.scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);
		slotGroup= new Group(scrolledComposite,SWT.NONE);
		slotGroup.setLayout(new GridLayout(2,false));
		scrolledComposite.setContent(slotGroup);
		return scrolledComposite;
	}
}
