package org.opaeum.uimodeler.util;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.opaeum.uim.editor.EditorPage;

public class UimContributionFactory extends ExtensionContributionFactory{
	public UimContributionFactory(){
	}
	@Override
	public void createContributionItems(IServiceLocator serviceLocator,IContributionRoot additions){
		MenuManager menuManager = new MenuManager("Opaeum");
		additions.addContributionItem(menuManager, new Expression(){
			@Override
			public EvaluationResult evaluate(IEvaluationContext context) throws CoreException{
				System.out.println("UimContributionFactory.createContributionItems(...).new Expression() {...}.evaluate()");
				ISelectionService s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
				System.out.println(s.getSelection().getClass());
				if(s.getSelection() instanceof IStructuredSelection){
					IStructuredSelection ss = (IStructuredSelection) s.getSelection();
					System.out.println(ss.getFirstElement());
					if(ss.getFirstElement() instanceof IAdaptable){
						IAdaptable selection = (IAdaptable) ss.getFirstElement();
						Object eobject = ((IAdaptable) selection).getAdapter(EObject.class);
						if(eobject instanceof EditorPage){
							return EvaluationResult.TRUE;
						}
					}
				}
				return EvaluationResult.FALSE;
			}
		});
		menuManager.add(new CompoundContributionItem(){
			@Override
			protected IContributionItem[] getContributionItems(){
				CommandContributionItemParameter parameter = new CommandContributionItemParameter(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow(), "org.opaeum.uimodeler.util.CreateEditorPageDiagramCommand",
						"org.opaeum.uimodeler.util.CreateEditorPageDiagramCommand", null, WorkbenchImages
								.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR), WorkbenchImages
								.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR_DISABLED), null, null, null, null,
						CommandContributionItem.STYLE_CHECK, null, false);
				return new IContributionItem[]{new CommandContributionItem(parameter)};
			}
		});
	}
}
