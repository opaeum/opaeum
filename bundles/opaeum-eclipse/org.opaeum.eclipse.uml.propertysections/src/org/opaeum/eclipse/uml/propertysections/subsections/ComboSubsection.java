package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class ComboSubsection extends AbstractTabbedPropertySubsection<Combo,Object> implements SelectionListener{
	private IChoiceProvider choiceProvider;
	private ComboViewer viewer;
	protected ILabelProvider labelProvider;
	public ComboSubsection(IMultiPropertySection section){
		super(section);
	}
	public void widgetSelected(SelectionEvent e){
		super.updateModel();
	}
	public void widgetDefaultSelected(SelectionEvent e){
	}
	@Override
	protected Object getNewValue(){
		return (Object) ((IStructuredSelection) viewer.getSelection()).getFirstElement();
	}
	@Override
	protected void populateControls(){
		viewer.setLabelProvider(getLabelProvider());
		viewer.setInput(getChoiceProvider().getChoices());
		Object cv = getCurrentValue();
		if(cv == null){
			viewer.setSelection(new StructuredSelection());
		}else{
			viewer.setSelection(new StructuredSelection(cv));
		}
	}
	@Override
	protected Combo createControl(Composite parent){
		viewer = new ComboViewer(parent, SWT.READ_ONLY);
		viewer.setContentProvider(new ArrayContentProvider());
		return viewer.getCombo();
	}
	public ILabelProvider getLabelProvider(){
		if(this.labelProvider==null){
			labelProvider=new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory());
		}
		return labelProvider;
	}
	public void setLabelProvider(ILabelProvider labelProvider){
		this.labelProvider = labelProvider;
		viewer.setLabelProvider(labelProvider);
	}
	@Override
	public void hookControlListener(){
		getControl().addSelectionListener(this);
	}
	public void setVisible(boolean b){
		super.label.setVisible(b);
		getControl().setVisible(b);
	}
	public IChoiceProvider getChoiceProvider(){
		return choiceProvider;
	}
	public void setChoiceProvider(IChoiceProvider choiceProvider){
		this.choiceProvider = choiceProvider;
	}
}