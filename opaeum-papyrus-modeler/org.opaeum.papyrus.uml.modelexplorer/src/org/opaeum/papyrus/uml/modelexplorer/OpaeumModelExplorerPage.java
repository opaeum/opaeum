package org.opaeum.papyrus.uml.modelexplorer;

import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPage;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

public class OpaeumModelExplorerPage extends ModelExplorerPage{
	public OpaeumModelExplorerPage(){
	}
	protected IViewPart createViewer(IWorkbenchPart part){
		ModelExplorerView modelExplorerView = new ModelExplorerView((IMultiDiagramEditor) part){
			@Override
			public void createPartControl(Composite aParent){
				super.createPartControl(aParent);
				getCommonViewer().getTree().getVerticalBar().setVisible(true);
			}
		};
		return modelExplorerView;
	}
}
