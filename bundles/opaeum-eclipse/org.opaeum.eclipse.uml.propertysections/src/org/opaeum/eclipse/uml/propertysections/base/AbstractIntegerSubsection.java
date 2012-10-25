package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.opaeum.eclipse.uml.propertysections.common.TextChangeHelper;

public abstract class AbstractIntegerSubsection <E>extends AbstractTabbedPropertySubsection<Text,E> {
	protected Integer defaultValue;

	public AbstractIntegerSubsection(IMultiPropertySection section){
		super(section);
	}

	@Override
	public void hookControlListener(){
		new TextChangeHelper(){
			@Override
			public void textChanged(Control control){
				updateModel();
			}

		}.startListeningForEnter(getControl());
	}

	protected Integer getNewIntValue(){
		int parseInt;
		try{
			String text = getControl().getText();
			if(text.contains("*")){
				parseInt = -1;
			}else{
				parseInt = Integer.parseInt(text);
			}
		}catch(Exception e){
			return null;
		}
		return parseInt;
	}



	@Override
	protected Text createControl(Composite parent){
		return getWidgetFactory().createText(parent, "",SWT.BORDER);
	}

	public void setDefaultValue(Integer i){
		this.defaultValue=i;
	}
	public Integer getDefaultValue(){
		return defaultValue;
	}
}