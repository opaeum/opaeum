package org.opaeum.papyrus.uml.modelexplorer;


import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.core.ui.pagebookview.ViewPartPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.opaeum.papyrus.editor.OpaeumMultiDiagramEditor;
import org.opaeum.papyrus.editor.OpaeumTabbedPropertySheetPage;


public class OpaeumModelExplorerPageBookView extends ModelExplorerPageBookView implements IGotoMarker, ITabbedPropertySheetPageContributor {

	@Override
	protected PageRec doCreatePage(IWorkbenchPart part) {
		ViewPartPage page = new OpaeumModelExplorerPage();
		initPage(page, part);
		page.createControl(getPageBook());
		return new PageRec(part, page);
	}
	private IPropertySheetPage propertySheetPage;
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter){
		if(IPropertySheetPage.class.equals(adapter)){
			return getPropertySheetPage();
		}else{
			return super.getAdapter(adapter);
		}
	}
	private IPropertySheetPage getPropertySheetPage(){
		final IMultiDiagramEditor multiDiagramEditor = EditorUtils.getMultiDiagramEditor();
		if(multiDiagramEditor instanceof OpaeumMultiDiagramEditor){
			if(propertySheetPage == null){
				this.propertySheetPage=new OpaeumTabbedPropertySheetPage((ITabbedPropertySheetPageContributor) multiDiagramEditor);
			}
			return propertySheetPage;
		}
		return null;
	}

}
