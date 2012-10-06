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
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.action.LinkToQuery;
import org.opaeum.uim.action.TransitionButton;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.editor.InstanceEditor;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.resources.InMemoryNotationResource;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.AbstractEditorEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.ActionBarActionBarChildrenCompartmentEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.ActionBarEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.ActionBarNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButton2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLink2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButton2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UserInterfaceEditPart;

@SuppressWarnings("unchecked")
public class DiagramCreator{
//	// private UserInterfaceRoot pageContainer;
//	private InMemoryNotationResource resource;
//	SashWindowsMngr windowsManager;
//	// private TabFolder folder;
//	public DiagramCreator(InMemoryNotationResource diagramsResource,SashWindowsMngr windowsManager){
//		// this.pageContainer = cf;
//		this.resource = diagramsResource;
//		this.windowsManager = windowsManager;
//	}
//	public void createDiagrams(){
//		windowsManager.getSashModel().getWindows();
//		Window window;
//		if(windowsManager.getSashModel().getWindows().size() == 1){
//			window = windowsManager.getSashModel().getWindows().get(0);
//			folder = (TabFolder) window.getChildren().get(0);
//		}else{
//			window = DiFactory.eINSTANCE.createWindow();
//			windowsManager.getSashModel().getWindows().add(window);
//			folder = DiFactory.eINSTANCE.createTabFolder();
//			window.getChildren().add(folder);
//		}
//		windowsManager.getSashModel().setCurrentSelection(folder);
//		addPages(pageContainer);
//		if(pageContainer instanceof InstanceEditor){
//			InstanceEditor e = (InstanceEditor) pageContainer;
//			ActionBar actionBar = e.getActionBar();
//			System.out.println(diagram);
//			// PageRef pageRef2 = DiFactory.eINSTANCE.createPageRef();
//			// pageRef2.setEmfPageIdentifier(diagram);
//			// folder.getChildren().add(pageRef2);
//		}
//	}
//	protected void addPages(UserInterfaceRoot userInterface2){
//		for(Page page:userInterface2.getPages()){
//			Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
//		}
//	}
}