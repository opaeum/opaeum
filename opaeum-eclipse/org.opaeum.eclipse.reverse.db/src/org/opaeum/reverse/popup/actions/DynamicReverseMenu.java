package org.opaeum.reverse.popup.actions;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;

public class DynamicReverseMenu extends CompoundContributionItem{
	private IStructuredSelection selection;
	@Override
	protected IContributionItem[] getContributionItems(){
		this.selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		ReverseEngineerTablesAction a = new ReverseEngineerTablesAction();
		a.setText("Create UML Entities from Tables");
		PurgeDeletedRecordsAction a2 = new PurgeDeletedRecordsAction();
		a2.setText("Purge Deleted Records ");
		return new IContributionItem[]{
			new ActionContributionItem(a),new ActionContributionItem(a2)
		};
	}
}
