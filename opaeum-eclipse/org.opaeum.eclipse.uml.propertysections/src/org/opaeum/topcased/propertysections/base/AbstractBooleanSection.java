package org.opaeum.topcased.propertysections.base;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Element;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractBooleanSection extends AbstractTabbedPropertySection{
	protected Button check;
	private Label label;
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
	public void refresh(){
		super.refresh();
		List<EObject> eObjectList = getEObjectList();
		Boolean isGreyed = Boolean.FALSE;
		Boolean selection = null;
		for(EObject eObject:eObjectList){
			Boolean value = (Boolean) getElement(eObject).eGet(getFeature());
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
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		data.height = 18;
		check.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(check, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(check, 0, SWT.TOP);
		data.height = 15;
		label.setLayoutData(data);
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		this.label = getWidgetFactory().createLabel(composite, getLabelText());
		this.check = getWidgetFactory().createButton(composite, "", SWT.CHECK);
		check.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				check.setGrayed(false);
				CompoundCommand cc = new CompoundCommand();
				List<EObject> list = getEObjectList();
				for(EObject eObject:list){
					cc.append(SetCommand.create(getEditingDomain(), getElement(eObject), getFeature(), check.getSelection()));
				}
				getEditingDomain().getCommandStack().execute(cc);
				refresh();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
	}
}