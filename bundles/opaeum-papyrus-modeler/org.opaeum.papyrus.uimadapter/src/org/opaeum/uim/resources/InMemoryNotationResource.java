package org.opaeum.uim.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.xmi.DOMHandler;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.sashwindows.di.DiFactory;
import org.eclipse.papyrus.infra.core.sashwindows.di.DiPackage;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageRef;
import org.eclipse.papyrus.infra.gmfdiag.css.resource.CSSNotationResource;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.action.LinkToQuery;
import org.opaeum.uim.action.TransitionButton;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.AbstractEditorEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.ActionBarActionBarChildrenCompartmentEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.ActionBarEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.ActionBarNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLink2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UserInterfaceEditPart;
import org.w3c.dom.Document;

@SuppressWarnings("unchecked")
public class InMemoryNotationResource extends CSSNotationResource{
	private UimModelSet modelSet;
	private EList<Adapter> dummyAdaptors = new BasicEList<Adapter>(){
		private static final long serialVersionUID = 4260280000124344269L;
		public boolean add(Adapter object){
			return superEAdapters().add(object);
		};
	};
	private Map<UserInteractionElement,Diagram> diagrams = new HashMap<UserInteractionElement,Diagram>();
	public InMemoryNotationResource(UimModelSet ms,URI uri){
		super(uri);
		this.modelSet = ms;
		super.resourceSet = ms;
	}
	private EList<Adapter> superEAdapters(){
		return super.eAdapters();
	}
	public Diagram getDiagram(UserInteractionElement key){
		if(key instanceof Page || key instanceof ActionBar){
			EditingDomain editingDomain = modelSet.getOpenUmlFile().getEditingDomain();
			Diagram diagram = diagrams.get(key);
			if(diagram == null){
				diagram = NotationFactory.eINSTANCE.createDiagram();
				getContents().add(diagram);
				PageRef pageRef = DiFactory.eINSTANCE.createPageRef();
				pageRef.setEmfPageIdentifier(diagram);
				InternalTransactionalEditingDomain ite = (InternalTransactionalEditingDomain) modelSet.getTransactionalEditingDomain();
				if(ite.getActiveTransaction() != null && ite.getActiveTransaction().isActive()){
					if(!ite.getActiveTransaction().isReadOnly()){
						modelSet.getWindowsManager().getPageList().getAvailablePage().add(pageRef);
					}else{
						System.out.println();
					}
				}else{
					try{
						editingDomain.getCommandStack().execute(
								AddCommand.create(editingDomain, modelSet.getWindowsManager().getPageList(),
										DiPackage.eINSTANCE.getPageList_AvailablePage(), pageRef));
					}catch(Exception e2){
						e2.printStackTrace();
						return null;
					}
				}
				final TransactionalEditingDomain txDomain = (TransactionalEditingDomain) editingDomain;
				final DiagramEventBroker deb = DiagramEventBroker.getInstance(txDomain);
				// NB!! we need to keep the DIagramEventBroker updated on Diagram changes
//				diagram.eAdapters().add(new EContentAdapter(){
//					@Override
//					public void notifyChanged(Notification notification){
//						super.notifyChanged(notification);
//						if(notification.getFeature() instanceof EStructuralFeature){
//							ResourceSetChangeEvent e = new ResourceSetChangeEvent(txDomain, null, Collections.singletonList(notification));
////							deb.resourceSetChanged(e);
//						}
//					}
//				});
				diagrams.put(key, diagram);
			}
			if(diagram.getPersistedChildren().isEmpty()){
				if(PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
						&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
					IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					boolean ignore = false;
					if(activeEditor != null && activeEditor instanceof PapyrusMultiDiagramEditor){
						PapyrusMultiDiagramEditor mm = (PapyrusMultiDiagramEditor) activeEditor;
						if(diagram != null && mm.getDiagram() == diagram){
							ignore = true;
						}
					}
					if(!ignore){
						if(key instanceof ActionBar){
							populateActionBar(key, diagram);
						}else if(key instanceof Page){
							populatePage((Page) key, diagram);
						}
					}
				}
			}
			return diagram;
		}else{
			return null;
		}
	}
	public void populatePage(Page page,Diagram diagram){
		diagram.setElement(page);
		diagram.setType(UserInterfaceEditPart.MODEL_ID);
		diagram.setName(page.getName());
		diagram.setMutable(true);
		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
		diagram.setVisible(true);
		org.opaeum.uim.panel.AbstractPanel panel = page.getPanel();
		if(panel instanceof GridPanel){
			populatePanelPanel(diagram, panel);
		}
		PageRef pageRef = DiFactory.eINSTANCE.createPageRef();
		pageRef.setEmfPageIdentifier(diagram);
	}
	void populatePanelPanel(View diagram,org.opaeum.uim.panel.AbstractPanel panel){
		Shape panelShape = NotationFactory.eINSTANCE.createShape();
		panelShape.setElement(panel);
		panelShape.setType(GridPanelEditPart.VISUAL_ID + "");
		panelShape.setFontName("Sans");
		panelShape.setFontHeight(8);
		panelShape.setLineColor(0);
		DecorationNode labelNode = NotationFactory.eINSTANCE.createDecorationNode();
		labelNode.setType(GridPanelNameEditPart.VISUAL_ID + "");
		panelShape.getPersistedChildren().add(labelNode);
		DecorationNode compartmentDecoration = NotationFactory.eINSTANCE.createDecorationNode();
		compartmentDecoration.setType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID + "");
		panelShape.getPersistedChildren().add(compartmentDecoration);
		Bounds bounds = NotationFactory.eINSTANCE.createBounds();
		if(panel.eContainer() instanceof Page){
			// TODO Irrelevant really- should be the size of the diagram
			bounds.setX(0);
			bounds.setY(0);
			bounds.setHeight(768);
			bounds.setWidth(1028);
		}
		panelShape.setLayoutConstraint(bounds);
		compartmentDecoration.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		diagram.getPersistedChildren().add(panelShape);
		for(UimComponent uimComponent:panel.getChildren()){
			if(uimComponent instanceof UimField){
				addComponent(compartmentDecoration, uimComponent, UimFieldEditPart.VISUAL_ID + "", null);// UimFieldNameEditPart.VISUAL_ID + "");
			}else if(uimComponent instanceof GridPanel){
				populatePanelPanel(compartmentDecoration, (GridPanel) uimComponent);
			}else if(uimComponent instanceof UimDataTable){
				populateDataTable(compartmentDecoration, (UimDataTable) uimComponent);
			}else if(uimComponent instanceof BuiltInActionButton){
				addComponent(compartmentDecoration, uimComponent, BuiltInActionButtonEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof InvocationButton){
				addComponent(compartmentDecoration, uimComponent, InvocationButtonEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof TransitionButton){
				addComponent(compartmentDecoration, uimComponent, TransitionButtonEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof BuiltInLink){
				addComponent(compartmentDecoration, uimComponent, BuiltInLinkEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof LinkToQuery){
				addComponent(compartmentDecoration, uimComponent, LinkToQueryEditPart.VISUAL_ID + "", null);
			}
		}
	}
	void populateActionBar(UserInteractionElement key,Diagram diagram){
		ActionBar actionBar = (ActionBar) key;
		diagram.setElement(actionBar);
		diagram.setType(AbstractEditorEditPart.MODEL_ID);
		diagram.setName(actionBar.getName());
		diagram.setMutable(true);
		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
		diagram.setVisible(true);
		Shape panelShape = NotationFactory.eINSTANCE.createShape();
		panelShape.setElement(actionBar);
		panelShape.setType(ActionBarEditPart.VISUAL_ID + "");
		panelShape.setFontName("Sans");
		panelShape.setFontHeight(8);
		panelShape.setLineColor(0);
		DecorationNode labelNode = NotationFactory.eINSTANCE.createDecorationNode();
		labelNode.setType(ActionBarNameEditPart.VISUAL_ID + "");
		panelShape.getPersistedChildren().add(labelNode);
		DecorationNode compartmentDecoration = NotationFactory.eINSTANCE.createDecorationNode();
		compartmentDecoration.setType(ActionBarActionBarChildrenCompartmentEditPart.VISUAL_ID + "");
		panelShape.getPersistedChildren().add(compartmentDecoration);
		Bounds bounds = NotationFactory.eINSTANCE.createBounds();
		bounds.setX(0);
		bounds.setY(0);
		bounds.setHeight(200);
		bounds.setWidth(1028);
		panelShape.setLayoutConstraint(bounds);
		compartmentDecoration.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		diagram.getPersistedChildren().add(panelShape);
		for(UimComponent uimComponent:actionBar.getChildren()){
			if(uimComponent instanceof BuiltInActionButton){
				addComponent(compartmentDecoration, uimComponent,
						org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInActionButtonEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof InvocationButton){
				addComponent(compartmentDecoration, uimComponent,
						org.opaeum.uimodeler.actionbar.diagram.edit.parts.InvocationButtonEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof TransitionButton){
				addComponent(compartmentDecoration, uimComponent,
						org.opaeum.uimodeler.actionbar.diagram.edit.parts.TransitionButtonEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof BuiltInLink){
				addComponent(compartmentDecoration, uimComponent, org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInLinkEditPart.VISUAL_ID
						+ "", null);
			}else if(uimComponent instanceof LinkToQuery){
				addComponent(compartmentDecoration, uimComponent, org.opaeum.uimodeler.actionbar.diagram.edit.parts.LinkToQueryEditPart.VISUAL_ID
						+ "", null);
			}
		}
		PageRef pageRef = DiFactory.eINSTANCE.createPageRef();
		pageRef.setEmfPageIdentifier(diagram);
	}
	public static void addComponent(DecorationNode compartmentDecoration,UimComponent uimComponent,String shapeId,String labelId){
		Shape fieldShape = NotationFactory.eINSTANCE.createShape();
		compartmentDecoration.getPersistedChildren().add(fieldShape);
		fieldShape.setElement(uimComponent);
		fieldShape.setType(shapeId);
		fieldShape.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		if(labelId != null){
			DecorationNode fieldLabelNode = NotationFactory.eINSTANCE.createDecorationNode();
			fieldLabelNode.setType(labelId);
			fieldLabelNode.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
			fieldShape.getPersistedChildren().add(fieldLabelNode);
		}
	}
	void populateDataTable(View diagram,UimDataTable panel){
		Shape panelShape = NotationFactory.eINSTANCE.createShape();
		diagram.getPersistedChildren().add(panelShape);
		panelShape.setElement(panel);
		panelShape.setType(UimDataTableEditPart.VISUAL_ID + "");
		panelShape.setFontName("Sans");
		panelShape.setFontHeight(8);
		panelShape.setLineColor(0);
		Bounds bounds = NotationFactory.eINSTANCE.createBounds();
		panelShape.setLayoutConstraint(bounds);
		DecorationNode columnsCompartment = NotationFactory.eINSTANCE.createDecorationNode();
		panelShape.getPersistedChildren().add(columnsCompartment);
		columnsCompartment.setType(UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID + "");
		columnsCompartment.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		DecorationNode actionBarCompartment = NotationFactory.eINSTANCE.createDecorationNode();
		actionBarCompartment.setType(UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID + "");
		panelShape.getPersistedChildren().add(actionBarCompartment);
		actionBarCompartment.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		for(UimComponent uimComponent:panel.getChildren()){
			if(uimComponent instanceof UimField){
				addComponent(columnsCompartment, uimComponent, UimField2EditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof BuiltInLink){
				addComponent(columnsCompartment, uimComponent, BuiltInLink2EditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof BuiltInActionButton){
				addComponent(columnsCompartment, uimComponent, BuiltInActionButton2EditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof InvocationButton){
				addComponent(columnsCompartment, uimComponent, InvocationButton2EditPart.VISUAL_ID + "", null);
			}
		}
		for(UimComponent uimComponent:panel.getActionsOnMultipleSelection()){
			if(uimComponent instanceof BuiltInActionButton){
				Shape fieldShape = NotationFactory.eINSTANCE.createShape();
				actionBarCompartment.getPersistedChildren().add(fieldShape);
				fieldShape.setElement(uimComponent);
				fieldShape.setType(BuiltInActionButton3EditPart.VISUAL_ID + "");
			}else if(uimComponent instanceof InvocationButton){
				Shape fieldShape = NotationFactory.eINSTANCE.createShape();
				actionBarCompartment.getPersistedChildren().add(fieldShape);
				fieldShape.setElement(uimComponent);
				fieldShape.setType(InvocationButton3EditPart.VISUAL_ID + "");
			}
		}
	}
	@Override
	public EList<Adapter> eAdapters(){
		return dummyAdaptors;
	}
	@Override
	public Document save(Document doc,Map<?,?> options,DOMHandler handler){
		return null;
	}
	@Override
	public void save(Map<?,?> options) throws IOException{
		EList<PageRef> availablePages = modelSet.getWindowsManager().getPageList().getAvailablePage();
		for(PageRef pr:new ArrayList<PageRef>(availablePages)){
			if(pr.getPageIdentifier() instanceof Diagram && ((Diagram) pr.getPageIdentifier()).getElement() instanceof Page){
				availablePages.remove(pr);
			}
		}
		modelSet.getWindowsManager().eResource().save(Collections.emptyMap());
	}
	@Override
	public boolean isLoaded(){
		return true;
	}
	@Override
	public boolean isLoading(){
		return false;
	}
	@Override
	public boolean isModified(){
		return false;
	}
}
