package org.opaeum.uimodeler.util;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.uml2uim.OpenUserInterfaceAction;
import org.opaeum.uml2uim.RegenerateAction;
import org.opaeum.uml2uim.RegenerateRecursivelyAction;

public class UimContributionFactory extends ExtensionContributionFactory{
	public UimContributionFactory(){
	}
	@Override
	public void createContributionItems(IServiceLocator serviceLocator,IContributionRoot additions){
		ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		if(s.getSelection() instanceof IStructuredSelection){
			IStructuredSelection ss = (IStructuredSelection) s.getSelection();
			EObject eobject = EmfElementFinder.adaptObject(ss.getFirstElement());
			if(hasConfigFile(ss) || eobject instanceof Class || eobject instanceof Operation || eobject instanceof Package){
				MenuManager menuManager = new MenuManager("UI");
				menuManager.add(new CompoundContributionItem(){
					@Override
					protected IContributionItem[] getContributionItems(){
						IAction regen = new RegenerateRecursivelyAction();
						IAction regen2 = new RegenerateAction();
						IAction open = new OpenUserInterfaceAction();
						return new IContributionItem[]{new ActionContributionItem(open),new ActionContributionItem(regen2),
								new ActionContributionItem(regen)};
					}
				});
				additions.addContributionItem(menuManager, new Expression(){
					@Override
					public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
						return EvaluationResult.TRUE;
					}
				});
			}
		}
	}
	// if(eobject instanceof Page){
	// menuManager.add(new CompoundContributionItem(){
	// @Override
	// protected IContributionItem[] getContributionItems(){
	// CommandContributionItemParameter parameter = new CommandContributionItemParameter(PlatformUI.getWorkbench()
	// .getActiveWorkbenchWindow(), "org.opaeum.uimodeler.util.CreateUserInterfaceDiagramCommand",
	// "org.opaeum.uimodeler.util.CreateUserInterfaceDiagramCommand", null, WorkbenchImages
	// .getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR), WorkbenchImages
	// .getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR_DISABLED), null, null, null, null,
	// CommandContributionItem.STYLE_CHECK, null, false);
	// return new IContributionItem[]{new CommandContributionItem(parameter)};
	// }
	// });
	// }
	// if(eobject instanceof CubeQuery){
	// menuManager.add(new CompoundContributionItem(){
	// @Override
	// protected IContributionItem[] getContributionItems(){
	// CommandContributionItemParameter parameter = new CommandContributionItemParameter(PlatformUI.getWorkbench()
	// .getActiveWorkbenchWindow(), "org.opaeum.uimodeler.cubequery.diagram.CreateCubeQueryDiagramCommand",
	// "org.opaeum.uimodeler.cubequery.diagram.CreateCubeQueryDiagramCommand", null, WorkbenchImages
	// .getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR), WorkbenchImages
	// .getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR_DISABLED), null, null, null, null,
	// CommandContributionItem.STYLE_CHECK, null, false);
	// return new IContributionItem[]{new CommandContributionItem(parameter)};
	// }
	// });
	// }
	// if(eobject instanceof PerspectiveConfiguration){
	// menuManager.add(new CompoundContributionItem(){
	// @Override
	// protected IContributionItem[] getContributionItems(){
	// CommandContributionItemParameter parameter = new CommandContributionItemParameter(PlatformUI.getWorkbench()
	// .getActiveWorkbenchWindow(), "org.opaeum.uimodeler.perspective.diagram.CreatePerspectiveDiagramCommand",
	// "org.opaeum.uimodeler.perspective.diagram.CreatePerspectiveDiagramCommand", null, WorkbenchImages
	// .getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR), WorkbenchImages
	// .getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR_DISABLED), null, null, null, null,
	// CommandContributionItem.STYLE_CHECK, null, false);
	// return new IContributionItem[]{new CommandContributionItem(parameter)};
	// }
	// });
	// }
	// if(eobject instanceof AbstractEditor){
	// menuManager.add(new CompoundContributionItem(){
	// @Override
	// protected IContributionItem[] getContributionItems(){
	// CommandContributionItemParameter parameter = new CommandContributionItemParameter(PlatformUI.getWorkbench()
	// .getActiveWorkbenchWindow(), "org.opaeum.metamodels.uim.actionbar.diagram.CreateAbstractActionBarDiagramCommand",
	// "org.opaeum.metamodels.uim.actionbar.diagram.CreateAbstractActionBarDiagramCommand", null, WorkbenchImages
	// .getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR), WorkbenchImages
	// .getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR_DISABLED), null, null, null, null,
	// CommandContributionItem.STYLE_CHECK, null, false);
	// return new IContributionItem[]{new CommandContributionItem(parameter)};
	// }
	// });
	// }
	// }
	private static boolean hasConfigFile(IStructuredSelection selection2){
		if(selection2.getFirstElement() instanceof IContainer){
			IContainer firstElement = (IContainer) selection2.getFirstElement();
			if(firstElement != null){
				return firstElement.findMember("opaeum.properties") != null;
			}
		}
		return false;
	}
}
