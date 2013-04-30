package org.opaeum.eclipse.starter;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.menu.ApplyProfileMenu;
import org.opaeum.eclipse.menu.ApplyStereotypeMenu;
import org.opaeum.eclipse.menu.ICompoundContributionItem;
import org.opaeum.eclipse.menu.ImportLibraryMenu;
import org.opaeum.eclipse.newchild.OpaeumEditorMenu;
import org.opaeum.reverse.popup.actions.DynamicReverseDbMenu;
import org.opaeum.reverse.popup.actions.DynamicReverseJavaMenu;

public class OpaeumContributionFactory extends ExtensionContributionFactory{
	Expression visibleWhen = new Expression(){
		@Override
		public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
			return EvaluationResult.TRUE;
		}
	};
	public OpaeumContributionFactory(){
	}
	@Override
	public void createContributionItems(IServiceLocator serviceLocator,IContributionRoot additions){
		ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		if(s.getSelection() instanceof IStructuredSelection){
			MenuManager menuManager = new MenuManager("Opaeum");
			IStructuredSelection selection = (IStructuredSelection) s.getSelection();
			maybeAddMenu(menuManager, new DynamicOpaeumMenu(selection));
			maybeAddMenu(menuManager, new DynamicReverseDbMenu(selection));
			maybeAddMenu(menuManager, new DynamicReverseJavaMenu(selection));
			if(!menuManager.isEmpty()){
				additions.addContributionItem(menuManager, visibleWhen);
			}
			Object firstElement = selection.getFirstElement();
			EObject element=EmfElementFinder.adaptObject(firstElement);
			if(element != null){
				additions.addContributionItem(new OpaeumEditorMenu(), visibleWhen);
				if(element instanceof Element && EmfPackageUtil.isRootObject((Element) element)){
					MenuManager applyProfileMenu = new MenuManager("Apply Profile");
					additions.addContributionItem(applyProfileMenu, visibleWhen);
					applyProfileMenu.add(new ApplyProfileMenu());
					MenuManager importLibraryMenu = new MenuManager("Import Library");
					additions.addContributionItem(importLibraryMenu, visibleWhen);
					importLibraryMenu.add(new ImportLibraryMenu());
				}
				if(element instanceof Element){
					MenuManager applyStereotypesMenu = new MenuManager("Apply Stereotypes");
					additions.addContributionItem(applyStereotypesMenu, visibleWhen);
					applyStereotypesMenu.add(new ApplyStereotypeMenu());
				}
			}
		}
	}
	public void maybeAddMenu(MenuManager menuManager,ICompoundContributionItem menu){
		IContributionItem[] contributionItems = menu.getContributionItems();
		if(contributionItems.length>0){
			menuManager.add(menu);
		}
	}
}
