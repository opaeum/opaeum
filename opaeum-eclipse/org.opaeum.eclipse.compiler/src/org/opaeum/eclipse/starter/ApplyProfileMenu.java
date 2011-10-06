package org.opaeum.eclipse.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.opaeum.eclipse.ModelLibrary;
import org.opaeum.eclipse.OpaeumEclipsePlugin;

public class ApplyProfileMenu extends CompoundContributionItem{
	private IStructuredSelection selection;
	@Override
	protected IContributionItem[] getContributionItems(){
		this.selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		List<IContributionItem> actions = new ArrayList<IContributionItem>();
		Set<ModelLibrary> profiles = OpaeumEclipsePlugin.getDefault().getProfiles();
		for(ModelLibrary uri:profiles){
			actions.add(new ActionContributionItem(new ApplyProfileAction(selection, uri)));
		}
		return (IContributionItem[]) actions.toArray(new IContributionItem[actions.size()]);
	}
}
