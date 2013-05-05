package org.opaeum.eclipse.uml.propertysections.base;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.uml.propertysections.common.TextChangeHelper;

public abstract class AbstractStringPropertySection extends AbstractOpaeumPropertySection{
	protected Text text;
	private TextChangeHelper listener;
	protected void createWidgets(Composite composite){
		text = getWidgetFactory().createText(composite, "", SWT.BORDER);
		listener = null;
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
		data.left = new FormAttachment(labelCombo);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		text.setLayoutData(data);
	}
	protected void hookListeners(){
		if(listener == null){
			listener = new TextChangeHelper(){
				@Override
				public void textChanged(Control control){
					handleTextModified((Text) control);
				}
			};
			listener.startListeningTo(text);
			if((getStyle() & SWT.MULTI) == 0){
				listener.startListeningForEnter(text);
			}
		}
	}
	protected int getStyle(){
		return SWT.SINGLE;
	}
	@Override
	public void populateControls(){
		if(text != null && !text.isDisposed()){
			Set<String> values = new HashSet<String>();
			List<EObject> eObjectList = getEObjectList();
			for(EObject eObject:eObjectList){
				EObject owner = getFeatureOwner(eObject);
				if(owner != null){
					Object value = getFeatureAsString(owner);
					values.add(value == null ? "" : value.toString());
				}else{
					values.add("");
				}
			}
			if(values.size() == 1){
				text.setText(values.iterator().next());
			}else{
				text.setText("");
			}
			if(values.size() == 1){
				text.setText(values.iterator().next());
			}else{
				text.setText("");
			}
		}
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(text != null){
			text.setEnabled(enabled);
		}
	}
	protected void handleTextModified(Text text){
		if(text != this.text){
			OpaeumEclipsePlugin.logWarning("String PropertySection " + getClass().getSimpleName() + " received a textChange event from an unknown text field");
		}else if(text.isDisposed()){
			OpaeumEclipsePlugin.logWarning("String PropertySection " + getClass().getSimpleName() + " received a textChange event from a disposed text field");
		}else{
			updateModel(getNewFeatureValue(text.getText()));
		}
	}
	protected String getFeatureAsString(EObject owner){
		if(owner != null){
			Object eGet = owner.eGet(getFeature(owner));
			return eGet == null ? "" : eGet.toString();
		}
		return "";
	}
	protected Object getNewFeatureValue(String newText){
		return newText;
	}
}
