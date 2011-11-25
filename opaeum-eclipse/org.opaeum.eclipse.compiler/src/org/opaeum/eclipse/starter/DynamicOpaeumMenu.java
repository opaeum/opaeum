package org.opaeum.eclipse.starter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opaeum.eclipse.javasync.RecompileElementAction;
import org.opaeum.eclipse.javasync.RecompileIntegrationCodeAction;
import org.opaeum.eclipse.javasync.RecompileModelAction;
import org.opaeum.eclipse.javasync.RecompileModelDirectoryAction;
import org.opaeum.eclipse.javasync.ToggleAutomaticSynchronization;
import org.opaeum.eclipse.versioning.CompileVersionAction;
import org.opaeum.eclipse.versioning.GenerateMigrationProjectAction;
import org.opaeum.eclipse.versioning.VersionAction;
import org.opaeum.emf.extraction.EmfExtractionPhase;
import org.opaeum.feature.OpaeumConfig;

public class DynamicOpaeumMenu extends CompoundContributionItem{
	private IStructuredSelection selection;
	@Override
	protected IContributionItem[] getContributionItems(){
		this.selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		List<IContributionItem> actions = new ArrayList<IContributionItem>();
		if(selection.getFirstElement() instanceof Model && JavaTransformationProcessManager.getCurrentTransformationProcess() != null){
			actions.add(new ActionContributionItem(new RecompileModelAction(selection)));
		}else if(selection.getFirstElement() instanceof IContainer){
			IContainer firstElement = (IContainer) selection.getFirstElement();
			if(OpaeumConfig.isValidVersionNumber(firstElement.getName())){
				actions.add(new ActionContributionItem(new CompileVersionAction(selection)));
				actions.add(new ActionContributionItem(new GenerateMigrationProjectAction(selection)));
			}else{
				if(hasUmlModels(selection)){
					EditOpaeumConfigAction action = new EditOpaeumConfigAction(selection);
					actions.add(new ActionContributionItem(action));
					if(hasConfigFile(selection)){
						if(!OpaeumEclipseContext.findOrCreateContextFor((IContainer) selection.getFirstElement()).isLoading()){
							action.setText("Edit Opaeum Settings");
							actions.add(new ActionContributionItem(new ClearOpaeumCacheACtion(selection)));
							actions.add(new ActionContributionItem(new RecompileModelDirectoryAction(selection)));
							actions.add(new ActionContributionItem(new RecompileIntegrationCodeAction(selection)));
							actions.add(new ActionContributionItem(new ToggleAutomaticSynchronization(selection)));
							actions.add(new ActionContributionItem(new RegenerateUuids(selection)));
							actions.add(new ActionContributionItem(new UpdateClasspathAction(selection)));
							actions.add(new ActionContributionItem(new VersionAction(selection)));
							actions.add(new ActionContributionItem(new CompileVersionAction(selection)));
							actions.add(new ActionContributionItem(new GenerateMigrationProjectAction(selection)));
						}
					}else{
						action.setText("Convert to  Opaeum Model Directory");
					}
				}
			}
		}else if((selection.getFirstElement() instanceof Element)&& JavaTransformationProcessManager.getCurrentTransformationProcess() != null){
			
			
			if(EmfExtractionPhase.canBeProcessedIndividually((EObject) selection.getFirstElement())){
				actions.add(new ActionContributionItem(new RecompileElementAction(selection)));
			}
		}else if(selection.getFirstElement() instanceof AbstractGraphicalEditPart && JavaTransformationProcessManager.getCurrentTransformationProcess() != null){
			//TODO we never get here
			AbstractGraphicalEditPart a = (AbstractGraphicalEditPart) selection.getFirstElement();
			System.out.println(a.getModel());
			if(a.getModel() instanceof Element && EmfExtractionPhase.canBeProcessedIndividually((EObject) a.getModel())){
				actions.add(new ActionContributionItem(new RecompileElementAction(selection)));
			}
		}
		System.out.println(selection.getFirstElement());
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
			return firstElement.findMember("opaeum.properties") != null;
		}else{
			return false;
		}
	}
}
