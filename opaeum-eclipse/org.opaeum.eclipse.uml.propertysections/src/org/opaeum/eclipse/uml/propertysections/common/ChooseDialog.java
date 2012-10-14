package org.opaeum.eclipse.uml.propertysections.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.uml.propertysections.Activator;

public class ChooseDialog extends SelectionDialog{
	int DEFAULT_DIALOG_WIDTH = 400;
	int DEFAULT_DIALOG_HEIGHT = 300;
	int MIN_DIALOG_WIDTH = 300;
	int MIN_DIALOG_HEIGHT = 300;
	private static final String SHOW_ADVANCED_LABEL_PROVIDER = "showAdvancedLabelProvider"; //$NON-NLS-1$
	private SearchableTree tree;
	private ILabelProvider labelProvider;
	private Object[] objects;
	private Button advancedLabelProviderCheckBox;
	private ILabelProvider advancedLabelProvider;
	private static String extension_point_id = "org.topcased.facilities.chooseDialogFilter"; //$NON-NLS-1$
	private static List<IChooseDialogFilter> filters = getFilters();
	private class TreeArrayContentProvider extends ArrayContentProvider implements ITreeContentProvider{
		public Object[] getChildren(Object parentElement){
			return new Object[0];
		}
		public Object getParent(Object element){
			return null;
		}
		public boolean hasChildren(Object element){
			return false;
		}
	}
	public ChooseDialog(Shell parentShell,Object[] objects){
		this(parentShell, objects, true);
	}
	public ChooseDialog(Shell parentShell,Object[] objects,boolean filter){
		super(parentShell);
		this.objects = objects;
		if(filter){
			filter();
		}
		setTitle("Choose");
		setMessage("Choose");
		setShellStyle(getShellStyle() | SWT.RESIZE);
	}
	protected void filter(){
		if(objects != null){
			ArrayList<Object> list = new ArrayList<Object>(objects.length);
			Collections.addAll(list, objects);
			for(Iterator<Object> i = list.iterator();i.hasNext();){
				Object o = i.next();
				for(IChooseDialogFilter f:filters){
					if(f.filter(o)){
						i.remove();
						break;
					}
				}
			}
			objects = list.toArray();
		}
	}
	private static List<IChooseDialogFilter> getFilters(){
		IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(extension_point_id);
		List<IChooseDialogFilter> filters = new ArrayList<IChooseDialogFilter>(elements.length);
		for(IConfigurationElement elt:elements){
			try{
				IChooseDialogFilter filter = (IChooseDialogFilter) elt.createExecutableExtension("filter"); //$NON-NLS-1$
				filters.add(filter);
			}catch(CoreException e){
				OpaeumEclipsePlugin.logError(e.getMessage(), e);
			}
		}
		return filters;
	}
	protected void configureShell(Shell shell){
		shell.setMinimumSize(MIN_DIALOG_WIDTH, MIN_DIALOG_HEIGHT);
		super.configureShell(shell);
	}
	protected Control createDialogArea(Composite parent){
		// Dialog
		Composite dialogComposite = (Composite) super.createDialogArea(parent);
		GridLayout dialogLayout = new GridLayout();
		dialogLayout.marginWidth = 10;
		dialogLayout.marginHeight = 10;
		GridData dialogLayoutData = new GridData(GridData.FILL_BOTH);
		dialogLayoutData.widthHint = DEFAULT_DIALOG_WIDTH;
		dialogLayoutData.heightHint = DEFAULT_DIALOG_HEIGHT;
		dialogComposite.setLayout(dialogLayout);
		dialogComposite.setLayoutData(dialogLayoutData);
		tree = new SearchableTree(dialogComposite, SWT.SINGLE);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
		tree.setContentProvider(new TreeArrayContentProvider());
		tree.setInitialSelection(new StructuredSelection(getInitialElementSelections()));
		if(getAdvancedLabelProvider() != null){
			advancedLabelProviderCheckBox = new Button(dialogComposite, SWT.CHECK);
			advancedLabelProviderCheckBox.setText("Show qualified");
			boolean showAdvanced = Activator.getDefault().getDialogSettings().getBoolean(SHOW_ADVANCED_LABEL_PROVIDER);
			advancedLabelProviderCheckBox.setSelection(showAdvanced);
			tree.setLabelProvider(showAdvanced ? getAdvancedLabelProvider() : this.labelProvider);
		}else{
			tree.setLabelProvider(this.labelProvider);
		}
		tree.setInput(this.objects);
		hookListeners();
		return dialogComposite;
	}
	/**
	 * This method had the UI listeners on the SWT widgets
	 */
	private void hookListeners(){
		tree.getTreeViewer().addOpenListener(new IOpenListener(){
			/**
			 * @see org.eclipse.jface.viewers.IOpenListener#open(org.eclipse.jface.viewers.OpenEvent)
			 */
			public void open(OpenEvent event){
				okPressed();
			}
		});
		// When an advancedLabelProvider is defined, add a listener to the checkbox changes
		if(getAdvancedLabelProvider() != null){
			this.advancedLabelProviderCheckBox.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e){
					// Update the LabelProvider to use to render domain elements
					tree.setLabelProvider(advancedLabelProviderCheckBox.getSelection() ? getAdvancedLabelProvider() : labelProvider);
					// Update tree labels by setting again input
					tree.setInput(objects);
				}
			});
		}
	}
	protected ILabelProvider getAdvancedLabelProvider(){
		return advancedLabelProvider;
	}
	public void setAdvancedLabelProvider(ILabelProvider advancedLabelProvider){
		this.advancedLabelProvider = advancedLabelProvider;
	}
	public void setLabelProvider(ILabelProvider provider){
		this.labelProvider = provider;
	}
	protected void okPressed(){
		IStructuredSelection selection = (IStructuredSelection) tree.getTreeViewer().getSelection();
		setResult(selection.toList());
		if(getAdvancedLabelProvider() != null){
			Activator.getDefault().getDialogSettings().put(SHOW_ADVANCED_LABEL_PROVIDER, advancedLabelProviderCheckBox.getSelection());
		}
		super.okPressed();
	}
}