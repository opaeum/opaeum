package org.opaeum.runtime.jface.navigator;

import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.jface.actions.NewAction;
import org.opaeum.runtime.jface.actions.OpenEditorAction;
import org.opaeum.runtime.jface.ui.IPartListener;
import org.opaeum.runtime.jface.ui.IWorkbenchPart;
import org.opaeum.runtime.jface.ui.OpaeumWorkbenchPage;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.event.ChangedEntity;
import org.opaeum.runtime.rwt.Activator;
import org.opaeum.runtime.rwt.OpaeumRapSession;

public class OpaeumNavigator implements IWorkbenchPart{
	TreeViewer viewer;
	private OpaeumWorkbenchPage page;
	private static final long serialVersionUID = -6446246205102706196L;
	public OpaeumNavigator(OpaeumWorkbenchPage page){
		this.page = page;
	}
	private Action openEditor;
	private OpaeumRapSession opaeumSession;
	private NavigatorContentProvider provider;
	private class ViewLabelProvider extends LabelProvider{
		private static final long serialVersionUID = -4725556006185541567L;
		public String getText(final Object element){
			if(element instanceof IPersonNode){
				return ((IPersonNode) element).getName();
			}else if(element instanceof PersistentObjectTreeItem){
				IPersistentObject po = ((PersistentObjectTreeItem) element).getEntity();
				return "<" + IntrospectionUtil.getOriginalClass(po).getSimpleName() + ">" + po.getName();
			}else if(element instanceof PropertyTreeItem){
				return NameConverter.capitalize(NameConverter.toPlural(((PropertyTreeItem) element).getTypedElement().getName()));
			}
			return "";
		}
		public Image getImage(final Object element){
			return Activator.getDefault().getImage(Activator.IMG_PROJECT);
		}
	}
	private void hookContextMenu(){
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener(){
			public void menuAboutToShow(IMenuManager manager){
				fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
	}
	private void fillContextMenu(final IMenuManager manager){
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if(selection != null){
			if(selection.getFirstElement() instanceof PersistentObjectTreeItem){
				manager.add(openEditor);
				manager.add(new Separator());
				PersistentObjectTreeItem item = (PersistentObjectTreeItem) selection.getFirstElement();
				Object[] children = item.getChildren();
				for(Object child:children){
					if(child instanceof PropertyTreeItem){
						PropertyTreeItem propertyItem = (PropertyTreeItem) child;
						if(!Modifier.isAbstract(propertyItem.getTypedElement().getBaseType().getModifiers())){
							manager.add(new NewAction(item.getEntity(), propertyItem.getTypedElement(), null, this.opaeumSession));
						}
					}
				}
			}
		}
		manager.add(new Separator());
	}
	private void makeActions(){
		openEditor = new OpenEditorAction(viewer, "Open", opaeumSession);
	}
	private void hookDoubleClickAction(){
		viewer.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent event){
				openEditor.run();
			}
		});
	}
	public boolean setFocus(){
		return viewer.getControl().setFocus();
	}
	@Override
	public void createPartControl(Composite c){
		viewer = new TreeViewer(c, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		this.provider = new NavigatorContentProvider(opaeumSession);
		viewer.setContentProvider(provider);
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(opaeumSession.getPersonNode());
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		page.addPartListener(new IPartListener(){
			@Override
			public void partActivated(final IWorkbenchPart part){
				if(part == viewer){
					for(IPersistentObject po:opaeumSession.getPersistence().reloadStaleObjects()){
						PersistentObjectTreeItem ti = provider.getTreeItemFor(po);
						if(ti != null){
							viewer.refresh(ti);
						}
					}
				}else{
					IPersistentObject entity = (IPersistentObject) part.getAdapter(IPersistentObject.class);
					if(entity != null){
						PersistentObjectTreeItem ti = provider.getTreeItemFor(entity);
						if(ti == null){
							// Newly created or not expanded yet
							CompositionNode cn = (CompositionNode) entity;
							ti = provider.getTreeItemFor((IPersistentObject) cn.getOwningObject());
							if(ti != null){
								// Newly created
								opaeumSession.getPersistence().refresh(ti.getEntity());// NB!! Get entity from THIS session
								viewer.refresh(ti);
							}
						}else{
							opaeumSession.getPersistence().refresh(ti.getEntity());// NB!! Get entity from THIS session
							ISelection selection = new StructuredSelection(ti);
							viewer.setSelection(selection, true);
						}
					}
				}
			}
			@Override
			public void partClosed(final IWorkbenchPart part){
				if(part != viewer){
					IPersistentObject entity = (IPersistentObject) part.getAdapter(IPersistentObject.class);
					if(entity != null){
						PersistentObjectTreeItem ti = provider.getTreeItemFor(entity);
						if(ti != null){
							// TODO should we not get it from the editorInput?
							if(((HibernateEntity) ti.getEntity()).getDeletedOn().before(new Date(System.currentTimeMillis() + 1))){
								CompositionNode owningObject = ((CompositionNode) ti.getEntity()).getOwningObject();
								opaeumSession.getPersistence().refresh((IPersistentObject) owningObject);// NB!! Get entity from THIS session
								viewer.refresh(ti.getParent());
							}else{
								opaeumSession.getPersistence().refresh(ti.getEntity());// NB!! Get entity from THIS session
								viewer.refresh(ti);
							}
						}
					}
				}
			}
		});
		viewer.expandToLevel(2);
	}
	public Control getTree(){
		return viewer.getTree();
	}
	public void addSelectionChangedListener(ISelectionChangedListener listener){
		viewer.addSelectionChangedListener(listener);
	}
	public void removeSelectionChangedListener(ISelectionChangedListener listener){
		viewer.removeSelectionChangedListener(listener);
	}
	@Override
	public String getPartName(){
		return "Object Navigator";
	}
	public IStructuredSelection getSelection(){
		return (IStructuredSelection) viewer.getSelection();
	}
	@Override
	public Object getAdapter(Class<?> class1){
		if(class1 == IPersistentObject.class){
			if(getSelection().isEmpty()){
				return null;
			}else{
				Object o = getSelection().getFirstElement();
				IPersistentObject po = null;
				if(o instanceof PropertyTreeItem){
					po = ((PropertyTreeItem) o).getOwner();
				}else if(o instanceof PersistentObjectTreeItem){
					po = ((PersistentObjectTreeItem) o).getEntity();
				}
				return po;
			}
		}
		return null;
	}
	@Override
	public Control getPartControl(){
		return viewer.getControl();
	}
}
