package org.opaeum.eclipse.uml.propertysections.base;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

public abstract class AbstractBooleanPropertySection extends AbstractOpaeumPropertySection{
	protected Button check;
	public AbstractBooleanPropertySection(){
		super();
	}
	protected abstract Boolean getDefaultValue();
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	@Override
	public Control getPrimaryInput(){
		return check;
	}
	@Override
	public void populateControls(){
		if(!check.isDisposed()){
			List<EObject> eObjectList = getEObjectList();
			Boolean isGreyed = Boolean.FALSE;
			Boolean selection = null;
			for(EObject eObject:eObjectList){
				Boolean value = getBooleanValue(eObject);
				if(value == null){
					value = getDefaultValue();
				}
				if(selection == null){
					selection = value;
				}else if(!selection.equals(value)){
					isGreyed = true;
					break;
				}
			}
			if(isGreyed){
				check.setGrayed(true);
				check.setSelection(true);
			}else{
				check.setGrayed(false);
				check.setSelection(selection);
			}
		}
	}
	protected Boolean getBooleanValue(EObject eObject){
		// TODO pull up
		EObject featureOwner = getFeatureOwner(eObject);
		if(featureOwner != null){
			return (Boolean) featureOwner.eGet(getFeature(featureOwner));
		}
		return null;
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(labelCombo);
		data.right = new FormAttachment(100, 0);
		data.bottom= new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		check.setLayoutData(data);
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		this.check = getWidgetFactory().createButton(composite, "", SWT.CHECK);
		check.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				handleSelection();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
	}
	protected void handleSelection(){
		updateModel(check.getSelection());
	}
}