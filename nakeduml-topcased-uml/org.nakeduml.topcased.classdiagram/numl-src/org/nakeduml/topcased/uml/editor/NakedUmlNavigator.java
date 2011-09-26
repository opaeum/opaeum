package org.nakeduml.topcased.uml.editor;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.ModelContentProvider;
import org.topcased.modeler.uml.actions.DefineProfileAction;
import org.topcased.modeler.uml.actions.UMLEObjectAction;
import org.topcased.modeler.uml.editor.outline.UMLNavigator;

public class NakedUmlNavigator extends UMLNavigator{
	@Override
	protected void initProviders(){
		super.initProviders();
//        AdapterFactoryContentProvider adapterContentProvider = new NavigatorAdapterFactoryContentProvider(getModeler().getAdapterFactory());
//        adapterContentProvider.inputChanged(getTreeViewer(), null, null);
//		getTreeViewer().setContentProvider(new ModelContentProvider(getModeler(),adapterContentProvider){
//			@Override
//			public Object[] getChildren(Object parentElement){
//				ArrayList<Object> result = new ArrayList<Object>(Arrays.asList(super.getChildren(parentElement)));
//				return result.toArray();
//			}
//			@Override
//			public Object getParent(Object element){
//				return super.getParent(element);
//			}
//			@Override
//			public boolean hasChildren(Object element){
//				return super.hasChildren(element);
//			}
//			@Override
//			public Object[] getElements(Object inputElement){
//				ArrayList<Object> result = new ArrayList<Object>(Arrays.asList(super.getElements(inputElement)));
//				if(inputElement instanceof Element){
//				}
//				return result.toArray();
//			}
//			@Override
//			public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
//				super.inputChanged(viewer, oldInput, newInput);
//			}
//			@Override
//			public void dispose(){
//				super.dispose();
//			}
//		});
//
	}
	public NakedUmlNavigator(Composite parent,Modeler editor,IPageSite pageSite){
		super(parent, editor, pageSite);
	}
	@Override
	protected void createSingleSelectionMenu(IMenuManager manager,Object selection){
		super.createSingleSelectionMenu(manager, selection);
		for(IContributionItem item:manager.getItems()){
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