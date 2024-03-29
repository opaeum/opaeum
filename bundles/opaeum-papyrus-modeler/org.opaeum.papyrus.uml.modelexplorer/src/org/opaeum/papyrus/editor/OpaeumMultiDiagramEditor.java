package org.opaeum.papyrus.editor;

import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.multidiagram.actionbarcontributor.ActionBarContributorRegistry;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.properties.IPropertySheetPage;

public class OpaeumMultiDiagramEditor extends PapyrusMultiDiagramEditor{
	private IPropertySheetPage tabbedPropertySheetPage;
	@Override
	protected ActionBarContributorRegistry getActionBarContributorRegistry(){
		return super.getActionBarContributorRegistry();
	}
	@Override
	public void init(IEditorSite site,IEditorInput input) throws PartInitException{
		super.init(site, input);
	}
	@Override
	public IPropertySheetPage getPropertySheetPage(){
		if(this.tabbedPropertySheetPage == null){
			this.tabbedPropertySheetPage = new OpaeumTabbedPropertySheetPage(this);
		}
		return tabbedPropertySheetPage;
	}
}
