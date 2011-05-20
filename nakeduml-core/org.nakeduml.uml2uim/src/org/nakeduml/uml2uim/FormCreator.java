package org.nakeduml.uml2uim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
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
import org.nakeduml.uim.LayoutContainer;
import org.nakeduml.uim.OutlayableComponent;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.TableBinding;
import org.nakeduml.uim.UimAction;
import org.nakeduml.uim.UimComponent;
import org.nakeduml.uim.UimDataColumn;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimFullLayout;
import org.nakeduml.uim.UimGridLayout;
import org.nakeduml.uim.UimLayout;
import org.nakeduml.uim.UimPanel;
import org.nakeduml.uim.UimTab;
import org.nakeduml.uim.UimTabPanel;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.layouts.FullLayoutManager;
import org.nakeduml.uim.layouts.GridLayout;
import org.nakeduml.uim.layouts.IUimLayoutManager;
import org.nakeduml.uim.modeleditor.SafeUmlUimLinks;
import org.nakeduml.uim.util.ControlUtil;
import org.nakeduml.uim.util.UimUtil;
import org.nakeduml.uim.util.UmlUimLinks;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphNode;

public class FormCreator{
	private UimGridLayout mainTabLayout;
	private UimTabPanel tabPanel;
	private FormPanel formPanel;
	private Diagram diag;
	public FormCreator(FormPanel cf,Diagram diag){
		this.formPanel = cf;
		this.diag = diag;
	}
	protected GraphNode createGraphNode(GraphNode parent,UserInteractionElement model,String presentation){
		GraphNode node = DiagramInterchangeFactory.eINSTANCE.createGraphNode();
		EMFSemanticModelBridge bridge = DiagramInterchangeFactory.eINSTANCE.createEMFSemanticModelBridge();
		bridge.setElement(model);
		bridge.setPresentation(presentation);
		node.setSemanticModel(bridge);
		parent.getContained().add(node);
		return node;
	}
	public void prepareFormPanel(String title,Collection<? extends TypedElement> typedElements){
		UimFullLayout formLayout = UimFactory.eINSTANCE.createUimFullLayout();
		formPanel.setLayout(formLayout);
		tabPanel = UimFactory.eINSTANCE.createUimTabPanel();
		formLayout.getChildren().add(tabPanel);
		UimTab tab = UimFactory.eINSTANCE.createUimTab();
		tab.setName("Edit Details");
		tabPanel.getChildren().add(tab);
		mainTabLayout = UimFactory.eINSTANCE.createUimGridLayout();
		mainTabLayout.setNumberOfColumns(1);
		tab.setLayout(mainTabLayout);
		addUserFields(typedElements);
	}
	private void addUserFields(Collection<? extends TypedElement> typedElements){
		for(TypedElement property:typedElements){
			if(property instanceof Property && ((Property) property).getOtherEnd() != null && ((Property) property).getOtherEnd().isComposite()){
			}else if(requiresTableTab(property)){
				addTableTabForField(property);
			}else if(requiresDetailsTab(property)){
				addDetailsTabForField(property);
			}else{
				addUserField(mainTabLayout, 390, property);
			}
		}
	}
	private void addDetailsTabForField(TypedElement e){
		Classifier c = (Classifier) e.getType();
		// // Create tab and add to panel
		UimTab tab = UimFactory.eINSTANCE.createUimTab();
		this.tabPanel.getChildren().add(tab);
		tab.setName(NameConverter.separateWords(e.getName()));
		UimGridLayout tabLayout = UimFactory.eINSTANCE.createUimGridLayout();
		tabLayout.setNumberOfColumns(1);
		tab.setLayout(tabLayout);
		for(Property property:SafeUmlUimLinks.getInstance(e).getOwnedAttributes(c)){
			if(requiresTableTab(property)){
				// No further details
			}else if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
				addUserField(tabLayout, 390, e, property);
			}
		}
	}
	private void addTableTabForField(TypedElement e){
		// Create tab and add to panel
		UimTab tab = UimFactory.eINSTANCE.createUimTab();
		tab.setName(NameConverter.separateWords(e.getName()));
		tabPanel.getChildren().add(tab);
		// Create tab XYLayout
		UimFullLayout tabLayout = UimFactory.eINSTANCE.createUimFullLayout();
		tab.setLayout(tabLayout);
		// cfeate Data TAble
		UimDataTable table = UimFactory.eINSTANCE.createUimDataTable();
		TableBinding binding = UimFactory.eINSTANCE.createTableBinding();
		table.setBinding(binding);
		binding.setUmlElementUid(UmlUimLinks.getId(e));
		tabLayout.getChildren().add(table);
		Collection<Property> attrs = SafeUmlUimLinks.getInstance(e).getOwnedAttributes((Classifier) e.getType());
		for(Property property:attrs){
			if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
				UimDataColumn column = UimFactory.eINSTANCE.createUimDataColumn();
				column.setName(NameConverter.separateWords(property.getName()));
				table.getChildren().add(column);
				// CReate column xy layout
				UimGridLayout columnLayout = UimFactory.eINSTANCE.createUimGridLayout();
				columnLayout.setNumberOfColumns(1);
				column.setLayout(columnLayout);
				addUserField(columnLayout, 0, property);
			}
		}
		// create fields
	}
	private boolean requiresTableTab(TypedElement property){
		return property.getType() instanceof org.eclipse.uml2.uml.Class && UimUtil.isMany(property) && !ControlUtil.requiresManySelection(formPanel, property);
	}
	private boolean requiresDetailsTab(TypedElement property){
		return property.getType() instanceof org.eclipse.uml2.uml.Class && !UimUtil.isMany(property)
				&& (!ControlUtil.requiresUserInput(formPanel, property) || UimUtil.isComposite(property));
	}
	private int addUserField(UimLayout layout,int labelWidth,TypedElement...properties){
		TypedElement property = properties[properties.length - 1];
		UimField uf = UimFactory.eINSTANCE.createUimField();
		uf.setLabelWidth(labelWidth < 100 ? 0 : labelWidth / 2);
		uf.setName(NameConverter.separateWords(property.getName()));
		ControlKind controlKind = ControlUtil.getPreferredControlKind(formPanel, property);
		uf.setControlKind(controlKind);
		uf.setControl(ControlUtil.instantiate(uf.getControlKind()));
		FieldBinding binding = UimFactory.eINSTANCE.createFieldBinding();
		uf.setBinding(binding);
		binding.setUmlElementUid(UmlUimLinks.getId(properties[0]));
		if(properties.length > 1){
			PropertyRef prev = UimFactory.eINSTANCE.createPropertyRef();
			prev.setUmlElementUid(UmlUimLinks.getId(properties[1]));
			binding.setNext(prev);
			for(int i = 2;i < properties.length;i++){
				PropertyRef next = UimFactory.eINSTANCE.createPropertyRef();
				next.setUmlElementUid(UmlUimLinks.getId(properties[i]));
				prev.setNext(next);
				prev = next;
			}
		}
		// TODO lookupBinding
		layout.getChildren().add(uf);
		int height = ControlUtil.isMultiRow(controlKind) ? 200 : 25;
		return height;
	}
	public void addButtonBar(ActionKind...updateCurrentEntity){
		UimPanel panel = UimFactory.eINSTANCE.createUimPanel();
		this.mainTabLayout.getChildren().add(panel);
		UimGridLayout gl = UimFactory.eINSTANCE.createUimGridLayout();
		panel.setLayout(gl);
		gl.setNumberOfColumns(updateCurrentEntity.length);
		for(ActionKind actionKind:updateCurrentEntity){
			BuiltInAction bia = UimFactory.eINSTANCE.createBuiltInAction();
			bia.setKind(actionKind);
			bia.setName(NameConverter.separateWords(NameConverter.capitalize(actionKind.getName())));
			gl.getChildren().add(bia);
		}
		createDiagram();
	}
	private void createDiagram(){
		GraphNode gn = this.diag;
		UimFullLayout l = (UimFullLayout) formPanel.getLayout();
		UimComponent tabPanel = l.getChildren().get(0);
		gn.setSize(new Dimension(calculateWidth(tabPanel), calculateHeight(tabPanel)));
		populate(gn, l);
	}
	public void populate(GraphNode parentNode,UimComponent c){
		// populates all components that are not contained by a layout manager
		GraphNode currentNode = createGraphNode(parentNode, c, "default");
		if(c instanceof UimTab){
			UimLayout layout = ((UimTab) c).getLayout();
			currentNode.setPosition(new Point(1, 31));
			currentNode.setSize(new Dimension(parentNode.getSize().width - 2, parentNode.getSize().height - 32));
			if(layout != null){
				populate(currentNode, layout);
			}
		}else if(c instanceof UimDataColumn){
			UimLayout layout = ((UimDataColumn) c).getLayout();
			currentNode.setPosition(new Point(1, 31));
			currentNode.setSize(new Dimension(calculateWidth(c), parentNode.getSize().height - 32));
			if(layout != null){
				populate(currentNode, layout);
			}
		}else if(c instanceof UimLayout){
			currentNode.setPosition(new Point(0, 0));
			currentNode.setSize(new Dimension(parentNode.getSize().width, parentNode.getSize().height));
			if(c instanceof UimGridLayout){
				UimGridLayout gridLayout = (UimGridLayout) c;
				doLayout(currentNode, gridLayout, new GridLayout(gridLayout.getNumberOfColumns()));
			}else if(c instanceof UimFullLayout){
				doLayout(currentNode, ((UimFullLayout) c), new FullLayoutManager());
			}
		}
	}
	private void doLayout(GraphNode parentNode,UimLayout gridLayout,IUimLayoutManager layout){
		List<Rectangle> rs = new ArrayList<Rectangle>();
		int j = 0;
		for(UimComponent child:gridLayout.getChildren()){
			Rectangle r = new Rectangle(++j, j, 0, 0);
			r.height = calculateHeight(child);
			r.width = calculateWidth(child);
			rs.add(r);
		}
		layout.layout(rs, parentNode.getSize());
		for(int i = 0;i < rs.size();i++){
			UimComponent child = gridLayout.getChildren().get(i);
			GraphNode gn = createGraphNode(parentNode, child, "default");
			Rectangle rect = rs.get(i);
			gn.setPosition(new Point(rect.x, rect.y));
			gn.setSize(rect.getSize());
			if(child instanceof LayoutContainer){
				populate(gn, ((LayoutContainer) child).getLayout());
			}else if(child instanceof UimLayout){
				populateChildren(gn, ((UimLayout) child).getChildren());
			}else if(child instanceof UimTabPanel){
				populateChildren(gn, ((UimTabPanel) child).getChildren());
			}else if(child instanceof UimDataTable){
				populateChildren(gn, ((UimDataTable) child).getChildren());
			}
		}
	}
	public void populateChildren(GraphNode gn,EList<? extends UimComponent> children){
		for(UimComponent c:children){
			populate(gn, c);
		}
	}
	private int calculateHeight(UimComponent c){
		int height = 0;
		if(c instanceof UimGridLayout){
			UimGridLayout l = (UimGridLayout) c;
			EList<OutlayableComponent> children = l.getChildren();
			int rowHeight = 0;
			for(int i = 0;i < children.size();i++){
				if(i % l.getNumberOfColumns() == 0 || i == children.size() - 1){
					height += rowHeight;
					height += 2;
					rowHeight = 0;
				}
				rowHeight = Math.max(rowHeight, calculateHeight(children.get(i)));
			}
		}
		if(c instanceof UimDataTable){
			height = 400;
		}
		if(c instanceof LayoutContainer){
			height = calculateLayoutHeight(height, ((LayoutContainer) c).getLayout());
		}
		if(c instanceof FormPanel){
			height = calculateLayoutHeight(height, ((FormPanel) c).getLayout());
		}
		if(c instanceof UimTabPanel){
			EList<UimTab> children = ((UimTabPanel) c).getChildren();
			for(UimTab tab:children){
				int calculatedHeight = calculateHeight(tab);
				height = Math.max(calculatedHeight, height);
			}
			height = height + 30;
		}
		if(c instanceof UimField){
			height = ControlUtil.getPreferredHeight(((UimField) c).getControlKind());
		}
		if(c instanceof UimTab){
			height = calculateLayoutHeight(height, ((UimTab) c).getLayout());
		}
		if(c instanceof UimAction){
			height = 35;
		}
		return height;
	}
	private int calculateLayoutHeight(int height,UimLayout layout){
		if(layout instanceof UimGridLayout){
			height = calculateHeight(layout);
		}
		return height;
	}
	private int calculateWidth(UimComponent c){
		int width = 0;
		if(c instanceof UimGridLayout){
			UimGridLayout l = (UimGridLayout) c;
			EList<OutlayableComponent> children = l.getChildren();
			int rowWidth = 0;
			for(int i = 0;i < children.size();i++){
				if(i % l.getNumberOfColumns() == 0 || i == children.size() - 1){
					width = Math.max(rowWidth + 2, width);
					rowWidth = 0;
				}
				rowWidth += calculateWidth(children.get(i));
			}
			width = Math.max(rowWidth + 2, width);
		}
		if(c instanceof UimDataTable){
			EList<UimDataColumn> columns = ((UimDataTable) c).getChildren();
			for(UimComponent uimComponent:columns){
				width += calculateWidth(uimComponent);
				width += 2;
			}
		}
		if(c instanceof LayoutContainer){
			// TAb, Detail and Column,FormPanel
			width = calculateLayoutWidth(width, ((LayoutContainer) c).getLayout());
		}
		if(c instanceof UimTabPanel){
			EList<UimTab> children = (EList) ((UimTabPanel) c).getChildren();
			for(UimTab tab:children){
				int calculatedWidth = calculateWidth(tab);
				width = Math.max(calculatedWidth + 2, width);
			}
		}
		if(c instanceof UimField){
			UimField field = (UimField) c;
			width = ControlUtil.getPreferredWidth(field.getControlKind()) + field.getLabelWidth() + 2;
		}
		if(c instanceof UimAction){
			width = 80;
		}
		return width;
	}
	private int calculateLayoutWidth(int width,UimLayout layout){
		if(layout instanceof UimGridLayout){
			width = calculateWidth(layout);
		}
		return width;
	}
}