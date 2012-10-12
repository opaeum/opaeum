package org.opaeum.papyrus.uml.modelexplorer;

import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPage;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

public class OpaeumModelExplorerPage extends ModelExplorerPage{
	public OpaeumModelExplorerPage(){
	}
	protected IViewPart createViewer(IWorkbenchPart part){
		return new ModelExplorerView((IMultiDiagramEditor) part);
	}
}
