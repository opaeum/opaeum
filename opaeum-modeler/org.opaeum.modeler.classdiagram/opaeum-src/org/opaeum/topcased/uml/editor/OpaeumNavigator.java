package org.opaeum.topcased.uml.editor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.IOutlineMenuConstants;
import org.topcased.modeler.internal.actions.CreateDiagramAction;
import org.topcased.modeler.uml.actions.DefineProfileAction;
import org.topcased.modeler.uml.actions.UMLEObjectAction;
import org.topcased.modeler.uml.editor.outline.UMLNavigator;

public class OpaeumNavigator extends UMLNavigator{
	@Override
	protected void initProviders(){
		super.initProviders();
		// AdapterFactoryContentProvider adapterContentProvider = new
		// NavigatorAdapterFactoryContentProvider(getModeler().getAdapterFactory());
		// adapterContentProvider.inputChanged(getTreeViewer(), null, null);
		// getTreeViewer().setContentProvider(new ModelContentProvider(getModeler(),adapterContentProvider){
		// @Override
		// public Object[] getChildren(Object parentElement){
		// ArrayList<Object> result = new ArrayList<Object>(Arrays.asList(super.getChildren(parentElement)));
		// return result.toArray();
		// }
		// @Override
		// public Object getParent(Object element){
		// return super.getParent(element);
		// }
		// @Override
		// public boolean hasChildren(Object element){
		// return super.hasChildren(element);
		// }
		// @Override
		// public Object[] getElements(Object inputElement){
		// ArrayList<Object> result = new ArrayList<Object>(Arrays.asList(super.getElements(inputElement)));
		// if(inputElement instanceof Element){
		// }
		// return result.toArray();
		// }
		// @Override
		// public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
		// super.inputChanged(viewer, oldInput, newInput);
		// }
		// @Override
		// public void dispose(){
		// super.dispose();
		// }
		// });
		//
	}
	@Override
	protected void createContextMenu(StructuredViewer sViewer){
		// TODO Auto-generated method stub
		super.createContextMenu(sViewer);
	}
	public OpaeumNavigator(Composite parent,Modeler editor,IPageSite pageSite){
		super(parent, editor, pageSite);
	}
	@Override
	protected void createSingleSelectionMenu(IMenuManager manager,Object selection){
		super.createSingleSelectionMenu(manager, selection);
		for(IContributionItem item:manager.getItems()){
			if(item instanceof MenuManager && ((MenuManager) item).getMenuText().equals("Add diagram")){
				MenuManager mm = (MenuManager) item;
				for(IContributionItem addAction:mm.getItems()){
					if(selection instanceof Behavior){
						if(StereotypesHelper.hasKeyword((Element) selection, StereotypeNames.BUSINES_PROCESS)){
							ActionContributionItem aci = (ActionContributionItem) addAction;
							if(!aci.getAction().getText().equals("Business Process Diagram")){
								mm.remove(addAction);
							}
						}else if(StereotypesHelper.hasKeyword((Element) selection, StereotypeNames.BUSINES_STATE_MACHINE)){
							ActionContributionItem aci = (ActionContributionItem) addAction;
							if(!aci.getAction().getText().equals("Business State Machine Diagram")){
								mm.remove(addAction);
							}
						}else if(StereotypesHelper.hasKeyword((Element) selection, StereotypeNames.SCREEN_FLOW)){
							ActionContributionItem aci = (ActionContributionItem) addAction;
							if(!aci.getAction().getText().equals("Screen Flow Diagram")){
								mm.remove(addAction);
							}
						}else if(selection instanceof Activity){
							ActionContributionItem aci = (ActionContributionItem) addAction;
							if(!aci.getAction().getText().equals("Method Diagram")){
								mm.remove(addAction);
							}
						}
					}
				}
			}
			if(item instanceof MenuManager && ((MenuManager) item).getMenuText().equals("Generate Primitive Types")){
				manager.remove(item);
			}
			if(item instanceof ActionContributionItem){
				ActionContributionItem actionItem = (ActionContributionItem) item;
				IAction act = actionItem.getAction();
				boolean isTopcasedAction = act instanceof UMLEObjectAction;
				if(isTopcasedAction && !(act instanceof DefineProfileAction)){
					manager.remove(item);
				}
			}
		}
	}
}