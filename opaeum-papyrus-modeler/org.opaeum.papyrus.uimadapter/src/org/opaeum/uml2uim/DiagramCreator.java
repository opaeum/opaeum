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
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.action.OperationActionPopup;
import org.opaeum.uim.action.UimLink;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionNameKindEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UserInterfaceEditPart;

public class DiagramCreator{
	private UserInterfaceEntryPoint userInterface;
	private Resource resource;
	SashWindowsMngr windowsManager;
	private TabFolder folder;
	public DiagramCreator(UserInterfaceEntryPoint cf,Resource diagramsResource,SashWindowsMngr windowsManager){
		this.userInterface = cf;
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
		addPages(userInterface);
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
				populateGridPanel(diagram, panel);
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
	private void populateGridPanel(View diagram,org.opaeum.uim.panel.AbstractPanel panel){
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
			bounds.setHeight(600);
			bounds.setWidth(800);
		}
		panelShape.setLayoutConstraint(bounds);
		compartmentDecoration.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		diagram.getPersistedChildren().add(panelShape);
		for(UimComponent uimComponent:panel.getChildren()){
			if(uimComponent instanceof UimField){
				addComponent(compartmentDecoration, uimComponent, UimFieldEditPart.VISUAL_ID + "", UimFieldNameEditPart.VISUAL_ID + "");
			}else if(uimComponent instanceof GridPanel){
				populateGridPanel(compartmentDecoration, (GridPanel) uimComponent);
			}else if(uimComponent instanceof UimDataTable){
				populateDataTable(compartmentDecoration, (UimDataTable) uimComponent);
			}else if(uimComponent instanceof BuiltInAction){
				addComponent(compartmentDecoration, uimComponent, BuiltInActionEditPart.VISUAL_ID + "", BuiltInActionNameKindEditPart.VISUAL_ID
						+ "");
			}else if(uimComponent instanceof UimLink){
			}
		}
	}
	private void addComponent(DecorationNode compartmentDecoration,UimComponent uimComponent,String shapeId,String labelId){
		Shape fieldShape = NotationFactory.eINSTANCE.createShape();
		compartmentDecoration.getPersistedChildren().add(fieldShape);
		fieldShape.setElement(uimComponent);
		fieldShape.setType(shapeId);
		DecorationNode fieldLabelNode = NotationFactory.eINSTANCE.createDecorationNode();
		fieldLabelNode.setType(labelId);
		fieldShape.getPersistedChildren().add(fieldLabelNode);
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
				populateGridPanel(actionBarCompartment, (GridPanel) uimComponent);
			}else if(uimComponent instanceof UimDataTable){
				populateDataTable(actionBarCompartment, (UimDataTable) uimComponent);
			}
		}
		for(UimComponent uimComponent:panel.getActionsOnMultipleSelection()){
			if(uimComponent instanceof BuiltInAction){
				Shape fieldShape = NotationFactory.eINSTANCE.createShape();
				actionBarCompartment.getPersistedChildren().add(fieldShape);
				fieldShape.setElement(uimComponent);
				fieldShape.setType(BuiltInAction3EditPart.VISUAL_ID + "");
			}else if(uimComponent instanceof OperationAction){
				Shape fieldShape = NotationFactory.eINSTANCE.createShape();
				actionBarCompartment.getPersistedChildren().add(fieldShape);
				fieldShape.setElement(uimComponent);
				fieldShape.setType(OperationAction3EditPart.VISUAL_ID + "");
				OperationActionPopup popup = ((OperationAction) uimComponent).getPopup();
				addPages(popup);
			}
		}
	}
}