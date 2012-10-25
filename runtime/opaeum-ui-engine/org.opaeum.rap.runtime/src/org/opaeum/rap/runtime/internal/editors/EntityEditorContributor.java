package org.opaeum.rap.runtime.internal.editors;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;

public class EntityEditorContributor
  extends MultiPageEditorActionBarContributor
{
//	private IEditorPart activeEditorPart;
//	private Action sampleAction;

	public EntityEditorContributor() {
		super();
		createActions();
	}

//	protected IAction getAction(ITextEditor editor, String actionID) {
//		return (editor == null ? null : editor.getAction(actionID));
//	}
	/* (non-JavaDoc)
	 * Method declared in AbstractMultiPageEditorActionBarContributor.
	 */

	public void setActivePage( final IEditorPart part ) {
	  
	}
//	public void setActivePage(IEditorPart part) {
//		if (activeEditorPart == part)
//			return;
//
//		activeEditorPart = part;
//
//		IActionBars actionBars = getActionBars();
//		if (actionBars != null) {
//
//			ITextEditor editor = (part instanceof ITextEditor) ? (ITextEditor) part : null;
//
//			actionBars.setGlobalActionHandler(
//				ActionFactory.DELETE.getId(),
//				getAction(editor, ITextEditorActionConstants.DELETE));
//			actionBars.setGlobalActionHandler(
//				ActionFactory.UNDO.getId(),
//				getAction(editor, ITextEditorActionConstants.UNDO));
//			actionBars.setGlobalActionHandler(
//				ActionFactory.REDO.getId(),
//				getAction(editor, ITextEditorActionConstants.REDO));
//			actionBars.setGlobalActionHandler(
//				ActionFactory.CUT.getId(),
//				getAction(editor, ITextEditorActionConstants.CUT));
//			actionBars.setGlobalActionHandler(
//				ActionFactory.COPY.getId(),
//				getAction(editor, ITextEditorActionConstants.COPY));
//			actionBars.setGlobalActionHandler(
//				ActionFactory.PASTE.getId(),
//				getAction(editor, ITextEditorActionConstants.PASTE));
//			actionBars.setGlobalActionHandler(
//				ActionFactory.SELECT_ALL.getId(),
//				getAction(editor, ITextEditorActionConstants.SELECT_ALL));
//			actionBars.setGlobalActionHandler(
//				ActionFactory.FIND.getId(),
//				getAction(editor, ITextEditorActionConstants.FIND));
//			actionBars.setGlobalActionHandler(
//				IDEActionFactory.BOOKMARK.getId(),
//				getAction(editor, IDEActionFactory.BOOKMARK.getId()));
//			actionBars.updateActionBars();
//		}
//	}
	private void createActions() {
//		sampleAction = new Action() {
//			public void run() {
//				MessageDialog.openInformation(null, "Ui Plug-in", "Sample Action Executed");
//			}
//		};
//		sampleAction.setText("Sample Action");
//		sampleAction.setToolTipText("Sample Action tool tip");
//		sampleAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
	}
	public void contributeToMenu( final IMenuManager manager) {
//		IMenuManager menu = new MenuManager("Editor &Menu");
//		manager.prependToGroup( IWorkbenchActionConstants.MB_ADDITIONS, menu );
//		menu.add(sampleAction);
	}
	public void contributeToToolBar( final IToolBarManager manager ) {
//		manager.add(new Separator());
//		manager.add(sampleAction);
	}
}
