package org.nakeduml.eclipse.starter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.uml2.uml.Model;
import org.nakeduml.eclipse.javasync.RecompileModelAction;
import org.nakeduml.eclipse.javasync.RecompileModelDirectoryAction;
import org.nakeduml.eclipse.javasync.ToggleAutomaticSynchronization;

public class DynamicOpiumMenu extends CompoundContributionItem{
	private IStructuredSelection selection;
	@Override
	protected IContributionItem[] getContributionItems(){
		this.selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		List<IContributionItem> actions = new ArrayList<IContributionItem>();
		if(selection.getFirstElement() instanceof Model){
			actions.add(new ActionContributionItem(new RecompileModelAction(selection)));
		}else if(selection.getFirstElement() instanceof IContainer){
			if(hasUmlModels(selection)){
				EditOpiumConfigAction action = new EditOpiumConfigAction(selection);
				actions.add(new ActionContributionItem(action));
				if(hasConfigFile(selection)){
					action.setText("Edit Opium Settings");
					ClearOpiumCacheACtion clc = new ClearOpiumCacheACtion(selection);
					actions.add(new ActionContributionItem(clc));
					RecompileModelDirectoryAction rmda = new RecompileModelDirectoryAction(selection);
					actions.add(new ActionContributionItem(rmda));
					ToggleAutomaticSynchronization t = new ToggleAutomaticSynchronization(selection);
					actions.add(new ActionContributionItem(t));
					RegenerateUuids ru = new RegenerateUuids(selection);
					actions.add(new ActionContributionItem(ru));
					UpdateClasspathAction uc = new UpdateClasspathAction(selection);
					actions.add(new ActionContributionItem(uc));
				}else{
					action.setText("Convert to  Opium Model Directory");
				}
			}
		}
		return (IContributionItem[]) actions.toArray(new IContributionItem[actions.size()]);
	}
	public static boolean hasUmlModels(IStructuredSelection selection2){
		IContainer firstElement = (IContainer) selection2.getFirstElement();
		try{
			if(firstElement != null){
				for(IResource r:firstElement.members()){
					if(r instanceof IFile && r.getFileExtension().equals("uml")){
						return true;
					}
				}
			}
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static boolean hasConfigFile(IStructuredSelection selection2){
		IContainer firstElement = (IContainer) selection2.getFirstElement();
		if(firstElement != null){
			return firstElement.findMember("nakeduml.properties") != null;
		}else{
			return false;
		}
	}
}
