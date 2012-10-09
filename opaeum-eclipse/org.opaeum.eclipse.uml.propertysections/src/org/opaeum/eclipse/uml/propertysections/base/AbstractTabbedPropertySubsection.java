package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class AbstractTabbedPropertySubsection<T extends Control, E> extends AdapterImpl{
	boolean isRefreshing = false;
	private IMultiPropertySection section;
	private T control;
	protected Label label;
	private EStructuralFeature feature;
	private String labelText;
	private int labelWidth;
	private int controlWidth;
	private Composite composite;
	protected AbstractTabbedPropertySubsection(IMultiPropertySection section){
		this.section = section;
		section.addSubsection(this);
	}
	public EStructuralFeature getFeature(){
		return feature;
	}
	protected abstract E getNewValue();
	protected abstract void populateControls();
	protected abstract void hookControlListener();
	protected abstract T createControl(Composite parent);
	public Composite getComposite(){
		return composite;
	}
	public void createWidgets(Composite parent){
		this.composite=parent;
		label = getWidgetFactory().createLabel(parent, getLabelText());
		this.setControl(createControl(parent));
	}
	protected TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return section.getWidgetFactory();
	}
	protected void hookModelListener(){
		if(hasSelectedObject()){
			EObject e = section.getFeatureOwner(section.getEObject());
			if(!e.eAdapters().contains(this)){
				e.eAdapters().add(this);
			}
		}
	}
	protected void unhookModelListener(){
		if(hasSelectedObject()){
			section.getFeatureOwner(section.getEObject()).eAdapters().remove(this);
		}
	}
	@Override
	public void notifyChanged(Notification msg){
		if((getControl() == null || getControl().isDisposed()) && msg.getNotifier() instanceof Notifier){
			((Notifier) msg.getNotifier()).eAdapters().remove(this);
		}else if(hasSelectedObject() 
				&& msg.getNotifier().equals(section.getFeatureOwner(section.getEObject())) 
				&& msg.getFeature() !=null && msg.getFeature().equals(getFeature())){
			refresh();
		}
	}
	@SuppressWarnings("unchecked")
	public E getCurrentValue(EObject e){
		return (E)e.eGet(getFeature());
	}
	public E getCurrentValue(){
		if(hasSelectedObject()){
			return getCurrentValue(section.getFeatureOwner(section.getEObject()));
		}else{
			return null;
		}
	}
	public void refresh(){
		if(getControl() != null && !getControl().isDisposed()){
			isRefreshing = true;
			populateControls();
			getControl().redraw();
			isRefreshing = false;
		}
	}
	protected void updateModel(){
		if(!isRefreshing){
			for(EObject eObject:section.getEObjectList()){
				EObject featureOwner = section.getFeatureOwner(eObject);
				if(featureOwner != null){
					Command cmd = buildCommand(eObject, featureOwner);
					section.getEditingDomain().getCommandStack().execute(cmd);
				}
			}
		}
	}

	protected Command buildCommand(EObject selection, EObject featureOwner){
		Command cmd = SetCommand.create(section.getEditingDomain(), featureOwner, getFeature(), getNewValue());
		return cmd;
	}
	public T getControl(){
		return control;
	}
	private void setControl(T control){
		this.control = control;
	}
	public void setFeature(EStructuralFeature feature){
		this.feature = feature;
	}
	public String getLabelText(){
		return labelText;
	}
	public void setLabelText(String labelText){
		this.labelText = labelText;
	}
	public void updateLayoutData(){
		GridData lgd = new GridData();
		lgd.minimumWidth = getLabelWidth();
		label.setLayoutData(lgd);
		GridData cgd = new GridData();
		cgd.minimumWidth = getControlWidth();
		cgd.verticalAlignment=SWT.CENTER;
		cgd.grabExcessVerticalSpace=true;
		getControl().setLayoutData(cgd);
		getComposite().pack();
	}
	public int getLabelWidth(){
		return labelWidth;
	}
	public void setLabelWidth(int labelWidth){
		this.labelWidth = labelWidth;
	}
	public int getControlWidth(){
		return controlWidth;
	}
	public void setControlWidth(int controlWidth){
		this.controlWidth = controlWidth;
	}
	public void removeModelListener(){
		if(hasSelectedObject()){
			section.getFeatureOwner(section.getFeatureOwner(section.getEObject())).eAdapters().remove(this);
		}
	}
	protected boolean hasSelectedObject(){
		return section.getEObject()!=null && section.getFeatureOwner(section.getEObject())!=null;
	}
}