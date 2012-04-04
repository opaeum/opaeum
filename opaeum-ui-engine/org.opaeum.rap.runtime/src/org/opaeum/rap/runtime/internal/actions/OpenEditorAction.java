package org.opaeum.rap.runtime.internal.actions;

import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.SelectionProviderAction;
import org.opaeum.rap.runtime.Constants;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.editors.EntityEditorInput;
import org.opaeum.rap.runtime.internal.views.PersistentObjectTreeItem;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;

public class OpenEditorAction extends SelectionProviderAction{
	private static final long serialVersionUID = -2907313475126686117L;
	private OpaeumRapSession opaeumSession;
	public OpenEditorAction(final ISelectionProvider provider,final String text,OpaeumRapSession session){
		super(provider, text);
		this.opaeumSession = session;
	}
	public void run(){
		IStructuredSelection structuredSelection = getStructuredSelection();
		Object firstElement = structuredSelection.getFirstElement();
		openEditor(firstElement, true, opaeumSession);
	}
	public static boolean openEditor(final Object entity,final boolean showMessa,OpaeumRapSession opaeumSession){
		boolean result = false;
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage activePage = window.getActivePage();
		if(canOpen(activePage)){
			IPersistentObject po = null;
			if(entity instanceof PersistentObjectTreeItem){
				po = ((PersistentObjectTreeItem) entity).getEntity();
			}else if(entity instanceof IPersistentObject){
				po = (IPersistentObject) entity;
			}
			Image image = Activator.getDefault().getImage(Activator.IMG_PROJECT);
			IEditorInput input = new EntityEditorInput(po,
					"Edit " + IntrospectionUtil.getOriginalClass(po).getSimpleName() + ": " + po.getName(), ImageDescriptor.createFromImage(image),
					opaeumSession);
			IEditorReference[] refs = activePage.getEditorReferences();
			boolean found = false;
			for(int i = 0;!found && i < refs.length;i++){
				try{
					found = ((EntityEditorInput) refs[i].getEditorInput()).getPersistentObject().equals(po);
				}catch(PartInitException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!found){
				if(canOpen(entity, activePage, true)){
					try{
						activePage.openEditor(input, Constants.ENTITY_EDITOR_ID, true);
						result = true;
					}catch(final PartInitException pie){
						Shell shell = window.getShell();
						String title = RMSMessages.get().OpenEditorAction_ProblemOccured;
						ErrorDialog.openError(shell, title, pie.getMessage(), pie.getStatus());
					}
				}
			}
		}
		return result;
	}
	private static boolean canOpen(final Object entity,final IWorkbenchPage activePage,final boolean showMessage){
		return true;
		// IAdaptable adaptable = ( IAdaptable )entity;
		// ILock lock = ( ILock )adaptable.getAdapter( ILock.class );
		// boolean result = lock==null?true:lock.lock();
		// if( !result && showMessage ) {
		// Shell shell = activePage.getWorkbenchWindow().getShell();
		// String msg
		// = RMSMessages.get().OpenEditorAction_UsedByAnother;
		// MessageDialog.openInformation( shell,
		// RMSMessages.get().OpenEditorAction_ElementLocked,
		// msg );
		// }
		// return result;
	}
	private static boolean canOpen(final IWorkbenchPage activePage){
		boolean result = true;
		IEditorReference[] openEditors = activePage.getEditorReferences();
		if(openEditors.length >= 8){
			IWorkbenchPart editor = openEditors[0].getPart(false);
			if(editor != null){
				result = activePage.closeEditor((IEditorPart) editor, true);
			}
		}
		if(!result){
			Shell shell = activePage.getWorkbenchWindow().getShell();
			String msg = RMSMessages.get().OpenEditorAction_CloseOneEditor;
			MessageDialog.openInformation(shell, RMSMessages.get().OpenEditorAction_TooManyEditors, msg);
		}
		return result;
	}
}