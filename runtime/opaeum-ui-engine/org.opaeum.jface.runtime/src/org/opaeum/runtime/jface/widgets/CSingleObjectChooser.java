package org.opaeum.runtime.jface.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.rap.rwt.widgets.DialogCallback;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;
import org.opaeum.runtime.rwt.DialogUtil;

public class CSingleObjectChooser extends Composite{
	private Text field;
	private Button chooseBt;
	private Object[] objects;
	private ILabelProvider labelProvider;
	private ILabelProvider advancedLabelProvider;
	private Object selectedObject;
	public CSingleObjectChooser(Composite parent,int style){
		super(parent, style);
		createContents(this);
		hookListeners();
	}
	protected void createContents(Composite parent){
		setLayout(parent);
		field = new Text(parent, SWT.FLAT | SWT.BORDER | SWT.READ_ONLY);
		field.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		chooseBt = new Button(parent, SWT.PUSH);
		chooseBt.setText("...");
	}
	private void setLayout(Composite parent){
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);
	}
	protected void hookListeners(){
		chooseBt.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				handleChoose();
			}
		});
	}
	public void setChoices(Object[] objs){
		if(objs != null && objs.length > 0){
			// replace any null entry with a NullObject
			Object[] objects2 = new Object[objs.length];
			for(int cpt = 0;cpt < objs.length;cpt++){
				if(objs[cpt] == null){
					objects2[cpt] = new NullObject();
				}else{
					objects2[cpt] = objs[cpt];
				}
			}
			this.objects = objects2;
		}
		setSelection(null);
	}
	/**
	 * Sets the editable state of the text field. The default value is READ-ONLY. However clients may set this value as true by calling this
	 * method
	 * 
	 * @param isEditable
	 */
	public void setEditable(boolean isEditable){
		if(field != null){
			field.setEditable(isEditable);
		}
	}
	/**
	 * Evaluates the the editable state of the text field.
	 * 
	 * @return true if the text field is editable
	 */
	public boolean isEditable(){
		return field != null && field.getEditable();
	}
	/**
	 * If the text field is editable, replace the actual text with the content of the parameter. Does nothing when the text field is not
	 * editable.
	 * 
	 * @param text
	 *          the new string to put in the text field
	 */
	public void setText(String text){
		if(isEditable()){
			field.setText(text);
		}
	}
	/**
	 * Set the provider that displays the objects
	 * 
	 * @param provider
	 *          the LabelProvider
	 */
	public void setLabelProvider(ILabelProvider provider){
		labelProvider = provider;
	}
	public void setAdvancedLabelProvider(ILabelProvider provider){
		advancedLabelProvider = provider;
	}
	private void handleChoose(){
		final ChooseDialog dialog = new ChooseDialog(getShell(), objects);
		dialog.setLabelProvider(labelProvider);
		dialog.setAdvancedLabelProvider(advancedLabelProvider);
		List<Object> selectedObjects = new ArrayList<Object>();
		selectedObjects.add(selectedObject);
		dialog.setInitialElementSelections(selectedObjects);
		DialogUtil.open(dialog, new DialogCallback(){
			@Override
			public void dialogClosed(int returnCode){
				if(returnCode == Window.OK){
					Object[] selection = dialog.getResult();
					if(selection != null && selection.length > 0){
						setSelection(selection[0]);
					}else{
						setSelection(null);
					}
					Event e = new Event();
					notifyListeners(SWT.Selection, e);
				}
			}
		});
	}
	public void setChangeable(boolean isChangeable){
		chooseBt.setEnabled(isChangeable);
	}
	public Object getSelection(){
		return selectedObject;
	}
	public void setSelection(Object selection){
		if("".equals(selection)){
			selectedObject = null;
		}else{
			selectedObject = selection;
		}
		String name = "";
		if(selectedObject != null){
			name = labelProvider.getText(selectedObject);
			if(name == null){
				name = "";
			}
		}
		field.setText(name);
	}
	public void addSelectionListener(SelectionListener listener){
		if(listener == null){
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Selection, typedListener);
	}
	public void addTextKeyListener(KeyListener listener){
		field.addKeyListener(listener);
	}
	public void removeSelectionListener(SelectionListener listener){
		if(listener == null){
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		removeListener(SWT.Selection, new TypedListener(listener));
	}
	static class NullObject{
		public String toString(){
			return "";
		}
		public boolean equals(Object obj){
			return obj instanceof NullObject;
		}
	}
	@Override
	public void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		field.setEnabled(enabled);
		chooseBt.setEnabled(enabled);
	}
}
