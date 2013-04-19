package org.opaeum.eclipse.uml.propertysections.common;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.opaeum.eclipse.uml.propertysections.Activator;

public class SearchableTree extends Composite implements IKeepTime{
	private static final String USE_CASE_SENSITIVE = "useCaseSensitive"; //$NON-NLS-1$
	private Text searchField;
	private Button sensitiveBt;
	private boolean sensitiveCase;
	private TreeViewer treeViewer;
	private IStructuredSelection initialSelection;
	private CountTime timer;
	private String searchedText = ""; //$NON-NLS-1$
	private class SearchFilter extends ViewerFilter{
		/**
		 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public boolean select(Viewer viewer,Object parentElement,Object element){
			if(searchedText != null && searchedText.length() > 0){
				IBaseLabelProvider provider = treeViewer.getLabelProvider();
				if(provider instanceof ILabelProvider){
					String text = ((ILabelProvider) provider).getText(element);
					if(!sensitiveCase){
						text = text.toLowerCase();
						searchedText = searchedText.toLowerCase();
					}
					return text.indexOf(searchedText) != -1;
				}
			}
			return true;
		}
	}
	/**
	 * Constructor
	 * 
	 * @param parent
	 *          the parent composite
	 * @param style
	 *          the Tree Style
	 */
	public SearchableTree(Composite parent,int style){
		super(parent, SWT.NONE);
		createContents(this, style);
		hookListeners();
		timer = new CountTime(this);
	}
	/**
	 * Creates the UI
	 * 
	 * @param parent
	 *          this widget
	 * @param style
	 *          the tree style
	 */
	protected void createContents(Composite parent,int style){
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);
		createSearchComp(parent);
		createTree(parent, style);
	}
	/**
	 * Creates the UI for the search field
	 * 
	 * @param parent
	 *          the parent composite
	 */
	private void createSearchComp(Composite parent){
		Composite main = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		main.setLayout(layout);
		main.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// Create search zone
		Label searchLbl = new Label(main, SWT.NONE);
		searchLbl.setText("Search");
		this.searchField = new Text(main, SWT.BORDER);
		this.searchField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// Create case sensitive checkbox
		this.sensitiveBt = new Button(main, SWT.CHECK);
		this.sensitiveBt.setText("Case sensitive");
		this.sensitiveBt.setSelection(Activator.getDefault().getDialogSettings().getBoolean(USE_CASE_SENSITIVE));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		this.sensitiveBt.setLayoutData(gd);
	}
	private void createTree(Composite parent,int style){
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | style);
		treeViewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		treeViewer.addFilter(new SearchFilter());
	}
	protected void hookListeners(){
		this.searchField.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e){
				timer.newTime();
			}
		});
		this.sensitiveBt.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				Activator.getDefault().getDialogSettings().put(USE_CASE_SENSITIVE, SearchableTree.this.sensitiveBt.getSelection());
				refresh();
			}
		});
	}
	/**
	 * Refresh the tree and the selection
	 */
	protected void refresh(){
		if(!sensitiveBt.isDisposed() && !searchField.isDisposed()){
			sensitiveCase = sensitiveBt.getSelection();
			searchedText = searchField.getText();
			treeViewer.refresh();
			// Try to restore initial selection
			treeViewer.setSelection(initialSelection);
			// Else select the first object
			if(((IStructuredSelection) treeViewer.getSelection()).size() == 0){
				if(treeViewer.getTree().getItems().length > 0){
					TreeItem item = treeViewer.getTree().getItem(0);
					treeViewer.getTree().setSelection(new TreeItem[]{item});
				}
			}
		}
	}
	public TreeViewer getTreeViewer(){
		return treeViewer;
	}
	public void setContentProvider(ITreeContentProvider provider){
		treeViewer.setContentProvider(provider);
	}
	public void setLabelProvider(ILabelProvider provider){
		treeViewer.setLabelProvider(provider);
	}
	public void setInput(Object input){
		treeViewer.setInput(input);
	}
	public void setInitialSelection(IStructuredSelection selection){
		this.initialSelection = selection;
		treeViewer.setSelection(this.initialSelection);
	}
	public void notifyTimeEnd(){
		Display.getDefault().syncExec(new Runnable(){
			public void run(){
				refresh();
			}
		});
	}
}
