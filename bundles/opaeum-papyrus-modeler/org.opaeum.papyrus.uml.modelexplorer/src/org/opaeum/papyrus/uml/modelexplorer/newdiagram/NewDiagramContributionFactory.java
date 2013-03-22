package org.opaeum.papyrus.uml.modelexplorer.newdiagram;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;
import org.opaeum.eclipse.menu.ICompoundContributionItem;

public class NewDiagramContributionFactory extends ExtensionContributionFactory{
	Expression visibleWhen = new Expression(){
		@Override
		public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
			return EvaluationResult.TRUE;
		}
	};
	public NewDiagramContributionFactory(){
	}
	@Override
	public void createContributionItems(IServiceLocator serviceLocator,IContributionRoot additions){
		ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		if(s.getSelection() instanceof IStructuredSelection){
			MenuManager menuManager = new MenuManager("New Diagram");
			IStructuredSelection selection = (IStructuredSelection) s.getSelection();
			maybeAddMenu(menuManager, new DynamicNewDiagramMenu(selection));
			if(!menuManager.isEmpty()){
				additions.addContributionItem(menuManager, visibleWhen);
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
