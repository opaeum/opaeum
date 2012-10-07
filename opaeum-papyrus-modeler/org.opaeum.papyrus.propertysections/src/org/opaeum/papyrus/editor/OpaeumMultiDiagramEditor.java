package org.opaeum.papyrus.editor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.multidiagram.actionbarcontributor.ActionBarContributorRegistry;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.opaeum.propertysections.OpaeumSectionActionProvider;

public class OpaeumMultiDiagramEditor extends PapyrusMultiDiagramEditor{
	private static final class OpaeumTabbedPropertySheet extends TabbedPropertySheetPage{
		private ITabbedPropertySheetPageContributor contributor;
		private OpaeumTabbedPropertySheet(ITabbedPropertySheetPageContributor tabbedPropertySheetPageContributor){
			super(tabbedPropertySheetPageContributor);
			this.contributor=tabbedPropertySheetPageContributor;
		}
		@Override
		public void selectionChanged(IWorkbenchPart part,ISelection selection){
			if(selection instanceof IStructuredSelection && ((IStructuredSelection) selection).getFirstElement()!=null){
				new OpaeumSectionActionProvider().setActionBars(contributor, getSite().getActionBars());
			}
			getSite().getActionBars().updateActionBars();
			super.selectionChanged(part, selection);
		}
	}
	private IPropertySheetPage tabbedPropertySheetPage;
	@Override
	protected ActionBarContributorRegistry getActionBarContributorRegistry(){
		return super.getActionBarContributorRegistry();
	}
	@Override
	public IPropertySheetPage getPropertySheetPage(){
		if(this.tabbedPropertySheetPage == null){
			this.tabbedPropertySheetPage = new OpaeumTabbedPropertySheet(this);
		}
		return tabbedPropertySheetPage;
	}
}
