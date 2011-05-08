package org.nakeduml.uim.figures;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.FontData;
import org.nakeduml.uim.figures.controls.GraphicsBridge;
import org.nakeduml.uim.figures.controls.UIMTextFigure;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;

public class UIMFieldFigure extends Figure implements ILabelFigure,
		IBindingFigure {
	// TODO implement labelHeight and a vertical layout
	private EditableLabel label;
	private JButton btn = new JButton();

	private IControlFigure control;
	private int labelWidth;

	public UIMFieldFigure() {
		this(2);
	}

	protected UIMFieldFigure(int noOfColumns) {
		super();
		getInsets().bottom = 0;
		getInsets().top = 0;
		getInsets().left = 0;
		getInsets().right = 0;

		UIMTextFigure newControl = new UIMTextFigure("Select Property");
		label = new EditableLabel();
		setControl(newControl);
	}

	public EditableLabel getLabel() {
		return label;
	}

	public IControlFigure getControl() {
		return control;

	}

	public int getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(int labelWidth) {
		this.labelWidth = labelWidth;
	}

	@Override
	public ILabel getBindingLabel() {
		return getControl().getBindingLabel();
	}

	public void setControl(IControlFigure control) {
		if (this.control != null) {
			remove(this.control);
			remove(this.label);
		}
		GridData controlGridData;
		GridLayout gl = null;
		if (control.getMinimumSize().height > 50) {
			gl = new GridLayout(1, false);
			controlGridData = new GridData(getSize().width, 50);
			controlGridData.grabExcessHorizontalSpace = true;
			controlGridData.grabExcessVerticalSpace = true;

		} else {
			gl = new GridLayout(2, false);
			controlGridData = new GridData(50, 50);
			controlGridData.grabExcessHorizontalSpace = true;
		}
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.verticalSpacing = 0;
		gl.horizontalSpacing = 0;
		setLayoutManager(gl);
		add(this.label, new GridData(50, 20));
		add(this.control = control, controlGridData);
		addLayoutListener(new LayoutListener() {
			@Override
			public void invalidate(IFigure arg0) {
			}

			@Override
			public boolean layout(IFigure arg0) {
				GridData constraint = (GridData) getLayoutManager()
						.getConstraint(getLabel());
				if (getParent() instanceof UIMDataColumnFigure) {
					// TODO DOESnt work anymore!!!!
					constraint.widthHint = 0;
					constraint.heightHint = 0;
				} else {
					constraint.widthHint = labelWidth;
				}
				// constraint.heightHint=fig.getSize().height;
				GridData constraint2 = (GridData) getLayoutManager()
						.getConstraint(getControl());
				if (((GridLayout) getLayoutManager()).numColumns == 2) {
					constraint2.widthHint = getSize().width
							- constraint.widthHint;
					constraint2.heightHint = getSize().height;
				} else {
					constraint2.widthHint = getSize().width;
					constraint2.heightHint = getSize().height-30;
				}
				return false;
			}

			@Override
			public void postLayout(IFigure arg0) {
			}

			@Override
			public void remove(IFigure arg0) {
			}

			@Override
			public void setConstraint(IFigure arg0, Object arg1) {
			}

		});

	}
	@Override
	public void paint(final Graphics g){
		 super.paint(g);
//		GraphicsBridge g2 = new GraphicsBridge(g);
//		JTabbedPane tabbedPane = new JTabbedPane();
//		tabbedPane.addTab("Tab1", new JPanel());
//		Rectangle b = getBounds();
//		tabbedPane.setBounds(new java.awt.Rectangle(b.x+5, b.y+5, b.width-10, b.height-10));
//		tabbedPane.doLayout();
//		FontData fontData = getFont().getFontData()[0];
//		tabbedPane.setFont(new Font(fontData.getName(),fontData.getStyle(), fontData.getHeight()));
//		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED,new Color(200,200,200),new Color(100,100,100)));
//		tabbedPane.paint(g2.create(b.x+5, b.y+5, b.width-10, b.height-10));
//		super.paint(g);
	}

	private void paintTree(final Graphics g){
	}

}
