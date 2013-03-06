package org.opaeum.eclipse.starter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageImport;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.GenerateBusinessIntelligenceSchemaAction;
import org.opaeum.eclipse.javasync.GenerateMetaModelClassesAction;
import org.opaeum.eclipse.javasync.RecompileElementAction;
import org.opaeum.eclipse.javasync.RecompileEverythingAction;
import org.opaeum.eclipse.javasync.RecompileIntegrationCodeAction;
import org.opaeum.eclipse.javasync.RecompileModelAction;
import org.opaeum.eclipse.javasync.RecompileModelDirectoryAction;
import org.opaeum.eclipse.javasync.RecompileModelLibraryAction;
import org.opaeum.eclipse.javasync.ToggleAutomaticSynchronization;
import org.opaeum.eclipse.menu.ICompoundContributionItem;
import org.opaeum.eclipse.simulation.GenerateSimulationCodeAction;
import org.opaeum.eclipse.simulation.GenerateSimulationModelAction;
import org.opaeum.eclipse.versioning.CompileVersionAction;
import org.opaeum.eclipse.versioning.GenerateMigrationProjectAction;
import org.opaeum.eclipse.versioning.VersionAction;
import org.opaeum.emf.extraction.AbstractEmfPhase;
import org.opaeum.feature.OpaeumConfig;

public class DynamicOpaeumMenu extends CompoundContributionItem implements ICompoundContributionItem{
	private IStructuredSelection selection;
	private List<IContributionItem> actions = null;
	public DynamicOpaeumMenu(IStructuredSelection selection){
		this.selection = selection;
	}
	protected Object getElementFrom(){
		Object firstElement = selection.getFirstElement();
		if(!(firstElement instanceof Element) && firstElement instanceof IAdaptable){
			return ((IAdaptable) firstElement).getAdapter(EObject.class);
		}
		return firstElement;
	}
	@Override
	public IContributionItem[] getContributionItems(){
		if(actions == null){
			actions = new ArrayList<IContributionItem>();
			Object firstElement = selection.getFirstElement();
			if(firstElement instanceof EPackage){
				if(((EPackage) firstElement).getESuperPackage()==null){
					actions.add(new ActionContributionItem(new GenerateMetaModelClassesAction(selection)));
				}
			}else if(firstElement instanceof IContainer){
				IContainer container = (IContainer) firstElement;
				if(OpaeumConfig.isValidVersionNumber(container.getName())){
					actions.add(new ActionContributionItem(new CompileVersionAction(selection)));
					actions.add(new ActionContributionItem(new GenerateMigrationProjectAction(selection)));
				}else{
					if(hasUmlModels(selection)){
						EditOpaeumConfigAction action = new EditOpaeumConfigAction(selection);
						actions.add(new ActionContributionItem(action));
						if(hasConfigFile(selection)){
							OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor((IContainer) firstElement);
							if(!ctx.isLoading()){
								action.setText("Edit Opaeum Settings");
								if(ctx.getConfig().getSourceFolderStrategy().isSingleProjectStrategy()){
									actions.add(new ActionContributionItem(new SelectOutputProjectAction(selection)));
								}
								actions.add(new ActionContributionItem(new ConfigureDatabaseAction(selection)));
								actions.add(new ActionContributionItem(new RecompileModelDirectoryAction(selection)));
								actions.add(new ActionContributionItem(new RecompileEverythingAction(selection)));
//								actions.add(new ActionContributionItem(new CreateApplicationProjectAction(selection)));
								actions.add(new ActionContributionItem(new RecompileIntegrationCodeAction(selection)));
								actions.add(new ActionContributionItem(new ToggleAutomaticSynchronization(selection)));
								// actions.add(new ActionContributionItem(new RegenerateUuids(selection)));
								// actions.add(new ActionContributionItem(new UpdateClasspathAction(selection)));
								actions.add(new ActionContributionItem(new VersionAction(selection)));
								actions.add(new ActionContributionItem(new CompileVersionAction(selection)));
								actions.add(new ActionContributionItem(new GenerateMigrationProjectAction(selection)));
								actions.add(new ActionContributionItem(new GenerateBusinessIntelligenceSchemaAction(selection)));
								actions.add(new ActionContributionItem(new GenerateSimulationModelAction(selection)));
							}
						}else{
							action.setText("Convert to  Opaeum Model Directory");
						}
					}
				}
			}else{
				firstElement = getElementFrom();
				if(firstElement != null){
					if(firstElement.getClass().getSimpleName().equals("SimulationModelImpl")){
						actions.add(new ActionContributionItem(new GenerateSimulationCodeAction(selection)));
					}else{
						if(firstElement instanceof Model){
							actions.add(new ActionContributionItem(new RecompileModelAction(selection)));
						}else if(firstElement instanceof PackageImport && ((PackageImport) firstElement).getImportedPackage() instanceof Model){
							Model m = (Model) ((PackageImport) firstElement).getImportedPackage();
							if(EmfPackageUtil.isRegeneratingLibrary(m)){
								actions.add(new ActionContributionItem(new RecompileModelLibraryAction(selection)));
							}
						}else if((firstElement instanceof Element)){
							if(AbstractEmfPhase.canBeProcessedIndividually((EObject) firstElement)){
								actions.add(new ActionContributionItem(new RecompileElementAction(selection)));
							}
						}
					}
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
