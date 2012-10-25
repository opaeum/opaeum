package org.opaeum.eclipse.menu;

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

public class ImportLibraryMenu extends CompoundContributionItem{
	private IStructuredSelection selection;
	@Override
	protected IContributionItem[] getContributionItems(){
		this.selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		List<IContributionItem> actions = new ArrayList<IContributionItem>();
		Set<ModelLibrary> modelLibraries = OpaeumEclipsePlugin.getDefault().getModelLibraries();
		for(ModelLibrary uri:modelLibraries){
			actions.add(new ActionContributionItem(new ImportLibraryAction(selection, uri)));
		}
		actions.add(new ActionContributionItem(new ImportPackageFromWorkspaceAction(selection)));
		return (IContributionItem[]) actions.toArray(new IContributionItem[actions.size()]);
	}
}
