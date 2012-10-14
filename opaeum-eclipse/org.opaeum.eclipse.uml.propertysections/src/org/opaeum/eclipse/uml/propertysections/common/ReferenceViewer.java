package org.opaeum.eclipse.uml.propertysections.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.opaeum.eclipse.ImageManager;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumFeatureDialog.IFeatureInfo;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class ReferenceViewer extends Viewer{
	private Button selectButton;
	private final TabbedPropertySheetWidgetFactory widgetFactory;
	private TableViewer tableViewer;
	private ILabelProvider labelProvider;
	private Composite contentPane;
	private IChoiceProvider choiceProvider;
	private IFeatureInfo input;
	private IStructuredSelection selection;
	private Button removeButton;
	public ReferenceViewer(Composite parent,String[] columnNames,TabbedPropertySheetWidgetFactory widgetFactory,IChoiceProvider choiceProvider){
		super();
		this.widgetFactory = widgetFactory;
		this.selection = new StructuredSelection();
		this.contentPane = widgetFactory.createComposite(parent);
		this.tableViewer = new TableViewer(widgetFactory.createTable(contentPane, getTableStyle()));
		this.choiceProvider = choiceProvider;
		this.contentPane.setLayout(new GridLayout(2, false));
		this.createButtons(contentPane);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		this.tableViewer.getTable().setLayoutData(gridData);
		this.tableViewer.getTable().setLinesVisible(true);
		this.tableViewer.getTable().setHeaderVisible(true);
		createColumns(columnNames);
		this.tableViewer.setUseHashlookup(true);
		this.tableViewer.setColumnProperties(columnNames);
		this.tableViewer.setContentProvider(new ArrayContentProvider());
		this.labelProvider = new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory());
	}
	protected int getTableStyle(){
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;
		return style;
	}
	private void createColumns(final String[] colNames){
		for(int i = 0;i < colNames.length;i++){
			TableViewerColumn tvc = new TableViewerColumn(tableViewer, SWT.LEFT, i);
			final String name = colNames[i];
			tvc.setLabelProvider(new CellLabelProvider(){
				@Override
				public void update(ViewerCell cell){
					EObject e = (EObject) cell.getElement();
					EStructuralFeature f = e.eClass().getEStructuralFeature(name);
					if(f != null){
						cell.setText("" + e.eGet(f));
					}
				}
			});
			TableColumn column = tvc.getColumn();
			column.setText(colNames[i]);
			column.setWidth(100);
		}
	}
	public TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return widgetFactory;
	}
	private void createButtons(Composite parent){
		final Composite comp = getWidgetFactory().createComposite(parent);
		comp.setLayout(new GridLayout());
		selectButton = getWidgetFactory().createButton(comp, "Select...", SWT.NONE);
		selectButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		selectButton.setImage(ImageManager.IMG_ADD);
		removeButton = getWidgetFactory().createButton(comp, "Remove", SWT.NONE);
		removeButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		removeButton.setImage(ImageManager.IMG_DELETE);
		removeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				IStructuredSelection tableSelection = (IStructuredSelection) tableViewer.getSelection();
				List remaining = new ArrayList(selection.toList());
				remaining.removeAll((List<?>) tableSelection.toList());
				setSelection(new StructuredSelection(remaining));
				fireSelectionChanged(new SelectionChangedEvent(ReferenceViewer.this, getSelection()));
			}
		});
		hookButtonListeners();
	}
	protected void hookButtonListeners(){
		selectButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				List<?> o = chooseObjectsFromDialog();
				if(o != null){
					StructuredSelection ss = new StructuredSelection(o);
					setSelection(ss);
					fireSelectionChanged(new SelectionChangedEvent(ReferenceViewer.this, ss));
				}
			}
		});
	}
	private List<?> chooseObjectsFromDialog(){
		Shell shell = Display.getDefault().getActiveShell();
		String displayName = "Choose the objects to add";
		FeatureEditorDialog dialog = new OpaeumFeatureDialog(shell, labelProvider, (IFeatureInfo) getInput(), selection, displayName, choiceProvider);
		dialog.open();
		return dialog.getResult();
	}
	public void setLayoutData(FormData data){
		contentPane.setLayoutData(data);
	}
	public void setEnabled(boolean enabled){
		tableViewer.getTable().setEnabled(enabled);
		selectButton.setEnabled(enabled);
	}
	@Override
	public Control getControl(){
		return contentPane;
	}
	@Override
	public IFeatureInfo getInput(){
		return input;
	}
	@Override
	public IStructuredSelection getSelection(){
		return this.selection;
	}
	@Override
	public void refresh(){
		setSelection(new StructuredSelection(this.input.getCurrentValues()));
	}
	@Override
	public void setInput(Object input){
		this.input = (IFeatureInfo) input;
		tableViewer.setInput(this.input.getCurrentValues());
	}
	@Override
	public void setSelection(ISelection selection,boolean reveal){
		this.selection = (IStructuredSelection) selection;
		tableViewer.setInput(this.selection.toList());
	}
	public Table getTable(){
		return tableViewer.getTable();
	}
	public void setLabelProvider(ILabelProvider labelProvider2){
		this.labelProvider = labelProvider2;
	}
}
