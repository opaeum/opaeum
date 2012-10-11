package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;

public class BooleanSubsection extends AbstractTabbedPropertySubsection<Button,Boolean> implements SelectionListener{
	private boolean defaultValue;
	public BooleanSubsection(IMultiPropertySection section){
		super(section);
	}

	
	public void widgetSelected(SelectionEvent e){
		super.updateModel();
	}
	public void widgetDefaultSelected(SelectionEvent e){
	}

	@Override
	protected Boolean getNewValue(){
		return getControl().getSelection();
	}

	@Override
	protected void populateControls(){
		if(getCurrentValue() instanceof Boolean){
			getControl().setSelection(getCurrentValue().equals(Boolean.TRUE));
		}else{
			getControl().setSelection(isDefaultValue());
			
		}
	}
	@Override
	protected Button createControl(Composite parent){
		Button b = getWidgetFactory().createButton(parent, "", SWT.CHECK);
		return b;
	}

	public boolean isDefaultValue(){
		return defaultValue;
	}

	public void setDefaultValue(boolean defaultValue){
		this.defaultValue = defaultValue;
	}

	@Override
	public void hookControlListener(){
		getControl().addSelectionListener(this);
	}


	public void setVisible(boolean b){
		super.label.setVisible(b);
		getControl().setVisible(b);
	}
}