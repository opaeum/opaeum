package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.opaeum.eclipse.uml.propertysections.common.TextChangeHelper;

public abstract class AbstractIntegerSubsection <E>extends AbstractTabbedPropertySubsection<Text,E> implements FocusListener{
	protected Integer defaultValue;

	public AbstractIntegerSubsection(IMultiPropertySection section){
		super(section);
	}

	@Override
	protected void hookControlListener(){
		new TextChangeHelper(){
			@Override
			public void textChanged(Control control){
				updateModel();
			}
		}.startListeningForEnter(getControl());
		this.getControl().addFocusListener(this);
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
	public void focusGained(FocusEvent e){
	}

	@Override
	public void focusLost(FocusEvent e){
		updateModel();
	}


	@Override
	protected Text createControl(Composite parent){
		return getWidgetFactory().createText(parent, "");
	}

	public void setDefaultValue(Integer i){
		this.defaultValue=i;
	}
	public Integer getDefaultValue(){
		return defaultValue;
	}
}