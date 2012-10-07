package org.opaeum.propertysections;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.IActionProvider;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.opaeum.eclipse.uml.propertysections.core.NavigationDecorator;

public class OpaeumSectionActionProvider implements IActionProvider{
	public OpaeumSectionActionProvider(){
	}
	@Override
	public void setActionBars(ITabbedPropertySheetPageContributor contributor,IActionBars actionBars){
		Action action = new Action("Back"){
			@Override
			public void run(){
				NavigationDecorator.goToPreviousEObject();
			}
		};
		if(actionBars.getToolBarManager().find("asdfasdfa.back") == null){
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
}
