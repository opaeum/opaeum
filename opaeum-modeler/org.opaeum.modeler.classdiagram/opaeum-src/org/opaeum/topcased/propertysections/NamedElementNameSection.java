package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.opaeum.topcased.uml.editor.OpaeumEditor;

public class NamedElementNameSection extends org.topcased.modeler.uml.internal.properties.sections.NamedElementNameSection{
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		EObject eObject = getEObject();
		super.setInput(part, selection);
		if(eObject != getEObject()){
			OpaeumEditor oe = (OpaeumEditor) getActivePage().getActiveEditor();
			oe.pushSelection(getEObject());
		}
	}
	@Override
	public void makeContributions(IMenuManager menuManager,IToolBarManager toolBarManager,IStatusLineManager statLineManager){
		toolBarManager.removeAll();
		if(toolBarManager.find("asdfasdfa.back") == null){
			final IWorkbenchPage activePage = getActivePage();
			Action action = new Action("Back"){
				@Override
				public void run(){
					NavigationDecorator.goToPreviousEObject(activePage);
				}
			};
			ISharedImages images = PlatformUI.getWorkbench().getSharedImages();
			action.setImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_BACK));
			action.setDisabledImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_BACK_DISABLED));
			ActionContributionItem item = new ActionContributionItem(action);
			item.setVisible(true);
			item.setId("asdfasdfa.back");
			toolBarManager.add(item);
			toolBarManager.update(false);
		}
		super.makeContributions(menuManager, toolBarManager, statLineManager);
	}
}
