package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.common.TextChangeHelper;

public class StringSubsection extends AbstractTabbedPropertySubsection<Text,String>{
	public StringSubsection(IMultiPropertySection section){
		super(section);
	}
	@Override
	protected String getNewValue(){
		return getControl().getText();
	}
	@Override
	protected void populateControls(){
		String cv = getCurrentValue();
		getControl().setText(cv == null ? "" : cv);
	}
	@Override
	protected Text createControl(Composite parent){
		return getWidgetFactory().createText(parent, "", SWT.BORDER);
	}
	@Override
	public void hookControlListener(){
		TextChangeHelper helper = new TextChangeHelper(){
			@Override
			public void textChanged(Control control){
				updateModel();
			}
		};
		helper.startListeningTo(getControl());
		helper.startListeningForEnter(getControl());
	}
	public void setVisible(boolean b){
		super.label.setVisible(b);
		getControl().setVisible(b);
	}
}