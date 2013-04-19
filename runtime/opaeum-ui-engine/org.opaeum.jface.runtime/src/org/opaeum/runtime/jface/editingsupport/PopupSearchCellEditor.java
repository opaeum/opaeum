package org.opaeum.runtime.jface.editingsupport;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.rap.rwt.widgets.DialogCallback;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jface.widgets.ChooseDialog;
import org.opaeum.runtime.rwt.DialogUtil;

public class PopupSearchCellEditor extends DialogCellEditor{
	private FocusListener buttonFocusListener;
	private Button button;
	public PopupSearchCellEditor(Composite parent){
		super(parent);
	}
	protected Object[] getChoices(){
		return new Object[0];
	}
	protected ILabelProvider getLabelProvider(){
		return new DefaultLabelProvider();
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new DefaultLabelProvider(){
			@Override
			public String getText(Object value){
				return value.getClass().getSimpleName() + ": " + super.getText(value);
			}
		};
	}
	@Override
	protected void updateContents(Object value){
		super.updateContents(value == null ? null : ((IPersistentObject) value).getName());
	}
	@Override
	protected Object openDialogBox(Control cellEditorWindow){
		final ChooseDialog dialog = new ChooseDialog(cellEditorWindow.getShell(), getChoices());
		dialog.setLabelProvider(getLabelProvider());
		dialog.setAdvancedLabelProvider(getAdvancedLabeProvider());
		List<Object> selectedObjects = new ArrayList<Object>();
		selectedObjects.add(getValue());
		dialog.setInitialElementSelections(selectedObjects);
		DialogUtil.open(dialog, new DialogCallback(){
			@Override
			public void dialogClosed(int arg0){
				button.addFocusListener(getButtonFocusListener());
				if(dialog.getResult() != null && dialog.getResult().length > 0){
					boolean newValidState = isCorrect(dialog.getResult()[0]);
					if(newValidState){
						markDirty();
						doSetValue(dialog.getResult()[0]);
					}else{
						// try to insert the current value into the error message.
						setErrorMessage(MessageFormat.format(getErrorMessage(), new Object[]{dialog.getResult()[0].toString()}));
					}
					fireApplyEditorValue();
				}
			}
		});
		return null;
	}
	private FocusListener getButtonFocusListener(){
		if(buttonFocusListener == null){
			buttonFocusListener = new FocusListener(){
				public void focusGained(FocusEvent e){
				}
				public void focusLost(FocusEvent e){
					PopupSearchCellEditor.this.focusLost();
				}
			};
		}
		return buttonFocusListener;
	}
  protected Button createButton(Composite parent) {
  	return this.button=super.createButton(parent);
}

}