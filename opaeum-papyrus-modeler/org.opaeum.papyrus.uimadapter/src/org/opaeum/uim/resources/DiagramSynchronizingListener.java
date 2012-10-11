package org.opaeum.uim.resources;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.opaeum.uim.Page;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.uml2uim.UserInterfaceUtil;

public final class DiagramSynchronizingListener extends EContentAdapter{
	private final InMemoryNotationResource resource;
	public DiagramSynchronizingListener(InMemoryNotationResource inMemoryNotationResource){
		resource = inMemoryNotationResource;
	}
	@Override
	public void notifyChanged(Notification notification){
		super.notifyChanged(notification);
		if(notification.getNotifier() instanceof EObject){
			EObject notifier = (EObject) notification.getNotifier();
			if(notifier.eClass().getEPackage().getNsURI().startsWith("http://opaeum.org/uimetamodel")){
				if(notification.getNotifier() instanceof Page && notification.getFeatureID(Page.class) == UimPackage.PAGE__NAME){
					Diagram diagram = resource.getDiagram((Page) notification.getNotifier());
					diagram.setName(((Page) notification.getNotifier()).getName());
				}else if(notification.getOldValue() instanceof UimComponent
						&& (notification.getEventType() == Notification.REMOVE || notification.getEventType() == Notification.SET)){
					UimComponent uic = (UimComponent) notification.getOldValue();
					removeReferences(uic);
				}else if(notification.getOldValue() instanceof Collection && notification.getEventType() == Notification.REMOVE_MANY){
					Collection<?> c = (Collection<?>) notification.getOldValue();
					for(Object object:c){
						if(object instanceof UimComponent){
							removeReferences((UimComponent) object);
						}
					}
				}
				if(PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
					IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					if(activeEditor != null && activeEditor instanceof PapyrusMultiDiagramEditor){
						PapyrusMultiDiagramEditor mm = (PapyrusMultiDiagramEditor) activeEditor;
						Diagram diagram = getDiagram(notifier);
						if(diagram != null && mm.getDiagram() != diagram){
							diagram.getPersistedChildren().clear();
							UserInteractionElement r = UserInterfaceUtil.getNearestPage(notifier);
							if(r instanceof Page){
								resource.populatePage((Page) r, diagram);
							}else if(r instanceof ActionBar){
								resource.populateActionBar(r, diagram);
							}
							// if(notification.getNewValue() instanceof UimComponent){
							// UimComponent uic = (UimComponent) notification.getNewValue();
							// TreeIterator<EObject> eAllContents = diagram.eAllContents();
							// Set<EObject> things = new HashSet<EObject>();
							// while(eAllContents.hasNext()){
							// things.add(eAllContents.next());
							// }
							// for(EObject eObject:things){
							// if(eObject.eContainer() != null && eObject instanceof View && ((View) eObject).getElement() == uic.eContainer()){
							// View parentView = (View) eObject;
							// if(uic.eContainer() instanceof ActionBar){
							// parentView.getPersistedChildren().clear();
							// resource.populateActionBar((UserInteractionElement) uic.eContainer(), parentView.getDiagram());
							// }else if(uic.eContainer() instanceof UimDataTable){
							// parentView.getPersistedChildren().clear();
							// resource.populateDataTable(parentView, (UimDataTable) uic.eContainer());
							// }else if(uic.eContainer() instanceof AbstractPanel){
							// parentView.getPersistedChildren().clear();
							// resource.populatePanelPanel(parentView, (AbstractPanel) uic.eContainer());
							// }
							// }
							// }
							// }
						}
					}
				}
			}
		}
	}
	@SuppressWarnings("rawtypes")
	protected void removeReferences(UimComponent uic){
		Diagram diagram = getDiagram(uic);
		if(diagram == null){
			System.out.println();
		}else if(uic.eContainer() == null){
			TreeIterator<EObject> eAllContents = diagram.eAllContents();
			Set<View> remove = new HashSet<View>();
			while(eAllContents.hasNext()){
				EObject eObject = (EObject) eAllContents.next();
				if(eObject instanceof View && (((View) eObject).getElement() == uic || ((View) eObject).getElement() == null)){
					remove.add((View) eObject);
				}
			}
			for(View view:remove){
				if(view.eContainingFeature().isMany()){
					Object eGet = view.eContainer().eGet(view.eContainingFeature());
					((Collection) eGet).remove(view);
				}else{
					view.eContainer().eSet(view.eContainingFeature(), null);
				}
			}
		}
	}
	protected Diagram getDiagram(EObject uic){
		Diagram diagram = resource.getDiagram(UserInterfaceUtil.getNearestPage(uic));
		return diagram;
	}
}