package org.nakeduml.uim.figures.controls;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.FontData;
import org.nakeduml.uim.figures.IControlFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UIMTreeFigure extends Figure implements IControlFigure{
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("1");
	JTree tree = new JTree(root, false);
	public UIMTreeFigure(){
		DefaultMutableTreeNode node1_1 = new DefaultMutableTreeNode("1.1");
		root.add(node1_1);
		DefaultMutableTreeNode node1_1_1 = new DefaultMutableTreeNode("1.1.1");
		node1_1.add(node1_1_1);
		root.add(new DefaultMutableTreeNode("1.2"));
		root.add(new DefaultMutableTreeNode("1.3"));
		tree.setSelectionPath(new TreePath(new Object[]{root,node1_1,node1_1_1}));
		tree.setBackground(new Color(255,255,255));

	}
	@Override
	public ILabel getBindingLabel(){
		// TODO Auto-generated method stub
		return new Label(){
			public void setText(String s){
				root.setUserObject(s);
			}
		};
	}
	public void paint(Graphics g){
		GraphicsBridge g2 = new GraphicsBridge(g);
		FontData fontData = getFont().getFontData()[0];
		tree.setFont(new Font(fontData.getName(),fontData.getStyle(), fontData.getHeight()));
		tree.setBorder(new BevelBorder(BevelBorder.RAISED,new Color(200,200,200),new Color(100,100,100)));
		Rectangle b = getBounds();
		tree.setBounds(new java.awt.Rectangle(b.x+5, b.y+5, b.width-10, b.height-10));
		tree.doLayout();
		tree.paint(g2.create(b.x+5, b.y+5, b.width-10, b.height-10));
		g.popState();
	}
}
