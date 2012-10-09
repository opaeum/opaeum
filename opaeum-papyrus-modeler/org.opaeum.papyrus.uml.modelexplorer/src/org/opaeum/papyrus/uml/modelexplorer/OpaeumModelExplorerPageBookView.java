package org.opaeum.papyrus.uml.modelexplorer;


import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.core.ui.pagebookview.ViewPartPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;


public class OpaeumModelExplorerPageBookView extends ModelExplorerPageBookView implements IGotoMarker, ITabbedPropertySheetPageContributor {

	@Override
	protected PageRec doCreatePage(IWorkbenchPart part) {
		ViewPartPage page = new OpaeumModelExplorerPage();
		initPage(page, part);
		page.createControl(getPageBook());
		return new PageRec(part, page);
	}
}
