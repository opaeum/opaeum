package org.opaeum.eclipse.uml.propertysections.common;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.AESCipherImpl;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.DecoratedField;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class OpaeumObjectChooser extends Viewer{
	protected TabbedPropertySheetWidgetFactory widgetFactory;
	private Button chooseBt;
	private Object[] objects;
	private ILabelProvider labelProvider;
	private ILabelProvider advancedLabelProvider;
	private IChoiceProvider choiceProvider;
	private Composite contentPane;
	public static final int SINGLE = 11234;
	public static final int MULTI = 11231234;
	private boolean isSingle = false;
	private Text text;
	private ChooserContentAssistHelper contentAssistHelper;
	private IStructuredSelection ss;
	public OpaeumObjectChooser(Composite parent,TabbedPropertySheetWidgetFactory factory,int style){
		this.widgetFactory = factory;
		this.contentPane = new Composite(parent, SWT.NONE);
		this.labelProvider = new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory());
		this.advancedLabelProvider = new OpaeumQualifiedNameLabelProvider(new OpaeumItemProviderAdapterFactory());
		factory.adapt(contentPane);
		setLayout(contentPane);
		createContents(contentPane);
		hookListeners();
	}
	protected void createContents(Composite parent){
		setLayout(parent);
		text = getWidgetFactory().createText(contentPane, "", SWT.BORDER | SWT.READ_ONLY);
		contentAssistHelper = new ChooserContentAssistHelper(text);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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
		new TextChangeListener(){
			@Override
			public void textChanged(Control control){
				setSelection(contentAssistHelper.getSelectedObject(text.getText()));
				fireSelectionChanged(new SelectionChangedEvent(OpaeumObjectChooser.this, getSelection()));
			}
		}.startListeningTo(text);
	}
	public void setEditable(boolean isEditable){
		if(text != null){
			text.setEditable(isEditable&& isSingle);
		}
	}
	public boolean isEditable(){
		return text != null && text.getEditable();
	}
	public void setText(String text){
		if(isEditable()){
			this.text.setText(text);
		}
	}
	private void handleChoose(){
		ChooseDialog dialog = new ChooseDialog(contentPane.getShell(), getObjects());
		dialog.setLabelProvider(labelProvider);
		if(advancedLabelProvider == null){
			advancedLabelProvider = new OpaeumQualifiedNameLabelProvider(new OpaeumItemProviderAdapterFactory());
		}
		dialog.setAdvancedLabelProvider(advancedLabelProvider);
		dialog.setInitialElementSelections(ss == null ? Collections.emptyList() : ss.toList());
		if(dialog.open() == Window.OK){
			Object[] selection = dialog.getResult();
			if(isSingle && selection.length > 0){
				setSelection(new StructuredSelection(selection[0]));
			}else{
				setSelection(new StructuredSelection(selection));
			}
			fireSelectionChanged(new SelectionChangedEvent(this, getSelection()));
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
	public EObject getSelectedObject(){
		return (EObject) (ss.isEmpty() ? null : ss.getFirstElement());
	}
	public IStructuredSelection getSelection(){
		return ss;
	}
	public void addTextKeyListener(KeyListener listener){
		text.addKeyListener(listener);
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
		text.setEnabled(enabled);
		chooseBt.setEnabled(enabled);
	}
	public IChoiceProvider getChoiceProvider(){
		return choiceProvider;
	}
	public void setChoiceProvider(IChoiceProvider choiceProvider){
		this.choiceProvider = choiceProvider;
		this.contentAssistHelper.setChoiceProvider(choiceProvider);
	}
	public Composite getContentPane(){
		return contentPane;
	}
	@Override
	public Control getControl(){
		return contentPane;
	}
	@Override
	public Object getInput(){
		return this.choiceProvider;
	}
	@Override
	public void refresh(){
	}
	@Override
	public void setInput(Object input){
		this.choiceProvider = ((IChoiceProvider) input);
	}
	@Override
	public void setSelection(ISelection selection,boolean reveal){
		this.ss = (IStructuredSelection) selection;
		if(isSingle && ss.size() > 1){
			ss = new StructuredSelection(ss.getFirstElement());
		}
		objects = null;
		StringBuilder buffer = concateText();
		text.setText(buffer.toString());
	}
	protected StringBuilder concateText(){
		Iterator<?> iterator = ss.iterator();
		StringBuilder buffer = new StringBuilder();
		boolean firstDone = false;
		while(iterator.hasNext()){
			Object object = iterator.next();
			if(firstDone){
				buffer.append(", ");
			}
			if(!object.toString().equals("")){
				if(isSingle){
					buffer.append(labelProvider.getText(object));
				}else if(object instanceof NamedElement){
					buffer.append(((NamedElement) object).getName());
				}
				firstDone = true;
			}
		}
		return buffer;
	}
	public void setSelection(EObject e){
		if(e == null){
			setSelection(new StructuredSelection());
		}else{
			setSelection(new StructuredSelection(e));
		}
	}
	public void setSingle(boolean b){
		this.isSingle = b;
		text.setEditable(b);
	}
	public void setLabelProvider(ILabelProvider adapterFactoryLabelProvider){
		this.labelProvider = advancedLabelProvider;
	}
}
