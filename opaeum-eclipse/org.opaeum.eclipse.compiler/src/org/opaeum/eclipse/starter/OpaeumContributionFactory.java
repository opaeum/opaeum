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
import org.opaeum.emf.extraction.EmfExtractionPhase;

public class OpaeumContributionFactory extends ExtensionContributionFactory{
	public OpaeumContributionFactory(){
	}
	@Override
	public void createContributionItems(IServiceLocator serviceLocator,IContributionRoot additions){
		MenuManager menuManager = new MenuManager("Opaeum");
		additions.addContributionItem(menuManager, new Expression(){
			@Override
			public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
				ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
				if(s.getSelection() instanceof IStructuredSelection){
					IStructuredSelection selection = (IStructuredSelection) s.getSelection();
					Object firstElement = selection.getFirstElement();
					if(firstElement instanceof IContainer){
						if(DynamicOpaeumMenu.hasUmlModels(selection) || DynamicOpaeumMenu.hasConfigFile(selection)){
							return EvaluationResult.TRUE;
						}
					}else{
						if(!(firstElement instanceof Element) && firstElement instanceof IAdaptable){
							firstElement = ((IAdaptable) firstElement).getAdapter(EObject.class);
						}
						if(firstElement instanceof Model){
							return EvaluationResult.TRUE;
						}else if(firstElement instanceof Element){
							if(EmfExtractionPhase.canBeProcessedIndividually((EObject) firstElement)){
								return EvaluationResult.TRUE;
							}
						}else if(firstElement instanceof AbstractGraphicalEditPart){
							AbstractGraphicalEditPart a = (AbstractGraphicalEditPart) firstElement;
							if(a.getModel() instanceof Element && EmfExtractionPhase.canBeProcessedIndividually((EObject) a.getModel())){
								return EvaluationResult.TRUE;
							}
						}
					}
				}
				return EvaluationResult.FALSE;
			}
		});
		menuManager.add(new DynamicOpaeumMenu());
		MenuManager applyProfileMenu = new MenuManager("Apply Profile");
		Expression visibleWhen = new Expression(){
			@Override
			public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
				ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
				if(s.getSelection() instanceof IStructuredSelection){
					IStructuredSelection selection = (IStructuredSelection) s.getSelection();
					Object firstElement = selection.getFirstElement();
					if(!(firstElement instanceof Element) && firstElement instanceof IAdaptable){
						firstElement = ((IAdaptable) firstElement).getAdapter(EObject.class);
					}
					if(firstElement instanceof Model){
						return EvaluationResult.TRUE;
					}
				}
				return EvaluationResult.FALSE;
			}
		};
		additions.addContributionItem(applyProfileMenu, visibleWhen);
		applyProfileMenu.add(new ApplyProfileMenu());
		MenuManager importLibraryMenu = new MenuManager("Import Library");
		additions.addContributionItem(importLibraryMenu, visibleWhen);
		importLibraryMenu.add(new ImportLibraryMenu());
	}
}
