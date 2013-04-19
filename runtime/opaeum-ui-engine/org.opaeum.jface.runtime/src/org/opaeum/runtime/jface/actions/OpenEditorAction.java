package org.opaeum.runtime.jface.actions;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.runtime.jface.ui.OpaeumWorkbenchPage;
import org.opaeum.runtime.rwt.OpaeumRapSession;

public class OpenEditorAction extends SelectionAction {
	private static final long serialVersionUID = -2907313475126686117L;
	private OpaeumRapSession opaeumSession;
	public OpenEditorAction(final ISelectionProvider provider,final String text,OpaeumRapSession session){
		super(text);
		provider.addSelectionChangedListener(this);
		this.opaeumSession = session;
	}
	public void run(){
		IStructuredSelection structuredSelection = getStructuredSelection();
		Object firstElement = structuredSelection.getFirstElement();
		openEntityEditor(firstElement, true, opaeumSession);
	}
	public static boolean openEntityEditor(final Object entity,final boolean showMessa,OpaeumRapSession opaeumSession){
		return OpaeumWorkbenchPage.getWorkbench().openEditor(entity,showMessa,opaeumSession);
	}
	public static boolean openCubeEditor(final Object entity,final boolean showMessa,OpaeumRapSession opaeumSession){
		return false;
	}

}