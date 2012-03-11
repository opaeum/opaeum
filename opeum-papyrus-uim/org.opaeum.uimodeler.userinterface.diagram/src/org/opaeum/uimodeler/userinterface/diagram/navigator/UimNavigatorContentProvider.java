package org.opaeum.uimodeler.userinterface.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInAction2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartment2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntity2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntityEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToOperationEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationAction2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.VerticalPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.part.UimVisualIDRegistry;

/**
 * @generated
 */
public class UimNavigatorContentProvider implements ICommonContentProvider{
	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];
	/**
	 * @generated
	 */
	private Viewer myViewer;
	/**
	 * @generated
	 */
	private AdapterFactoryEditingDomain myEditingDomain;
	/**
	 * @generated
	 */
	private WorkspaceSynchronizer myWorkspaceSynchronizer;
	/**
	 * @generated
	 */
	private Runnable myViewerRefreshRunnable;
	/**
	 * @generated
	 */
	@SuppressWarnings({"unchecked","serial","rawtypes"})
	public UimNavigatorContentProvider(){
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		myEditingDomain.setResourceToReadOnlyMap(new HashMap(){
			public Object get(Object key){
				if(!containsKey(key)){
					put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		myViewerRefreshRunnable = new Runnable(){
			public void run(){
				if(myViewer != null){
					myViewer.refresh();
				}
			}
		};
		myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain, new WorkspaceSynchronizer.Delegate(){
			public void dispose(){
			}
			public boolean handleResourceChanged(final Resource resource){
				for(Iterator it = myEditingDomain.getResourceSet().getResources().iterator();it.hasNext();){
					Resource nextResource = (Resource) it.next();
					nextResource.unload();
				}
				if(myViewer != null){
					myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
				}
				return true;
			}
			public boolean handleResourceDeleted(Resource resource){
				for(Iterator it = myEditingDomain.getResourceSet().getResources().iterator();it.hasNext();){
					Resource nextResource = (Resource) it.next();
					nextResource.unload();
				}
				if(myViewer != null){
					myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
				}
				return true;
			}
			public boolean handleResourceMoved(Resource resource,final URI newURI){
				for(Iterator it = myEditingDomain.getResourceSet().getResources().iterator();it.hasNext();){
					Resource nextResource = (Resource) it.next();
					nextResource.unload();
				}
				if(myViewer != null){
					myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
				}
				return true;
			}
		});
	}
	/**
	 *
	 *Papyrus Template
	 *this method is a modification of gmf code in order to avoid  getViewChidreen() method becoming greater than 64kb.
	 *@generated
	 **/
	private Object[] getViewChildrenForUserInterfaceEditPart(View view,Object parentElement){
		Collection result = new ArrayList();
		Collection connectedViews = getChildrenByType(Collections.singleton(view), UimVisualIDRegistry.getType(GridPanelEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view), UimVisualIDRegistry.getType(HorizontalPanelEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view), UimVisualIDRegistry.getType(VerticalPanelEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		return result.toArray();
	}
	/**
	 *
	 *Papyrus Template
	 *this method is a modification of gmf code in order to avoid  getViewChidreen() method becoming greater than 64kb.
	 *@generated
	 **/
	private Object[] getViewChildrenForGridPanelEditPart(View view,Object parentElement){
		Collection result = new ArrayList();
		Collection connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(UimFieldEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(BuiltInActionEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(HorizontalPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(VerticalPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(TransitionActionEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(OperationActionEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(LinkToOperationEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(LinkToEntityEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(VerticalPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(HorizontalPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(UimDataTableEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(GridPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		return result.toArray();
	}
	/**
	 *
	 *Papyrus Template
	 *this method is a modification of gmf code in order to avoid  getViewChidreen() method becoming greater than 64kb.
	 *@generated
	 **/
	private Object[] getViewChildrenForUimDataTableEditPart(View view,Object parentElement){
		Collection result = new ArrayList();
		Collection connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(UimField2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(LinkToEntity2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(BuiltInAction2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(OperationAction2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(BuiltInAction3EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(OperationAction3EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		return result.toArray();
	}
	/**
	 *
	 *Papyrus Template
	 *this method is a modification of gmf code in order to avoid  getViewChidreen() method becoming greater than 64kb.
	 *@generated
	 **/
	private Object[] getViewChildrenForGridPanel2EditPart(View view,Object parentElement){
		Collection result = new ArrayList();
		Collection connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(UimFieldEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(BuiltInActionEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(HorizontalPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(VerticalPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(TransitionActionEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(OperationActionEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(LinkToOperationEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(LinkToEntityEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(VerticalPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(HorizontalPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(UimDataTableEditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		connectedViews = getChildrenByType(Collections.singleton(view),
				UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID));
		connectedViews = getChildrenByType(connectedViews, UimVisualIDRegistry.getType(GridPanel2EditPart.VISUAL_ID));
		result.addAll(createNavigatorItems(connectedViews, parentElement, false));
		return result.toArray();
	}
	/**
	 * @generated
	 */
	public void dispose(){
		myWorkspaceSynchronizer.dispose();
		myWorkspaceSynchronizer = null;
		myViewerRefreshRunnable = null;
		for(Iterator it = myEditingDomain.getResourceSet().getResources().iterator();it.hasNext();){
			Resource resource = (Resource) it.next();
			resource.unload();
		}
		((TransactionalEditingDomain) myEditingDomain).dispose();
		myEditingDomain = null;
	}
	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
		myViewer = viewer;
	}
	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement){
		return getChildren(inputElement);
	}
	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento){
	}
	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento){
	}
	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig){
	}
	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement){
		if(parentElement instanceof IFile){
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource resource = myEditingDomain.getResourceSet().getResource(fileURI, true);
			Collection result = new ArrayList();
			return result.toArray();
		}
		if(parentElement instanceof UimNavigatorGroup){
			UimNavigatorGroup group = (UimNavigatorGroup) parentElement;
			return group.getChildren();
		}
		if(parentElement instanceof UimNavigatorItem){
			UimNavigatorItem navigatorItem = (UimNavigatorItem) parentElement;
			if(navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())){
				return EMPTY_ARRAY;
			}
			return getViewChildren(navigatorItem.getView(), parentElement);
		}
		return EMPTY_ARRAY;
	}
	/**
	 * @generated
	 */
	private Object[] getViewChildren(View view,Object parentElement){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case UserInterfaceEditPart.VISUAL_ID:{
			//modification of the template to avoid mistake of 65kb.
			return getViewChildrenForUserInterfaceEditPart(view, parentElement);
		}
		case GridPanelEditPart.VISUAL_ID:{
			//modification of the template to avoid mistake of 65kb.
			return getViewChildrenForGridPanelEditPart(view, parentElement);
		}
		case UimDataTableEditPart.VISUAL_ID:{
			//modification of the template to avoid mistake of 65kb.
			return getViewChildrenForUimDataTableEditPart(view, parentElement);
		}
		case GridPanel2EditPart.VISUAL_ID:{
			//modification of the template to avoid mistake of 65kb.
			return getViewChildrenForGridPanel2EditPart(view, parentElement);
		}
		}
		return EMPTY_ARRAY;
	}
	/**
	 * @generated
	 */
	private Collection getLinksSourceByType(Collection edges,String type){
		Collection result = new ArrayList();
		for(Iterator it = edges.iterator();it.hasNext();){
			Edge nextEdge = (Edge) it.next();
			View nextEdgeSource = nextEdge.getSource();
			if(type.equals(nextEdgeSource.getType()) && isOwnView(nextEdgeSource)){
				result.add(nextEdgeSource);
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	private Collection getLinksTargetByType(Collection edges,String type){
		Collection result = new ArrayList();
		for(Iterator it = edges.iterator();it.hasNext();){
			Edge nextEdge = (Edge) it.next();
			View nextEdgeTarget = nextEdge.getTarget();
			if(type.equals(nextEdgeTarget.getType()) && isOwnView(nextEdgeTarget)){
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	private Collection getOutgoingLinksByType(Collection nodes,String type){
		Collection result = new ArrayList();
		for(Iterator it = nodes.iterator();it.hasNext();){
			View nextNode = (View) it.next();
			result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}
	/**
	 * @generated
	 */
	private Collection getIncomingLinksByType(Collection nodes,String type){
		Collection result = new ArrayList();
		for(Iterator it = nodes.iterator();it.hasNext();){
			View nextNode = (View) it.next();
			result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}
	/**
	 * @generated
	 */
	private Collection getChildrenByType(Collection nodes,String type){
		Collection result = new ArrayList();
		for(Iterator it = nodes.iterator();it.hasNext();){
			View nextNode = (View) it.next();
			result.addAll(selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}
	/**
	 * @generated
	 */
	private Collection getDiagramLinksByType(Collection diagrams,String type){
		Collection result = new ArrayList();
		for(Iterator it = diagrams.iterator();it.hasNext();){
			Diagram nextDiagram = (Diagram) it.next();
			result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}
	/**
	 * @generated
	 */
	private Collection selectViewsByType(Collection views,String type){
		Collection result = new ArrayList();
		for(Iterator it = views.iterator();it.hasNext();){
			View nextView = (View) it.next();
			if(type.equals(nextView.getType()) && isOwnView(nextView)){
				result.add(nextView);
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	private boolean isOwnView(View view){
		return UserInterfaceEditPart.MODEL_ID.equals(UimVisualIDRegistry.getModelID(view));
	}
	/**
	 * @generated
	 */
	private Collection createNavigatorItems(Collection views,Object parent,boolean isLeafs){
		Collection result = new ArrayList();
		for(Iterator it = views.iterator();it.hasNext();){
			result.add(new UimNavigatorItem((View) it.next(), parent, isLeafs));
		}
		return result;
	}
	/**
	 * @generated
	 */
	public Object getParent(Object element){
		if(element instanceof UimAbstractNavigatorItem){
			UimAbstractNavigatorItem abstractNavigatorItem = (UimAbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}
	/**
	 * @generated
	 */
	public boolean hasChildren(Object element){
		return element instanceof IFile || getChildren(element).length > 0;
	}
}
