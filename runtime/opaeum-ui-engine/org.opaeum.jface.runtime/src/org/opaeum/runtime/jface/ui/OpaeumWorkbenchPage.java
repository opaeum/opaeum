package org.opaeum.runtime.jface.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jface.entityeditor.EntityEditorInputJface;
import org.opaeum.runtime.jface.navigator.OpaeumNavigator;
import org.opaeum.runtime.jface.navigator.PersistentObjectTreeItem;
import org.opaeum.runtime.jface.navigator.PropertyTreeItem;
import org.opaeum.runtime.rwt.Activator;
import org.opaeum.runtime.rwt.OpaeumRapSession;

public class OpaeumWorkbenchPage implements IWorkbenchPage,IWorkbenchWindow,IEditorSite{
	private static final String ATTRIBUTE_NAME = OpaeumWorkbenchPage.class.getName() + ".SESSION_ATTRIBUTE";
	OpaeumNavigator navigator;
	OpaeumEditorPane editorPane;
	List<IPartListener> workbenchPartListeners = new ArrayList<IPartListener>();
	List<ISelectionListener> selectionListeners = new ArrayList<ISelectionListener>();
	private OpaeumRapSession opaeumSession;
	public OpaeumWorkbenchPage(OpaeumRapSession s){
		this.opaeumSession = s;
		RWT.getUISession().setAttribute(ATTRIBUTE_NAME, this);
	}
	public static OpaeumWorkbenchPage getWorkbench(){
		return (OpaeumWorkbenchPage) RWT.getUISession().getAttribute(ATTRIBUTE_NAME);
	}
	public void createParts(){
		Display display = new Display();
		final Shell page = new Shell(display, SWT.NO_TRIM);
		page.setMaximized(true);
		page.setLayout(new GridLayout());
		navigator = new OpaeumNavigator(this);
		navigator.createPartControl(page);
		GridData navigatorGridData = new GridData(SWT.FILL, SWT.FILL, false, true);
		navigatorGridData.minimumWidth = 400;
		navigatorGridData.widthHint = 400;
		navigator.getTree().setLayoutData(navigatorGridData);
		navigator.getTree().addFocusListener(new FocusListener(){
			@Override
			public void focusLost(FocusEvent event){
			}
			@Override
			public void focusGained(FocusEvent event){
				firePartActivated(navigator);
			}
		});
		navigator.addSelectionChangedListener(new ISelectionChangedListener(){
			@Override
			public void selectionChanged(SelectionChangedEvent event){
				fireSelectionChanged(navigator, event.getSelection());
			}
		});
		editorPane = new OpaeumEditorPane();
		editorPane.createPane(page);
		GridData editorPaneGridData = new GridData(SWT.FILL, SWT.FILL, false, true);
		editorPaneGridData.minimumWidth = 400;
		editorPaneGridData.widthHint = 400;
		editorPane.getTabFolder().setLayoutData(editorPaneGridData);
		page.layout();
		page.open();
	}
	public void addPartListener(IPartListener opaeumWorkbenchPartListener){
		workbenchPartListeners.add(opaeumWorkbenchPartListener);
	}
	public void removePartListener(IPartListener opaeumWorkbenchPartListener){
		workbenchPartListeners.remove(opaeumWorkbenchPartListener);
	}
	public void firePartActivated(IWorkbenchPart p){
		for(IPartListener wpl:workbenchPartListeners){
			wpl.partActivated(p);
		}
	}
	public void fireSelectionChanged(IWorkbenchPart part,ISelection s){
		for(ISelectionListener wpl:selectionListeners){
			wpl.selectionChanged(part, s);
		}
	}
	public void firePartClosed(IWorkbenchPart p){
		for(IPartListener wpl:workbenchPartListeners){
			wpl.partClosed(p);
		}
	}
	@Override
	public IWorkbenchPage getActivePage(){
		return this;
	}
	public OpaeumRapSession getOpaeumSession(){
		return opaeumSession;
	}
	@Override
	public IWorkbenchPage getPage(){
		return this;
	}
	@Override
	public IWorkbenchWindow getActiveWorkbenchWindow(){
		return this;
	}
	@Override
	public void addSelectionListener(ISelectionListener entityEditor){
		selectionListeners.add(entityEditor);
	}
	public boolean openEditor(Object entity,boolean showMessa,OpaeumRapSession opaeumSession2){
		if(entity instanceof IPersistentObject){
			IPersistentObject po = (IPersistentObject) entity;
			this.editorPane.openEditor(new EntityEditorInputJface((IPersistentObject) entity, po.getName(), Activator.getDefault()
					.getImageDescriptor(Activator.IMG_PROJECT), opaeumSession2));
			return true;
		}
		return false;
	}
}
