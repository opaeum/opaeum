package org.opaeum.eclipse.uml.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.IActionProvider;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.uml.propertysections.core.NavigationDecorator;

public class OpaeumSectionActionProvider implements IActionProvider{
	private EObject currentSelection;
	public OpaeumSectionActionProvider(){
	}
	@Override
	public void setActionBars(ITabbedPropertySheetPageContributor contributor,IActionBars actionBars){
		if(actionBars.getToolBarManager().find("asdfasdfa.back") == null){
			Action action = new Action("Back"){
				@Override
				public void run(){
					OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(currentSelection);
					EObject current = ouf.geteObjectSelectorUI().popSelection();
					EObject previous = ouf.geteObjectSelectorUI().popSelection();
					ouf.geteObjectSelectorUI().gotoEObject(previous);
					NavigationDecorator.selectEObjectInAllViews(previous);
				}
			};
			ISharedImages images = PlatformUI.getWorkbench().getSharedImages();
			action.setImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_BACK));
			action.setDisabledImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_BACK_DISABLED));
			ActionContributionItem item = new ActionContributionItem(action);
			item.setVisible(true);
			item.setId("asdfasdfa.back");
			actionBars.getToolBarManager().add(item);
			actionBars.getToolBarManager().markDirty();
		}
	}
	public void setCurrentSelection(EObject eObject){
		currentSelection = eObject;
	}
}
