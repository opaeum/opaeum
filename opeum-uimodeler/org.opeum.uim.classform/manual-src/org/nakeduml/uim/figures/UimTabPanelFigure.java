package org.opeum.uim.figures;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.accessibility.AccessibleComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.FontData;
import org.opeum.uim.figures.controls.GraphicsBridge;
import org.opeum.uim.layouts.StackLayout;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;

public class UimTabPanelFigure extends Figure implements IContainerFigure,ILabelFigure{
	@Override
	public void setFont(org.eclipse.swt.graphics.Font f){
		super.setFont(f);
		repaint();
	}
	IFigure contentPane;
	JTabbedPane tabbedPane = new JTabbedPane();
	int selectedIndex;
	private StackLayout stackLayout;
	private EditableLabel label;
	public UimTabPanelFigure(){
		
		setLayoutManager(new AbstractLayout(){
			@Override
			public void layout(IFigure arg0){
				Rectangle tpb = getBounds();
				FontData lb = label.getFont().getFontData()[0];
				int labelHeight = Math.round(lb.height*2.4f);
				label.setBounds(new Rectangle(tpb.x+tpb.width-100, tpb.y + 2, 100, labelHeight));
				label.setAlignment(PositionConstants.RIGHT);
				contentPane.setBounds(new Rectangle(tpb.x + 4, tpb.y + labelHeight+4, tpb.width - 8, tpb.height - labelHeight-8));
			}
			@Override
			protected Dimension calculatePreferredSize(IFigure arg0,int arg1,int arg2){
				return new Dimension(getBounds().width, getBounds().height - 30);
			}
		});
		label = new EditableLabel(){
			@Override
			public void setFont(org.eclipse.swt.graphics.Font f){
				super.setFont(f);
				UimTabPanelFigure.this.layout();
				UimTabPanelFigure.this.repaint();
			}
		};
		label.setOpaque(false);
		contentPane = createContainer();
		// contentPane.setBorder(new LineBorder(4));
		// GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, GridData.VERTICAL_ALIGN_BEGINNING, true, true);
		// add(contentPane, gridData);
		add(contentPane);
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClicked(MouseEvent me){
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent me){
				for(int i = 0;i < tabbedPane.getAccessibleContext().getAccessibleChildrenCount();i++){
					AccessibleComponent page = (AccessibleComponent) tabbedPane.getAccessibleContext().getAccessibleChild(i);
					java.awt.Rectangle awtBounds = page.getBounds();
					Object constr = getParent().getLayoutManager().getConstraint(UimTabPanelFigure.this);
					Rectangle swtBounds = getBounds();
					Rectangle intersect = new Rectangle(awtBounds.x + swtBounds.x, awtBounds.y + swtBounds.y, awtBounds.width, awtBounds.height);
					if(intersect.contains(me.getLocation())){
						setActiveTabIndex(i);
						UimTabFigure c= (UimTabFigure) contentPane.getChildren().get(i);
						break;
					}
				}
			}
			@Override
			public void mouseReleased(MouseEvent me){
			}
		});
	}
	public void layout(){
		List<UimTabFigure> children = contentPane.getChildren();
		for(int i = 0;i < children.size();i++){
			UimTabFigure f=children.get(i);
			Rectangle rectangle = GraphicsBridge.convert(tabbedPane.getBoundsAt(i));
			f.setLabelBounds(rectangle);
		}
		super.layout();
	}
	protected IFigure createContainer(){
		stackLayout = new StackLayout();
		IFigure container = new Figure(){
			public void setFont(org.eclipse.swt.graphics.Font f){
				super.setFont(f);
			}
			@Override
			public void remove(IFigure figure){
				if(figure instanceof UimTabFigure){
					UimTabFigure t = (UimTabFigure) figure;
					tabbedPane.remove(this.getChildren().indexOf(figure));
				}
				super.remove(figure);
			}
			@Override
			public void add(IFigure figure,Object constraint,int index){
				super.add(figure, constraint, index);
				if(figure instanceof UimTabFigure){
					UimTabFigure t = (UimTabFigure) figure;
					tabbedPane.addTab(t.getLabel().getText(), new JPanel());
					repaint();
				}
			}
		};
		container.setLayoutManager(stackLayout);
		container.setOpaque(false);
		return container;
	}
	public void setActiveTabIndex(int index){
		final List<Figure> tabs = contentPane.getChildren();
		final List<UimTabFigure> tabLabels = contentPane.getChildren();
		for(int i = 0;i < tabs.size();i++){
			// if (i == index) {
			// tabLabels.get(i).setBackgroundColor(ColorConstants.lightGray);
			// } else {
			// tabLabels.get(i).setBackgroundColor(ColorConstants.white);
			// }
		}
	
		stackLayout.activeChild = index;
		this.selectedIndex = index;
		tabLabels.get(index).selected();
		invalidateTree();
		revalidate();
		super.repaint();
	}
	@Override
	public void paint(Graphics g){
		GraphicsBridge g2 = new GraphicsBridge(g);
		List<UimTabFigure> children = contentPane.getChildren();
		for(int i = 0;i < children.size();i++){
			tabbedPane.setTitleAt(i, children.get(i).getLabel().getText());
		}
		Rectangle b = getBounds();
		tabbedPane.setBounds(new java.awt.Rectangle(b.x + 1, b.y + 1, b.width - 2, b.height - 2));
		FontData fontData = getLabel().getFont().getFontData()[0];
		tabbedPane.setFont(new Font(fontData.getName(), fontData.getStyle(), Math.round(fontData.height*1.4f)));
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(200, 200, 200), new Color(100, 100, 100)));
		if(selectedIndex < tabbedPane.getComponentCount()){
			tabbedPane.setSelectedIndex(selectedIndex);
		}
		tabbedPane.doLayout();
		tabbedPane.paint(g2.create(b.x + 1, b.y + 1, b.width - 2, b.height - 2));
		g.popState();
		super.paintChildren(g);
	}
	@Override
	public IFigure getContentPane(){
		return contentPane;
	}
	@Override
	public ILabel getLabel(){
		return this.label;
	}
}
