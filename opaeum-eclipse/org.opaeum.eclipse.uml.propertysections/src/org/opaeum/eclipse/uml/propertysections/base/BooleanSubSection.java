package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class BooleanSubSection extends AbstractTabbedPropertySubsection<Button,Boolean> implements SelectionListener{
	private boolean defaultValue;
	public BooleanSubSection(IMultiPropertySection section){
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
		if(!(getCurrentValue() instanceof Boolean)){
			getControl().setSelection(isDefaultValue());
		}else{
			getControl().setSelection(getCurrentValue().equals(Boolean.TRUE));
			
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
	protected void hookControlListener(){
		getControl().addSelectionListener(this);
	}


	public void setVisible(boolean b){
		super.label.setVisible(b);
		getControl().setVisible(b);
		// TODO Auto-generated method stub
		
	}
}