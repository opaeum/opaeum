package org.opeum.uml2uim;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.opeum.uim.UimComponent;
import org.opeum.uim.UimDataTable;
import org.opeum.uim.UimField;
import org.opeum.uim.UimTab;
import org.opeum.uim.UimTabPanel;
import org.opeum.uim.UserInteractionElement;
import org.opeum.uim.action.UimAction;
import org.opeum.uim.form.FormPanel;
import org.opeum.uim.layout.LayoutContainer;
import org.opeum.uim.layout.OutlayableComponent;
import org.opeum.uim.layout.UimColumnLayout;
import org.opeum.uim.layout.UimFullLayout;
import org.opeum.uim.layout.UimGridLayout;
import org.opeum.uim.layout.UimLayout;
import org.opeum.uim.layouts.FullLayoutManager;
import org.opeum.uim.layouts.GridLayout;
import org.opeum.uim.layouts.IUimLayoutManager;
import org.opeum.uim.layouts.UimColumnLayoutManager;
import org.opeum.uim.util.ControlUtil;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphNode;

public class DiagramCreator{
	private UimGridLayout mainTabLayout;
	private UimTabPanel tabPanel;
	private FormPanel formPanel;
	private Diagram diag;
	public DiagramCreator(FormPanel cf,Diagram diag){
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
	void createDiagram(){
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
		}else if(c instanceof UimLayout){
			currentNode.setPosition(new Point(0, 0));
			currentNode.setSize(new Dimension(parentNode.getSize().width, parentNode.getSize().height));
			if(c instanceof UimGridLayout){
				UimGridLayout gridLayout = (UimGridLayout) c;
				doLayout(currentNode, gridLayout, new GridLayout(gridLayout.getNumberOfColumns()));
			}else if(c instanceof UimFullLayout){
				doLayout(currentNode, ((UimFullLayout) c), new FullLayoutManager());
			}else if(c instanceof UimColumnLayout){
				currentNode.setPosition(new Point(1, 31));
				currentNode.setSize(new Dimension(calculateWidth(c), parentNode.getSize().height - 32));
				doLayout(currentNode, (UimColumnLayout)c,new UimColumnLayoutManager());
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
			EList<OutlayableComponent> columns = ((UimDataTable) c).getLayout().getChildren();
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