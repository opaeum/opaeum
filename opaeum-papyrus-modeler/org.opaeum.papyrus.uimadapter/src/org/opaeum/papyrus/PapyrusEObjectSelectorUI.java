package org.opaeum.papyrus;

import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.navigator.CommonViewer;
import org.opaeum.eclipse.context.EObjectSelectorUI;

import com.google.common.collect.Lists;

public class PapyrusEObjectSelectorUI implements EObjectSelectorUI{
	private IWorkbenchWindow window;
	Stack<EObject> selection = new Stack<EObject>();
	public PapyrusEObjectSelectorUI(IWorkbenchWindow workbenchWindow){
		this.window = workbenchWindow;
	}
	public void gotoEObject(EObject key){
		for(IViewReference vr:window.getActivePage().getViewReferences()){
			IViewPart view = vr.getView(true);
			if(view instanceof ModelExplorerPageBookView){
				ModelExplorerPageBookView me = (ModelExplorerPageBookView) view;
				IViewPart viewPart = me.getActiveView();
				if(viewPart instanceof ModelExplorerView){
					ModelExplorerView modelExplorerView = (ModelExplorerView) viewPart;
					CommonViewer treeViewer = modelExplorerView.getCommonViewer();
					// The common viewer is in fact a tree viewer
					ModelExplorerView.reveal(Lists.newArrayList(key), treeViewer);
					// Object modelElementItem = me.findElementForEObject(treeViewer, key);
					// if(modelElementItem != null){
					// TreePath treePath = new TreePath(new Object[]{modelElementItem});
					// EObject parent = key.eContainer();
					// if(parent != null){
					// // workaround: in case of a pseudo parent (like "ownedConnector", the expansion
					// // is not made automatically
					// Object parentElement = me.findElementForEObject(treeViewer, parent);
					// treeViewer.expandToLevel(parentElement, 1);
					// }
					// modelExplorerView.revealSemanticElement(Arrays.asList(key));
					// treeViewer.setSelection(new StructuredSelection(modelElementItem), true);
					// // modelExplorerView.selectReveal(new TreeSelection(treePath));
					// }
				}
			}
		}
	}
	public void pushSelection(EObject eObject){
		if(selection.isEmpty() || selection.peek() != eObject){
			selection.push(eObject);
		}
	}
	public EObject popSelection(){
		return selection.pop();
	}
}