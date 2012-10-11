package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.opaeum.eclipse.uml.propertysections.common.TextChangeHelper;

public abstract class AbstractStringPropertySection extends AbstractOpaeumPropertySection{
	protected Text text;
	private TextChangeHelper listener;
	protected void createWidgets(Composite composite){
		text = getWidgetFactory().createText(composite, "", SWT.BORDER);
		if(getFeature() != null){
			boolean isChangeable = getFeature().isChangeable();
			text.setEditable(isChangeable);
			text.setEnabled(isChangeable);
		}
	}
	@Override
	public Control getPrimaryInput(){
		return text;
	}
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		data.bottom = new FormAttachment(100, 0);
		text.setLayoutData(data);
	}
	protected void hookListeners(){
		listener = new TextChangeHelper(){
			@Override
			public void textChanged(Control control){
				handleTextModified();
			}
		};
		listener.startListeningTo(text);
		if((getStyle() & SWT.MULTI) == 0){
			listener.startListeningForEnter(text);
		}
	}
	protected int getStyle(){
		return SWT.SINGLE;
	}
	public void refresh(){
		super.refresh();
		if(text != null){
			text.setText(getFeatureAsString());
		}
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(text != null){
			text.setEnabled(enabled);
		}
	}
	protected void handleTextModified(){
		createCommand(getOldFeatureValue(), getNewFeatureValue(text.getText()));
	}
	protected String getFeatureAsString(){
		String string = getEObject() == null ? null : (String) getEObject().eGet(getFeature());
		if(string == null){
			return "";
		}
		return string;
	}
	protected Object getNewFeatureValue(String newText){
		return newText;
	}
	protected Object getOldFeatureValue(){
		return getFeatureAsString();
	}
}
