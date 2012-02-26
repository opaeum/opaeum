package org.opaeum.reverse.popup.actions;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

public class ReverseEngineeringContributionFactory extends ExtensionContributionFactory{
	public ReverseEngineeringContributionFactory(){
	}
	@Override
	public void createContributionItems(IServiceLocator serviceLocator,IContributionRoot additions){
		MenuManager menuManager = new MenuManager("Opaeum");
		Expression visibleWhen = new Expression(){
			@Override
			public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
				ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
				if(s.getSelection() instanceof IStructuredSelection){
					IStructuredSelection selection = (IStructuredSelection) s.getSelection();
					if(selection.getFirstElement() instanceof JDBCSchema || selection.getFirstElement() instanceof JDBCTable){
						return EvaluationResult.TRUE;
					}
				}
				return EvaluationResult.FALSE;
			}
		};
		additions.addContributionItem(menuManager, visibleWhen);
		menuManager.add(new DynamicReverseMenu());
	}
}
