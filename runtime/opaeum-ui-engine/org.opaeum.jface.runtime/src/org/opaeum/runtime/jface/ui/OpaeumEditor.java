package org.opaeum.runtime.jface.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jface.entityeditor.EntityEditorInputJface;

public abstract class OpaeumEditor implements IEditorPart{
	private String partName;
	private Image titleImage;
	private List<IFormPage> pages = new ArrayList<IFormPage>();
	private List<CTabItem> items = new ArrayList<CTabItem>();
	private List<Composite> pageComposites=new ArrayList<Composite>();
	private EntityEditorInputJface editorInput;
	private IEditorSite worbenchWindow;
	private CTabFolder pageFolder;
	private Composite body;
	private Composite header;
	protected MessageTable messageTable;
	private Composite buttonBar;
	private CLabel titleComposite;
	private Composite container;
	public void init(IEditorSite site,IEditorInput input){
		this.editorInput = (EntityEditorInputJface) input;
		this.worbenchWindow = site;
	}
	protected abstract void addPages();
	public void addPage(IFormPage page){
		pages.add(page);
		CTabItem newItem = new CTabItem(pageFolder, SWT.NONE);
		Composite pageComposite = new Composite(pageFolder, SWT.NONE);
		pageComposite.setLayout(new FillLayout());
		pageComposites.add(pageComposite);
		newItem.setControl(pageComposite);
		newItem.setText(page.getPartName());
		items.add(newItem);
		page.init(editorInput);

	}
	@Override
	public Control getPartControl(){
		return body;
	}
	public void createPartControl(Composite parent){
		this.container = new Composite(parent,SWT.NONE);
		this.container.setLayout(new GridLayout(1, true));
		header = new Composite(container, SWT.BORDER);
  	GridData headerData = new GridData(SWT.FILL, SWT.TOP, true, false);
		headerData.heightHint = 200;
		header.setLayoutData(headerData);
		header.setLayout(new GridLayout());
		this.body = new Composite(container, SWT.BORDER);
		body.setLayout(new FillLayout());
		body.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		titleComposite=new CLabel(header, SWT.BORDER);
		titleComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true));
		messageTable=new MessageTable(header, SWT.NONE);
		buttonBar = new Composite(header, SWT.BORDER);
		createButtonBarContents(this.buttonBar);
		pageFolder = new CTabFolder(body, SWT.BORDER);
		pageFolder.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				int index=items.indexOf(e.item);
				IFormPage p = pages.get(index);
				if(p.getPartControl()==null){
					Composite pageComposite = pageComposites.get(index);
					p.createPartControl(pageComposite);
					pageComposite.layout();
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		addPages();
		pageFolder.setSelection(0);
		pageFolder.setFocus();
	}
	public void createButtonBarContents(Composite buttonBar2){
		
	}
	public void refresh(){
		// TODO check ifthis creates problems with all the JFace Binding stuff
		Composite parent = container.getParent();
		container.dispose();
		createPartControl(parent);
	}
	public String getPartName(){
		return partName;
	}
	public void setPartName(String partName){
		this.partName = partName;
	}
	public Image getTitleImage(){
		return titleImage;
	}
	public void setTitleImage(Image titleImage){
		this.titleImage = titleImage;
	}
	public List<IFormPage> getPages(){
		return pages;
	}
	public EntityEditorInputJface getEditorInput(){
		return editorInput;
	}
	public OpaeumWorkbenchPage getWorbenchWindow(){
		return (OpaeumWorkbenchPage) worbenchWindow;
	}
	public IEditorSite getSite(){
		return worbenchWindow;
	}
	public void dispose(){
	}
	@Override
	@SuppressWarnings("rawtypes")
	public Object getAdapter(final Class adapter){
		Object result = null;
		if(adapter == IPersistentObject.class){
			result = getEditorInput().getPersistentObject();
		}
		return result;
	}
}
