package org.opeum.uim.figures;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.opeum.uim.figure.TabListener;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.ILabelFigure;

public class UimTabFigure extends Figure implements IContainerFigure,ILabelFigure{
	private Rectangle labelBounds;
	private EditableLabel label;
	private Figure contentPane;
	private Collection<TabListener> tabListeners = new ArrayList<TabListener>();
	public UimTabFigure(){
		setBackgroundColor(ColorConstants.darkGreen);
		super.setLayoutManager(new AbstractLayout(){
			@Override
			public void layout(IFigure container){
				label.setBounds(labelBounds);
				Rectangle bounds2 = container.getBounds().getCopy();
				bounds2.x = bounds2.x + 1;
				bounds2.y = bounds2.y + 1;
				bounds2.height = bounds2.height - 3;
				bounds2.width = bounds2.width - 3;
				contentPane.setBounds(bounds2);
			}
			@Override
			protected Dimension calculatePreferredSize(IFigure container,int wHint,int hHint){
				return container.getPreferredSize(wHint, hHint);
			}
		});
		add(label = new EditableLabel());
		add(contentPane = new Figure());
		contentPane.setOpaque(true);
		contentPane.setBorder(new LineBorder(1));
		contentPane.setLayoutManager(new org.opeum.uim.layouts.StackLayout());
	}
	public void setLabelBounds(Rectangle rectangle){
		this.labelBounds = rectangle;
		this.labelBounds.y = getBounds().y - rectangle.height - 3;
		this.labelBounds.x = rectangle.x + 4;
	}
	@Override
	public ILabel getLabel(){
		return label;
	}
	@Override
	public IFigure getContentPane(){
		return contentPane;
	}
	public void addTabListener(TabListener tabListener){
		tabListeners.add(tabListener);
	}
	public void selected(){
		for(TabListener tabListener:this.tabListeners){
			tabListener.tabSelected(this);
		}
	}
}
