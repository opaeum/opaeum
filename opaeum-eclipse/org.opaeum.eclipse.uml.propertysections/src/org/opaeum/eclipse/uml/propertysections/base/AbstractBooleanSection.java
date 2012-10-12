package org.opaeum.eclipse.uml.propertysections.base;

import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
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
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Element;

public abstract class AbstractBooleanSection extends AbstractOpaeumPropertySection{
	protected Button check;
	public AbstractBooleanSection(){
		super();
	}
	protected abstract Boolean getDefaultValue();
	protected abstract Element getElement(EObject eObject);
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	@Override
	public Control getPrimaryInput(){
		return check;
	}
	@Override
	public void refresh(){
		super.refresh();
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
		Boolean value = (Boolean) getElement(eObject).eGet(getFeature());
		return value;
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
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
		check.setGrayed(false);
		CompoundCommand cc = new CompoundCommand();
		List<EObject> list = getEObjectList();
		for(EObject eObject:list){
			cc.append(SetCommand.create(getEditingDomain(), getElement(eObject), getFeature(), check.getSelection()));
		}
		getEditingDomain().getCommandStack().execute(cc);
		refresh();
	}
}