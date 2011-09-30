package org.opeum.eclipse.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.opeum.eclipse.ModelLibrary;
import org.opeum.eclipse.NakedUmlEclipsePlugin;

public class ImportLibraryMenu extends CompoundContributionItem{
	private IStructuredSelection selection;
	@Override
	protected IContributionItem[] getContributionItems(){
		this.selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		List<IContributionItem> actions = new ArrayList<IContributionItem>();
		Set<ModelLibrary> modelLibraries = NakedUmlEclipsePlugin.getDefault().getModelLibraries();
		for(ModelLibrary uri:modelLibraries){
			actions.add(new ActionContributionItem(new ImportLibraryAction(selection, uri)));
		}
		return (IContributionItem[]) actions.toArray(new IContributionItem[actions.size()]);
	}
}
