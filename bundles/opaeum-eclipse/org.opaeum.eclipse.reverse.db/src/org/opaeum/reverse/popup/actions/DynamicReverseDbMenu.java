package org.opaeum.reverse.popup.actions;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.opaeum.eclipse.menu.ICompoundContributionItem;

public class DynamicReverseDbMenu extends CompoundContributionItem implements ICompoundContributionItem{
	private IStructuredSelection selection;
	@Override
	public IContributionItem[] getContributionItems(){
		if(selection.getFirstElement() instanceof JDBCSchema || selection.getFirstElement() instanceof JDBCTable){
			ReverseEngineerTablesAction a = new ReverseEngineerTablesAction(selection);
			a.setText("Create UML Entities from Tables");
			PurgeDeletedRecordsAction a2 = new PurgeDeletedRecordsAction();
			a2.setText("Purge Deleted Records ");
			return new IContributionItem[]{new ActionContributionItem(a),new ActionContributionItem(a2)};
		}else{
			return new IContributionItem[]{};
		}
	}
	public DynamicReverseDbMenu(IStructuredSelection selection){
		super();
		this.selection = selection;
	}
}
