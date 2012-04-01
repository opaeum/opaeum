package org.opaeum.rap.runtime.internal.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.rap.rms.data.IDataModel;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.IServiceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.opaeum.name.NameConverter;
import org.opaeum.rap.runtime.Constants;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.actions.OpenEditorAction;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.organization.IPersonNode;

public class Navigator extends ViewPart {

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private Action openEditor;
	private OpaeumRapSession opaeumSession;

	private class ViewLabelProvider extends LabelProvider {
		public String getText(final Object element) {
			if(element instanceof IPersonNode){
				return ((IPersonNode) element).getName();
			}else if(element instanceof PersistentObjectTreeItem){
				IPersistentObject po = ((PersistentObjectTreeItem) element).getEntity();
				return "<" + IntrospectionUtil.getOriginalClass(po).getSimpleName()+">" + po.getName();
			}else if(element instanceof PropertyTreeItem){
				return NameConverter.capitalize(NameConverter.toPlural(((PropertyTreeItem) element).getDescriptor().getName()));
			}
			return "";
		}

		public Image getImage(final Object element) {
			return EntityAdapter.getImage(element);
		}
	}

	public void createPartControl(final Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		this.opaeumSession = (OpaeumRapSession)RWT.getRequest().getSession(true).getAttribute("opaeumSession");
		viewer.setContentProvider(new NavigatorContentProvider(opaeumSession));
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(opaeumSession.getPersonNode());
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();

		getSite().getPage().addPartListener(new IPartListener() {
			public void partActivated(final IWorkbenchPart part) {
				if (part != Navigator.this) {
					IPersistentObject entity = (IPersistentObject) part.getAdapter(IPersistentObject.class);
					if (entity != null) {
						ISelection selection = new StructuredSelection(entity);
						viewer.setSelection(selection, true);
					}
				}
			}

			public void partBroughtToTop(final IWorkbenchPart part) {
			}

			public void partClosed(final IWorkbenchPart part) {
			}

			public void partDeactivated(final IWorkbenchPart part) {
			}

			public void partOpened(final IWorkbenchPart part) {
			}
		});
		viewer.expandToLevel(2);
		getSite().setSelectionProvider(viewer);

		IServiceStore serviceStore = RWT.getServiceStore();
		ISelection selection = (ISelection) serviceStore
				.getAttribute(Constants.PRE_SELECTION);
		if (selection != null) {
			viewer.setSelection(selection, true);
		}
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				Navigator.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillContextMenu(final IMenuManager manager) {
		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		if (selection != null) {
			EntityAdapter.createNewMenuItem(selection.getFirstElement(),
					manager);
		}
		manager.add(new Separator());
		if (!(selection.getFirstElement() instanceof IDataModel)) {
			manager.add(openEditor);
			manager.add(new Separator());
		}
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		openEditor = new OpenEditorAction(viewer,
				RMSMessages.get().Navigator_Open,opaeumSession);
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				openEditor.run();
			}
		});
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}