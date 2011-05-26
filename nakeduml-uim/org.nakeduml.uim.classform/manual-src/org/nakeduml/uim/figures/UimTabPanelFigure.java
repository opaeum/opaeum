package org.nakeduml.uim.figures;

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
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.FontData;
import org.nakeduml.uim.figures.controls.GraphicsBridge;
import org.nakeduml.uim.layouts.StackLayout;
import org.topcased.draw2d.figures.IContainerFigure;

public class UimTabPanelFigure extends Figure implements IContainerFigure{
	@Override
	public void setFont(org.eclipse.swt.graphics.Font f){
		super.setFont(f);
		repaint();
	}
	IFigure contentPane;
	JTabbedPane tabbedPane = new JTabbedPane();
	int selectedIndex;
	private StackLayout stackLayout;
	public UimTabPanelFigure(){
		setLayoutManager(new AbstractLayout(){
			@Override
			public void layout(IFigure arg0){
				Rectangle b = getBounds();
				contentPane.setBounds(new Rectangle(b.x + 4, b.y + 22, b.width - 8, b.height - 30));
			}
			@Override
			protected Dimension calculatePreferredSize(IFigure arg0,int arg1,int arg2){
				return new Dimension(getBounds().width, getBounds().height - 30);
			}
		});
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
						break;
					}
				}
			}
			@Override
			public void mouseReleased(MouseEvent me){
			}
		});
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
		final List<Figure> tabLabels = contentPane.getChildren();
		for(int i = 0;i < tabs.size();i++){
			// if (i == index) {
			// tabLabels.get(i).setBackgroundColor(ColorConstants.lightGray);
			// } else {
			// tabLabels.get(i).setBackgroundColor(ColorConstants.white);
			// }
		}
		stackLayout.activeChild = index;
		this.selectedIndex = index;
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
		FontData fontData = getFont().getFontData()[0];
		tabbedPane.setFont(new Font(fontData.getName(), fontData.getStyle(), fontData.getHeight() + 2));
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
}
