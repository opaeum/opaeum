package org.opaeum.rap.runtime.widgets;

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
import org.opaeum.uim.swt.SearchableTree;

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
	/**
	 * Wrapper to adapt the ArrayContentProvider to a TreeViewer
	 * 
	 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
	 */
	private class TreeArrayContentProvider extends ArrayContentProvider implements ITreeContentProvider{
		/**
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object parentElement){
			return new Object[0];
		}
		/**
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
		 */
		public Object getParent(Object element){
			return null;
		}
		/**
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object element){
			return false;
		}
	}
	public ChooseDialog(Shell parentShell,Object[] objects){
		super(parentShell);
		this.objects = objects;
		setTitle("Choose Object");
		setMessage("Choose...");
		setShellStyle(getShellStyle() | SWT.RESIZE);
	}
	protected void configureShell(Shell shell){
		shell.setMinimumSize(500, 500);
		super.configureShell(shell);
	}
	/**
	 * Create the Dialog area
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
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
		// Initialize LabelProvider to be used to render Tree contents
		if(getAdvancedLabelProvider() != null){
			advancedLabelProviderCheckBox = new Button(dialogComposite, SWT.CHECK);
			advancedLabelProviderCheckBox.setText("Detailed Names");
			advancedLabelProviderCheckBox.setSelection(false);
			tree.setLabelProvider(this.labelProvider);
		}else{
			tree.setLabelProvider(this.labelProvider);
		}
		tree.setInput(this.objects);
		hookListeners();
		return dialogComposite;
	}
	private void hookListeners(){
		tree.getTreeViewer().addOpenListener(new IOpenListener(){
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
	/**
	 * Get the Advanced LabelProvider to use to display the Object
	 * 
	 * @return ILabelProvider
	 */
	protected ILabelProvider getAdvancedLabelProvider(){
		return advancedLabelProvider;
	}
	/**
	 * Set the Advanced LabelProvider to use to display the Object
	 * 
	 * @param advancedLabelProvider
	 *          ILabelProvider
	 */
	public void setAdvancedLabelProvider(ILabelProvider advancedLabelProvider){
		this.advancedLabelProvider = advancedLabelProvider;
	}
	/**
	 * Set the provider that displays the objects
	 * 
	 * @param provider
	 *          the LabelProvider
	 */
	public void setLabelProvider(ILabelProvider provider){
		this.labelProvider = provider;
	}
	/**
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed(){
		IStructuredSelection selection = (IStructuredSelection) tree.getTreeViewer().getSelection();
		setResult(selection.toList());
		super.okPressed();
	}
}