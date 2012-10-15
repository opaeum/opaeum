package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.swt.custom.TextChangeListener;
import org.eclipse.swt.custom.TextChangedEvent;
import org.eclipse.swt.custom.TextChangingEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;

public abstract class RecreatingOpaqueExpressionSection extends AbstractOpaeumPropertySection implements TextChangeListener{
	protected OpaqueExpressionComposite oclComposite;
	@Override
	public String getLabelText(){
		return "Body";
	}
	@Override
	public Control getPrimaryInput(){
		return oclComposite;
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	@Override
	protected void hookListeners(){
		oclComposite.addTextChangeListener(this);
	}
	protected void createWidgets(Composite composite){
		oclComposite = new OpaqueExpressionComposite(composite, getWidgetFactory());
	}
	protected String getExpressionLabel(){
		return "Body expression";
	}
	protected void setSectionData(Composite composite){
		FormData fd = new FormData();
		fd.left = new FormAttachment(labelCombo);
		fd.top = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(100, 0);
		this.oclComposite.setLayoutData(fd);
	}
	@Override
	public void populateControls(){
		oclComposite.setOclContext((NamedElement) getSelectedObject(), null);
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		oclComposite.setEnabled(enabled);
	}
	@Override
	public void textChanging(TextChangingEvent event){
	}
	@Override
	public void textChanged(TextChangedEvent event){
		updateModel(EmfValueSpecificationUtil.buildOpaqueExpression((NamedElement)getSelectedObject(),getFeature().getName(), oclComposite.getTextControl().getText()));
	}
	@Override
	public void textSet(TextChangedEvent event){
	}
}
