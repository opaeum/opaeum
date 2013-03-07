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
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;

public abstract class AbstractTabbedPropertySubsection<T extends Control,E> extends RecursiveAdapter{
	public static final int FILL = -1233;
	protected boolean isRefreshing = false;
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
	private boolean visible;
	protected AbstractTabbedPropertySubsection(IMultiPropertySection section){
		this.section = section;
		enabled = true;
		visible=true;
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
	public boolean isEnabled(){
		return enabled;
	}
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
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
			EObject e = getFeatureOwner(section.getSelectedObject());
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
		}else if(hasSelectedObject() && msg.getNotifier() instanceof EObject && msg.getFeature() instanceof EStructuralFeature){
			EObject eo = (EObject) msg.getNotifier();
			boolean inScope = false;
			while(eo != null){
				if(eo == section.getSelectedObject()){
					inScope = true;
					eo = null;
				}else{
					eo = EmfElementFinder.getContainer(eo);
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
	public E getCurrentValue(EObject featureOwner){
		if(featureOwner != null && getFeature() != null && getFeature().getEContainingClass().isInstance(featureOwner)){
			return (E) featureOwner.eGet(getFeature());
		}else{
			return null;
		}
	}
	public E getCurrentValue(){
		if(hasSelectedObject()){
			return getCurrentValue(getFeatureOwner(section.getSelectedObject()));
		}else{
			return null;
		}
	}
	public void refresh(){
		if(getControl() != null && !getControl().isDisposed()){
			isRefreshing = true;
			populateControls();
			if(section.getSelectedObject() == null
					|| (getFeature() != null && !getFeature().getEContainingClass().isInstance(getFeatureOwner(section.getSelectedObject())))){
				getControl().setEnabled(false);
			}else{
				getControl().setEnabled(enabled);
			}
			setVisible(visible);
			getControl().redraw();
			isRefreshing = false;
		}
	}
	protected void updateModel(){
		if(!isRefreshing){
			for(EObject selection:section.getEObjectList()){
				EObject featureOwner = getFeatureOwner(selection);
				Command cmd = buildCommand(selection, featureOwner);
				if(cmd != null){
					section.getEditingDomain().getCommandStack().execute(cmd);
				}
			}
		}
	}
	protected Command buildCommand(EObject selection,EObject featureOwner){
		if(featureOwner != null){
			Command cmd = SetCommand.create(section.getEditingDomain(), featureOwner, getFeature(), getNewValue());
			return cmd;
		}else{
			return null;
		}
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
		cgd.verticalAlignment = SWT.FILL;
		if(getControlWidth() == AbstractTabbedPropertySubsection.FILL){
			cgd.grabExcessHorizontalSpace = true;
			cgd.horizontalAlignment = SWT.FILL;
		}else{
			cgd.minimumWidth = getControlWidth();
			cgd.widthHint = getControlWidth();
			cgd.horizontalAlignment = SWT.LEFT;
		}
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
			EObject featureOwner = getFeatureOwner(section.getSelectedObject());
			EList<Adapter> eAdapters = featureOwner.eAdapters();
			eAdapters.remove(this);
		}
	}
	protected boolean hasSelectedObject(){
		return section.getSelectedObject() != null && getFeatureOwner(section.getSelectedObject()) != null;
	}
	protected EObject getFeatureOwner(EObject eObject){
		return section.getFeatureOwner(eObject);
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
	public boolean isVisible(){
		return visible;
	}
	public void setVisible(boolean visible){
		if(getControl()!=null){
			getControl().setVisible(visible);
		}
		if(label!=null){
			label.setVisible(visible);
		}
		this.visible = visible;
	}
}