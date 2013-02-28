package org.opaeum.runtime.jface.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.deferred.SetModel;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jface.entityeditor.EntityEditorInputJface;
import org.opaeum.runtime.jface.navigator.OpaeumNavigator;
import org.opaeum.runtime.rwt.Activator;
import org.opaeum.runtime.rwt.OpaeumRapSession;

public class OpaeumWorkbenchPage extends ApplicationWindow implements IWorkbenchPage,IWorkbenchWindow,IEditorSite{
	private static final String ATTRIBUTE_NAME = OpaeumWorkbenchPage.class.getName() + ".SESSION_ATTRIBUTE";
	OpaeumNavigator navigator;
	OpaeumEditorPane editorPane;
	List<IPartListener> workbenchPartListeners = new ArrayList<IPartListener>();
	List<ISelectionListener> selectionListeners = new ArrayList<ISelectionListener>();
	private OpaeumRapSession opaeumSession;
	private SashForm topLevelForm;
	public OpaeumWorkbenchPage(Shell shell,OpaeumRapSession s){
		super(shell);
		shell.setLayout(new FillLayout());
		this.opaeumSession = s;
		RWT.getUISession().setAttribute(ATTRIBUTE_NAME, this);
		addMenuBar();
		addStatusLine();
	}
	public static OpaeumWorkbenchPage getWorkbench(){
		return (OpaeumWorkbenchPage) RWT.getUISession().getAttribute(ATTRIBUTE_NAME);
	}
	@Override
	protected MenuManager createMenuManager(){
		MenuManager mm = super.createMenuManager();
		MenuManager file = new MenuManager("File");
		mm.add(file);
		file.add(new Action("Exit"){
			@Override
			public void run(){
				getShell().close();
			}
		});
		return mm;
	}
	@Override
	protected Control createContents(Composite parent){
		topLevelForm = new SashForm(parent, SWT.HORIZONTAL);
		navigator = new OpaeumNavigator(this);
		navigator.createPartControl(topLevelForm);
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
		editorPane = new OpaeumEditorPane(this);
		editorPane.createPane(topLevelForm);
		topLevelForm.setWeights(new int[]{1,3});

		return topLevelForm;
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
		if(entity instanceof IAdaptable && ((IAdaptable) entity).getAdapter(IPersistentObject.class) != null){
			IPersistentObject po = (IPersistentObject) ((IAdaptable) entity).getAdapter(IPersistentObject.class);
			this.editorPane.openEditor(new EntityEditorInputJface((IPersistentObject) po, po.getName(), Activator.getDefault()
					.getImageDescriptor(Activator.IMG_PROJECT), opaeumSession2));
			return true;
		}
		return false;
	}
}
