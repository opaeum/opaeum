package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;

public abstract class AbstractTabbedPropertySubsection<T extends Control,E> extends RecursiveAdapter{
	boolean isRefreshing = false;
	protected IMultiPropertySection section;
	private T control;
	protected Label label;
	private EStructuralFeature feature;
	private String labelText;
	private int labelWidth;
	private int controlWidth;
	private Composite composite;
	private Integer columnSpan;
	private Integer rowSpan;
	private boolean enabled;
	protected AbstractTabbedPropertySubsection(IMultiPropertySection section){
		this.section = section;
		enabled=true;
		section.addSubsection(this);
	}
	public EStructuralFeature getFeature(){
		return feature;
	}
	protected abstract E getNewValue();
	protected abstract void populateControls();
	public abstract void hookControlListener();
	protected abstract T createControl(Composite parent);
	public Composite getComposite(){
		return composite;
	}
	public void setEnabled(boolean enabled){
		this.enabled=enabled;
		if(!(getControl() == null || getControl().isDisposed())){
				getControl().setEnabled(enabled);
		}
	}
	public void createWidgets(Composite parent){
		this.composite = parent;
		label = getWidgetFactory().createLabel(parent, getLabelText(), SWT.NONE);
		this.setControl(createControl(parent));
		setEnabled(enabled);
	}
	protected TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return section.getWidgetFactory();
	}
	public void hookModelListener(){
		if(hasSelectedObject()){
			EObject e = section.getFeatureOwner(section.getEObject());
			subscribeTo(e, getModelSubscriptionLevel());
		}
	}
	protected int getModelSubscriptionLevel(){
		return 2;
	}
	protected void unhookModelListener(){
		unsubscribe();
	}
	@Override
	public void safeNotifyChanged(Notification msg){
		if((getControl() == null || getControl().isDisposed())){
			unsubscribe();
		}else if(hasSelectedObject() && msg.getNotifier() instanceof EObject && msg.getFeature() != null /*
																																																			 * &&
																																																			 * msg.getFeature().equals(getFeature
																																																			 * ())
																																																			 */){
			EObject eo = (EObject) msg.getNotifier();
			boolean inScope = false;
			while(eo != null){
				if(eo == section.getEObject()){
					inScope = true;
					eo = null;
				}else{
					eo = eo.eContainer();
				}
			}
			if(inScope){
				refresh();
			}else{
				unsubscribe();
			}
		}
	}
	@SuppressWarnings("unchecked")
	public E getCurrentValue(EObject e){
		if(getFeature() != null && getFeature().getEContainingClass().isSuperTypeOf(e.eClass())){
			return (E) e.eGet(getFeature());
		}else{
			return null;
		}
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
			if(section.getEObject()==null || !getFeature().getEContainingClass().isSuperTypeOf(section.getEObject().eClass())){
				getControl().setEnabled(false);
			}else{
				getControl().setEnabled(enabled);
			}
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
	protected Command buildCommand(EObject selection,EObject featureOwner){
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
		lgd.verticalAlignment = SWT.CENTER;
		lgd.widthHint = getLabelWidth();
		// lgd.grabExcessHorizontalSpace=true;
		label.setLayoutData(lgd);
		GridData cgd = new GridData();
		cgd.minimumWidth = getControlWidth();
		cgd.widthHint = getControlWidth();
		cgd.verticalAlignment = SWT.FILL;
		cgd.horizontalAlignment = SWT.LEFT;
		cgd.grabExcessVerticalSpace = true;
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
			EObject featureOwner = section.getFeatureOwner(section.getEObject());
			EList<Adapter> eAdapters = featureOwner.eAdapters();
			eAdapters.remove(this);
		}
	}
	protected boolean hasSelectedObject(){
		return section.getEObject() != null && section.getFeatureOwner(section.getEObject()) != null;
	}
	public Integer getColumnSpan(){
		return columnSpan;
	}
	public void setColumnSpan(Integer columnSpan){
		this.columnSpan = columnSpan;
	}
	public Integer getRowSpan(){
		return rowSpan;
	}
	public void setRowSpan(Integer rowSpan){
		this.rowSpan = rowSpan;
	}
	public void dispose(){
		removeModelListener();
		if(getComposite() != null){
			getComposite().dispose();
		}
	}
}