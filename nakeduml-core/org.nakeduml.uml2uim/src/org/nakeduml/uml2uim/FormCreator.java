package org.nakeduml.uml2uim;

import java.util.Collection;


import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.name.NameConverter;
import org.nakeduml.uim.ActionKind;
import org.nakeduml.uim.BuiltInAction;
import org.nakeduml.uim.ControlKind;
import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.FormPanel;
import org.nakeduml.uim.UIMContainer;
import org.nakeduml.uim.UIMDataColumn;
import org.nakeduml.uim.UIMDataTable;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UIMField;
import org.nakeduml.uim.UIMForm;
import org.nakeduml.uim.UIMTab;
import org.nakeduml.uim.UIMTabPanel;
import org.nakeduml.uim.UIMXYLayout;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.util.ControlUtil;
import org.nakeduml.uim.util.UimUtil;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsFactory;

public class FormCreator {
	UIMXYLayout mainTabLayout;
	GraphNode mainTabLayoutNode;
	private Diagrams diagrams;
	private UIMTabPanel tabPanel;
	private GraphNode tabPanelNode;

	public FormCreator(Diagrams diagrams) {
		
		this.diagrams = diagrams;
	}

	protected GraphNode createGraphNode(UserInteractionElement model,
			String presentation) {
		GraphNode node = DiagramInterchangeFactory.eINSTANCE.createGraphNode();
		EMFSemanticModelBridge bridge = DiagramInterchangeFactory.eINSTANCE
				.createEMFSemanticModelBridge();
		bridge.setElement(model);
		bridge.setPresentation(presentation);
		node.setSemanticModel(bridge);
		return node;
	}

	public void prepareFormPanel(UIMForm cf, String title,
			Collection<? extends TypedElement> typedElements) {
		Diagram diag = DiagramInterchangeFactory.eINSTANCE.createDiagram();
		EMFSemanticModelBridge bridge = DiagramInterchangeFactory.eINSTANCE
				.createEMFSemanticModelBridge();
		bridge.setPresentation("org.nakeduml.uim.classform");
		bridge.setElement(cf);
		diag.setSemanticModel(bridge);
		diag.setName(cf.getName());
		diag.setPosition(new Point());
		diag.setSize(new Dimension(1000, 1000));
		diag.setViewport(new Point());
		Diagrams subDiagrams = DiagramsFactory.eINSTANCE.createDiagrams();
		diagrams.getSubdiagrams().add(subDiagrams);
		subDiagrams.getDiagrams().add(diag);

		// Create FormPanel
		FormPanel formPanel = UIMFactory.eINSTANCE.createFormPanel();
		cf.setPanel(formPanel);
		formPanel.setName(title);
		GraphNode formPanelNode = createGraphNode(formPanel, "default");
		diag.getContained().add(formPanelNode);

		// Create Form XYLayout
		UIMXYLayout formLayout = UIMFactory.eINSTANCE.createUIMXYLayout();
		formPanel.getChildren().add(formLayout);
		GraphNode formLayoutNode = createGraphNode(formLayout, "default");

		formPanelNode.getContained().add(formLayoutNode);

		tabPanel = UIMFactory.eINSTANCE.createUIMTabPanel();
		formLayout.getChildren().add(tabPanel);
		tabPanelNode = createGraphNode(tabPanel, "default");
		tabPanelNode.setPosition(new Point(0, 0));
		formLayoutNode.getContained().add(tabPanelNode);

		// Create details Tab
		UIMTab tab = UIMFactory.eINSTANCE.createUIMTab();
		tab.setName("Edit Details");
		tabPanel.getChildren().add(tab);
		GraphNode tabNode = createGraphNode(tab, "default");
		tabPanelNode.getContained().add(tabNode);
		tabNode.setPosition(new Point(0, 0));

		// Create details tab XYLayout
		mainTabLayout = UIMFactory.eINSTANCE.createUIMXYLayout();

		tab.getChildren().add(mainTabLayout);
		mainTabLayoutNode = createGraphNode(mainTabLayout, "default");
		mainTabLayoutNode.setPosition(new Point(0, 0));
		tabNode.getContained().add(mainTabLayoutNode);
		int totalHeight = calculateHeight(typedElements);
		mainTabLayoutNode.setSize(new Dimension(400, totalHeight + 45));
		tabNode.setSize(new Dimension(400, totalHeight + 50));
		tabPanelNode.setSize(new Dimension(400, totalHeight + 60));
		formPanelNode.setSize(new Dimension(400, totalHeight + 75));
		addUserFields(typedElements);
	}

	private void addUserFields(Collection<? extends TypedElement> typedElements) {
		int currentY = 5;
		for (TypedElement property : typedElements) {
			if (requiresTableTab(property)) {
				addTableTabForField(property);
			} else if (requiresDetailsTab(property)) {
				addDetailsTabForField(property);
			} else {
				currentY += (3 + addUserField(mainTabLayout, mainTabLayoutNode,
						property, currentY, 390));
			}

		}
	}

	private void addDetailsTabForField(TypedElement e) {
		Classifier c = (Classifier) e.getType();
		// // Create tab and add to panel
		UIMTab tab = UIMFactory.eINSTANCE.createUIMTab();
		tab.setName(NameConverter.separateWords(e.getName()));
		GraphNode tabNode = createGraphNode(tab, "default");
		//
		// // Create tab XYLayout
		UIMXYLayout tabLayout = UIMFactory.eINSTANCE.createUIMXYLayout();

		tab.getChildren().add(tabLayout);
		GraphNode tabLayoutNode = createGraphNode(tabLayout, "default");
		tabLayoutNode.setSize(mainTabLayoutNode.getSize().getCopy());
		tabLayoutNode.setPosition(new Point(0, 0));
		tabNode.getContained().add(tabLayoutNode);
		int currentY = 5;
		for (TypedElement property : c.getAllAttributes()) {
			if (requiresTableTab(property)) {
				// No further details
			} else {
				currentY += (3 + addUserField(tabLayout, tabLayoutNode,
						property, currentY, 390));
			}

		}
		if (currentY > 5) {
			//Only add if there are contents on the tab - NB!!! Bug in topcased if empty  tabs are displayed
			tabPanel.getChildren().add(tab);
			tabPanelNode.getContained().add(tabNode);
		}
	}

	private void addTableTabForField(TypedElement e) {
		// Create tab and add to panel
		UIMTab tab = UIMFactory.eINSTANCE.createUIMTab();
		tab.setName(NameConverter.separateWords(e.getName()));
		tabPanel.getChildren().add(tab);
		GraphNode tabNode = createGraphNode(tab, "default");
		tabPanelNode.getContained().add(tabNode);

		// Create tab XYLayout
		UIMXYLayout tabLayout = UIMFactory.eINSTANCE.createUIMXYLayout();

		tab.getChildren().add(tabLayout);
		GraphNode tabLayoutNode = createGraphNode(tabLayout, "default");
		tabLayoutNode.setSize(mainTabLayoutNode.getSize().getCopy());

		tabNode.getContained().add(tabLayoutNode);

		// cfeate Data TAble
		UIMDataTable table = UIMFactory.eINSTANCE.createUIMDataTable();
		GraphNode tableNode = createGraphNode(table, "default");
		tableNode.setSize(new Dimension(mainTabLayoutNode.getSize().width,
				mainTabLayoutNode.getSize().height - 15));
		tabLayout.getChildren().add(table);
		tabLayoutNode.getContained().add(tableNode);
		EList<Property> attrs = ((Classifier) e.getType()).getAllAttributes();
		int x = 0;
		for (Property attr : attrs) {
			UIMDataColumn column = UIMFactory.eINSTANCE.createUIMDataColumn();
			GraphNode columnNode = createGraphNode(column, "default");
			column.setName(NameConverter.separateWords(attr.getName()));
			table.getChildren().add(column);
			tableNode.getContained().add(columnNode);
			final int COLUMN_WIDTH = 90;
			columnNode.setSize(new Dimension(COLUMN_WIDTH,
					tableNode.getSize().height - 15));
			columnNode.setPosition(new Point(x, 0));
			// CReate column xy layout
			UIMXYLayout columnLayout = UIMFactory.eINSTANCE.createUIMXYLayout();
			GraphNode columnLayoutNode = createGraphNode(columnLayout,
					"default");
			column.getChildren().add(columnLayout);
			columnLayoutNode.setSize(new Dimension(COLUMN_WIDTH, columnNode
					.getSize().height - 15));
			columnNode.getContained().add(columnLayoutNode);
			addUserField(columnLayout, columnLayoutNode, attr, 0,
					COLUMN_WIDTH - 15);
			x += COLUMN_WIDTH + 3;
		}
		// create fields
	}

	private int calculateHeight(Collection<? extends TypedElement> typedElements) {
		int height = 5;
		// TODO calculate the height of other tabs too
		for (TypedElement property : typedElements) {
			if (requiresTableTab(property)) {
			} else {
				height += (3 + (ControlUtil.isMultiRow(ControlUtil
						.getPreferredControlKind(property)) ? 200 : 25));
			}

		}
		for (TypedElement property : typedElements) {
			if (requiresDetailsTab(property)) {
				int detailsHeight = calculateHeight(((Classifier) property
						.getType()).getAllAttributes());
				if (detailsHeight > height) {
					height = detailsHeight;
				}
			}

		}
		return height;
	}

	private boolean requiresTableTab(TypedElement property) {
		return property.getType() instanceof org.eclipse.uml2.uml.Class
				&& UimUtil.isMany(property)
				&& !ControlUtil.requiresManySelection(property);
	}

	private boolean requiresDetailsTab(TypedElement property) {
		return property.getType() instanceof org.eclipse.uml2.uml.Class
				&& !UimUtil.isMany(property)
				&& !ControlUtil.requiresUserInput(property);
	}

	private int addUserField(UIMContainer layout, GraphNode layoutNode,
			TypedElement property, int y, int width) {
		UIMField uf = UIMFactory.eINSTANCE.createUIMField();
		uf.setLabelWidth(width < 100 ? 0 : width / 2);
		uf.setName(NameConverter.separateWords(property.getName()));
		ControlKind controlKind = ControlUtil.getPreferredControlKind(property);
		uf.setControlKind(controlKind);
		uf.setControl(ControlUtil.instantiate(uf.getControlKind()));
		FieldBinding binding = UIMFactory.eINSTANCE.createFieldBinding();
		uf.setBinding(binding);
		binding.setElement(property);
		// TODO lookupBinding
		layout.getChildren().add(uf);
		GraphNode userFieldNode = createGraphNode(uf, "default");
		int height = ControlUtil.isMultiRow(controlKind) ? 200 : 25;
		userFieldNode.setSize(new Dimension(width, height));
		userFieldNode.setPosition(new Point(3, y));
		layoutNode.getContained().add(userFieldNode);
		return height;
	}

	public void addButtonBar(ActionKind... updateCurrentEntity) {
		int x = 5;
		for (ActionKind actionKind : updateCurrentEntity) {

			BuiltInAction bia = UIMFactory.eINSTANCE.createBuiltInAction();
			bia.setKind(actionKind);
			bia.setName(NameConverter.separateWords(NameConverter
					.capitalize(actionKind.getName())));
			mainTabLayout.getChildren().add(bia);
			GraphNode builtInActionNode = createGraphNode(bia, "default");
			builtInActionNode.setSize(new Dimension(120, 20));
			builtInActionNode.setPosition(new Point(x, mainTabLayoutNode
					.getSize().height - 40));
			mainTabLayoutNode.getContained().add(builtInActionNode);
			x += 130;
		}
	}

}