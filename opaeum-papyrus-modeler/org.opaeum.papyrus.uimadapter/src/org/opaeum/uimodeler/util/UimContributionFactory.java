package org.opaeum.uimodeler.util;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorActionBar;
import org.opaeum.uml2uim.OpenUserInterfaceAction;
import org.opaeum.uml2uim.RegenerateRecursivelyAction;
import org.opaeum.uml2uim.SynchronizeAction;

public class UimContributionFactory extends ExtensionContributionFactory{
	public UimContributionFactory(){
	}
	@Override
	public void createContributionItems(IServiceLocator serviceLocator,IContributionRoot additions){
		MenuManager menuManager = new MenuManager("UI");
		EObject eobject = null;
		ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		if(s.getSelection() instanceof IStructuredSelection){
			IStructuredSelection ss = (IStructuredSelection) s.getSelection();
			if(ss.getFirstElement() instanceof EObject){
				eobject = (EObject) ss.getFirstElement();
			}else if(ss.getFirstElement() instanceof IAdaptable){
				IAdaptable selection = (IAdaptable) ss.getFirstElement();
				eobject = (EObject) ((IAdaptable) selection).getAdapter(EObject.class);
			}
		}
		additions.addContributionItem(menuManager, new Expression(){
			@Override
			public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
				ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
				if(s.getSelection() instanceof IStructuredSelection){
					IStructuredSelection ss = (IStructuredSelection) s.getSelection();
					if(ss.getFirstElement() instanceof IAdaptable){
						IAdaptable selection = (IAdaptable) ss.getFirstElement();
						Object eobject = ((IAdaptable) selection).getAdapter(EObject.class);
						if(eobject instanceof UserInterface || eobject instanceof AbstractEditor || eobject instanceof org.eclipse.uml2.uml.Class || eobject instanceof Operation || eobject instanceof org.eclipse.uml2.uml.Package){
							// TODO add SingleScreenTasks
							return EvaluationResult.TRUE;
						}
					}
				}
				return EvaluationResult.FALSE;
			}
		});
		if(eobject instanceof UserInterface){
			menuManager.add(new CompoundContributionItem(){
				@Override
				protected IContributionItem[] getContributionItems(){
					CommandContributionItemParameter parameter = new CommandContributionItemParameter(PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow(), "org.opaeum.uimodeler.util.CreateUserInterfaceDiagramCommand",
							"org.opaeum.uimodeler.util.CreateUserInterfaceDiagramCommand", null, WorkbenchImages
									.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR), WorkbenchImages
									.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR_DISABLED), null, null, null, null,
							CommandContributionItem.STYLE_CHECK, null, false);
					return new IContributionItem[]{new CommandContributionItem(parameter)};
				}
			});
		}
		if(eobject instanceof AbstractEditor){
			menuManager.add(new CompoundContributionItem(){
				@Override
				protected IContributionItem[] getContributionItems(){
					CommandContributionItemParameter parameter = new CommandContributionItemParameter(PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow(), "org.opaeum.metamodels.uim.actionbar.diagram.CreateAbstractActionBarDiagramCommand",
							"org.opaeum.metamodels.uim.actionbar.diagram.CreateAbstractActionBarDiagramCommand", null, WorkbenchImages
									.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR), WorkbenchImages
									.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR_DISABLED), null, null, null, null,
							CommandContributionItem.STYLE_CHECK, null, false);
					return new IContributionItem[]{new CommandContributionItem(parameter)};
				}
			});
		}
		if(eobject instanceof org.eclipse.uml2.uml.Class || eobject instanceof Operation || eobject instanceof org.eclipse.uml2.uml.Package ){
			menuManager.add(new CompoundContributionItem(){
				@Override
				protected IContributionItem[] getContributionItems(){
					IAction sync = new SynchronizeAction();
					IAction regen = new RegenerateRecursivelyAction();
					IAction open = new OpenUserInterfaceAction();
					return new IContributionItem[]{new ActionContributionItem(open),new ActionContributionItem(sync),
							new ActionContributionItem(regen)};
				}
			});
		}
	}
}
