package org.opaeum.uml2uim;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.core.sashwindows.di.DiFactory;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageRef;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.papyrus.infra.core.sashwindows.di.TabFolder;
import org.eclipse.papyrus.infra.core.sashwindows.di.Window;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.LinkToQuery;
import org.opaeum.uim.action.OperationButton;
import org.opaeum.uim.action.OperationPopup;
import org.opaeum.uim.action.TransitionButton;
import org.opaeum.uim.action.UimLink;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorActionBar;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.AbstractEditorEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.EditorActionBarActionBarChildrenCompartmentEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.EditorActionBarEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.EditorActionBarNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UserInterfaceEditPart;

public class DiagramCreator{
	private PageContainer pageContainer;
	private Resource resource;
	SashWindowsMngr windowsManager;
	private TabFolder folder;
	public DiagramCreator(PageContainer cf,Resource diagramsResource,SashWindowsMngr windowsManager){
		this.pageContainer = cf;
		this.resource = diagramsResource;
		this.windowsManager = windowsManager;
	}
	public void createDiagrams(){
		windowsManager.getSashModel().getWindows();
		Window window;
		if(windowsManager.getSashModel().getWindows().size() == 1){
			window = windowsManager.getSashModel().getWindows().get(0);
			folder = (TabFolder) window.getChildren().get(0);
		}else{
			window = DiFactory.eINSTANCE.createWindow();
			windowsManager.getSashModel().getWindows().add(window);
			folder = DiFactory.eINSTANCE.createTabFolder();
			window.getChildren().add(folder);
		}
		windowsManager.getSashModel().setCurrentSelection(folder);
		addPages(pageContainer);
		if(pageContainer instanceof AbstractEditor){
			AbstractEditor e = (AbstractEditor) pageContainer;
			EditorActionBar actionBar = e.getActionBar();
			Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
			diagram.setElement(e);
			diagram.setType(AbstractEditorEditPart.MODEL_ID);
			diagram.setName(actionBar.getName());
			diagram.setMutable(true);
			diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
			diagram.setVisible(true);
			Shape panelShape = NotationFactory.eINSTANCE.createShape();
			panelShape.setElement(actionBar);
			panelShape.setType(EditorActionBarEditPart.VISUAL_ID + "");
			panelShape.setFontName("Sans");
			panelShape.setFontHeight(8);
			panelShape.setLineColor(0);
			DecorationNode labelNode = NotationFactory.eINSTANCE.createDecorationNode();
			labelNode.setType(EditorActionBarNameEditPart.VISUAL_ID + "");
			panelShape.getPersistedChildren().add(labelNode);
			DecorationNode compartmentDecoration = NotationFactory.eINSTANCE.createDecorationNode();
			compartmentDecoration.setType(EditorActionBarActionBarChildrenCompartmentEditPart.VISUAL_ID + "");
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
							org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInActionButtonEditPart.VISUAL_ID + "", null);
				}else if(uimComponent instanceof OperationButton){
					addComponent(compartmentDecoration, uimComponent, org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.OperationButtonEditPart.VISUAL_ID + "", null);
				}else if(uimComponent instanceof TransitionButton){
					addComponent(compartmentDecoration, uimComponent, org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.TransitionButtonEditPart.VISUAL_ID + "", null);
				}else if(uimComponent instanceof BuiltInLink){
					addComponent(compartmentDecoration, uimComponent, org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInLinkEditPart.VISUAL_ID + "", null);
				}else if(uimComponent instanceof LinkToQuery){
					addComponent(compartmentDecoration, uimComponent, org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.LinkToQueryEditPart.VISUAL_ID + "", null);
				}
			}
			resource.getContents().add(diagram);
			PageRef pageRef = DiFactory.eINSTANCE.createPageRef();
			pageRef.setEmfPageIdentifier(diagram);
			windowsManager.getPageList().getAvailablePage().add(pageRef);
			PageRef pageRef2 = DiFactory.eINSTANCE.createPageRef();
			pageRef2.setEmfPageIdentifier(diagram);
			folder.getChildren().add(pageRef2);
		}
	}
	protected void addPages(PageContainer userInterface2){
		for(Page page:userInterface2.getPages()){
			Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
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
			resource.getContents().add(diagram);
			PageRef pageRef = DiFactory.eINSTANCE.createPageRef();
			pageRef.setEmfPageIdentifier(diagram);
			windowsManager.getPageList().getAvailablePage().add(pageRef);
			PageRef pageRef2 = DiFactory.eINSTANCE.createPageRef();
			pageRef2.setEmfPageIdentifier(diagram);
			folder.getChildren().add(pageRef2);
		}
	}
	private void populatePanelPanel(View diagram,org.opaeum.uim.panel.AbstractPanel panel){
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
		if(panel.eContainer() instanceof UserInterface){
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
				addComponent(compartmentDecoration, uimComponent, UimFieldEditPart.VISUAL_ID + "", UimFieldNameEditPart.VISUAL_ID + "");
			}else if(uimComponent instanceof GridPanel){
				populatePanelPanel(compartmentDecoration, (GridPanel) uimComponent);
			}else if(uimComponent instanceof UimDataTable){
				populateDataTable(compartmentDecoration, (UimDataTable) uimComponent);
			}else if(uimComponent instanceof BuiltInActionButton){
				addComponent(compartmentDecoration, uimComponent, BuiltInActionButtonEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof OperationButton){
				addComponent(compartmentDecoration, uimComponent, OperationButtonEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof TransitionButton){
				addComponent(compartmentDecoration, uimComponent, TransitionButtonEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof BuiltInLink){
				addComponent(compartmentDecoration, uimComponent, BuiltInLinkEditPart.VISUAL_ID + "", null);
			}else if(uimComponent instanceof LinkToQuery){
				addComponent(compartmentDecoration, uimComponent, LinkToQueryEditPart.VISUAL_ID + "", null);
			}
		}
	}
	private void addComponent(DecorationNode compartmentDecoration,UimComponent uimComponent,String shapeId,String labelId){
		Shape fieldShape = NotationFactory.eINSTANCE.createShape();
		compartmentDecoration.getPersistedChildren().add(fieldShape);
		fieldShape.setElement(uimComponent);
		fieldShape.setType(shapeId);
		if(labelId != null){
			DecorationNode fieldLabelNode = NotationFactory.eINSTANCE.createDecorationNode();
			fieldLabelNode.setType(labelId);
			fieldShape.getPersistedChildren().add(fieldLabelNode);
		}
	}
	private void populateDataTable(View diagram,UimDataTable panel){
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
				Shape fieldShape = NotationFactory.eINSTANCE.createShape();
				columnsCompartment.getPersistedChildren().add(fieldShape);
				fieldShape.setElement(uimComponent);
				fieldShape.setType(UimField2EditPart.VISUAL_ID + "");
			}else if(uimComponent instanceof GridPanel){
				populatePanelPanel(actionBarCompartment, (GridPanel) uimComponent);
			}else if(uimComponent instanceof UimDataTable){
				populateDataTable(actionBarCompartment, (UimDataTable) uimComponent);
			}
		}
		for(UimComponent uimComponent:panel.getActionsOnMultipleSelection()){
			if(uimComponent instanceof BuiltInActionButton){
				Shape fieldShape = NotationFactory.eINSTANCE.createShape();
				actionBarCompartment.getPersistedChildren().add(fieldShape);
				fieldShape.setElement(uimComponent);
				fieldShape.setType(BuiltInActionButton3EditPart.VISUAL_ID + "");
			}else if(uimComponent instanceof OperationButton){
				Shape fieldShape = NotationFactory.eINSTANCE.createShape();
				actionBarCompartment.getPersistedChildren().add(fieldShape);
				fieldShape.setElement(uimComponent);
				fieldShape.setType(OperationButton3EditPart.VISUAL_ID + "");
				OperationPopup popup = ((OperationButton) uimComponent).getPopup();
				addPages(popup);
			}
		}
	}
}