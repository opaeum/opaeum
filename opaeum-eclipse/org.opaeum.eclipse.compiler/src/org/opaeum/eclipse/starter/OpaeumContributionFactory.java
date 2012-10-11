package org.opaeum.eclipse.starter;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.newchild.OpaeumEditorMenu;
import org.opaeum.validation.ValidationPhase;

public class OpaeumContributionFactory extends ExtensionContributionFactory{
	public OpaeumContributionFactory(){
	}
	@Override
	public void createContributionItems(IServiceLocator serviceLocator,IContributionRoot additions){
		ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		if(s.getSelection() instanceof IStructuredSelection){
			MenuManager menuManager = new MenuManager("Opaeum");
			IStructuredSelection selection = (IStructuredSelection) s.getSelection();
			Object firstElement = selection.getFirstElement();
			if(firstElement instanceof IContainer){
				if(DynamicOpaeumMenu.hasUmlModels(selection) || DynamicOpaeumMenu.hasConfigFile(selection)){
					System.out.println("OpaeumContributionFactory.createContributionItems()");
					menuManager.add(new DynamicOpaeumMenu());
				}
			}
			EObject element = null;
			if(firstElement instanceof EObject){
				element = (EObject) firstElement;
			}else if(firstElement instanceof IAdaptable){
				Object adapter = ((IAdaptable) firstElement).getAdapter(EObject.class);
				if(adapter instanceof EObject){
					element = (EObject) adapter;
				}
			}else if(firstElement instanceof AbstractGraphicalEditPart){
				AbstractGraphicalEditPart a = (AbstractGraphicalEditPart) firstElement;
				if(a.getModel() instanceof EObject){
					{
						element = (EObject) a.getModel();
					}
				}
			}
			if(element != null){
				if((element.getClass().getSimpleName().equals("SimulationModelImpl") || element instanceof Model)){
					menuManager.add(new DynamicOpaeumMenu());
				}else if(ValidationPhase.canBeProcessedIndividually(element)){
					menuManager.add(new DynamicOpaeumMenu());
				}
			}
			if(menuManager.getSize() > 0){
				System.out.println("OpaeumContributionFactory.createContributionItems()");
				additions.addContributionItem(menuManager, new Expression(){
					@Override
					public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
						return EvaluationResult.TRUE;
					}
				});
			}
			if(element != null){
				additions.addContributionItem(new OpaeumEditorMenu(), new Expression(){
					@Override
					public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
						return EvaluationResult.TRUE;
					}
				});
				if(element instanceof Element && EmfPackageUtil.isRootObject((Element) element)){
					MenuManager applyProfileMenu = new MenuManager("Apply Profile");
					Expression visibleWhen = new Expression(){
						@Override
						public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
							return EvaluationResult.TRUE;
						}
					};
					additions.addContributionItem(applyProfileMenu, visibleWhen);
					applyProfileMenu.add(new ApplyProfileMenu());
					MenuManager importLibraryMenu = new MenuManager("Import Library");
					additions.addContributionItem(importLibraryMenu, visibleWhen);
					importLibraryMenu.add(new ImportLibraryMenu());
				}
			}
		}
	}
}
