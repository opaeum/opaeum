package org.opaeum.eclipse.uml.propertysections.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
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
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.topcased.facilities.dialogs.ChooseDialog;
import org.topcased.tabbedproperties.providers.AdvancedLabelProvider;
import org.topcased.tabbedproperties.providers.LabelProviderFactory;

public class OpaeumSingleObjectChooser{
	protected TabbedPropertySheetWidgetFactory widgetFactory;
	private Text field;
	private Button chooseBt;
	private Object[] objects;
	private ILabelProvider labelProvider;
	private ILabelProvider advancedLabelProvider;
	private Object selectedObject;
	private IChoiceProvider choiceProvider;
	private Composite contentPane;
	private HashMap<SelectionListener,TypedListener> listeners = new HashMap<SelectionListener,TypedListener>();
	public OpaeumSingleObjectChooser(Composite parent,TabbedPropertySheetWidgetFactory factory,int style){
		this.widgetFactory = factory;
		this.contentPane = new Composite(parent, style);
		this.labelProvider=new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory());
		this.advancedLabelProvider=new OpaeumQualifiedNameLabelProvider(new OpaeumItemProviderAdapterFactory());
		factory.adapt(contentPane);
		setLayout(contentPane);
		createContents(contentPane);
		hookListeners();
	}
	protected void createContents(Composite parent){
		setLayout(parent);
		field = widgetFactory.createText(parent, "", SWT.READ_ONLY|SWT.BORDER);
		field.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		chooseBt = widgetFactory.createButton(parent, "...", SWT.PUSH);
		chooseBt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
	}
	private void setLayout(Composite parent){
		int numColumns = getNumberOfColumns();
		if(numColumns < 2){
			numColumns = 2;
		}
		GridLayout layout = new GridLayout(numColumns, false);
		layout.marginHeight = 0;
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);
	}
	protected int getNumberOfColumns(){
		return 2;
	}
	protected void hookListeners(){
		chooseBt.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				handleChoose();
			}
		});
	}
	public void setEditable(boolean isEditable){
		if(field != null){
			field.setEditable(isEditable);
		}
	}
	public boolean isEditable(){
		return field != null && field.getEditable();
	}
	public void setText(String text){
		if(isEditable()){
			field.setText(text);
		}
	}
	private void handleChoose(){
		ChooseDialog dialog = new ChooseDialog(contentPane.getShell(), getObjects());
		dialog.setLabelProvider(labelProvider);
		if(advancedLabelProvider == null){
			LabelProviderFactory factory = AdvancedLabelProvider.getAdvancedLabelProviderFactory4CurrentEditor();
			if(factory != null){
				advancedLabelProvider = factory.createAdapterFactory();
			}
		}
		dialog.setAdvancedLabelProvider(advancedLabelProvider);
		List<Object> selectedObjects = new ArrayList<Object>();
		selectedObjects.add(selectedObject);
		dialog.setInitialElementSelections(selectedObjects);
		if(dialog.open() == Window.OK){
			Object[] selection = dialog.getResult();
			if(selection != null && selection.length > 0){
				setSelection(selection[0]);
			}else{
				setSelection(null);
			}
			Event e = new Event();
			contentPane.notifyListeners(SWT.Selection, e);
		}
	}
	protected Object[] getObjects(){
		if(objects == null){
			objects = choiceProvider.getChoices();
		}
		return objects;
	}
	public void setChangeable(boolean isChangeable){
		chooseBt.setEnabled(isChangeable);
	}
	public Object getSelection(){
		return selectedObject;
	}
	public void setSelection(Object selection){
		this.objects = null;
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
		this.listeners.put(listener, typedListener);
		contentPane.addListener(SWT.Selection, typedListener);
	}
	public void addTextKeyListener(KeyListener listener){
		field.addKeyListener(listener);
	}
	public void removeSelectionListener(SelectionListener listener){
		if(listener == null){
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		contentPane.removeListener(SWT.Selection, listeners.remove(listener));
	}
	static class NullObject{
		public String toString(){
			return "";
		}
		public boolean equals(Object obj){
			return obj instanceof NullObject;
		}
	}
	protected TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return widgetFactory;
	}
	public void setEnabled(boolean enabled){
//		contentPane.setEnabled(enabled);
		field.setEnabled(enabled);
		chooseBt.setEnabled(enabled);
	}
	public IChoiceProvider getChoiceProvider(){
		return choiceProvider;
	}
	public void setChoiceProvider(IChoiceProvider choiceProvider){
		this.choiceProvider = choiceProvider;
	}
	public Composite getContentPane(){
		return contentPane;
	}
}
