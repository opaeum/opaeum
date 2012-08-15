package org.opaeum.uim.figures.controls;

import org.eclipse.draw2d.ArrowButton;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.GroupBoxBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RectangularDropShadowLineBorder;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UimMultiSelectFigure extends Figure implements IControlFigure{
	private Figure from;
	private Figure to;
	private Figure buttonBar;
	private Label bindingLabel;
	public UimMultiSelectFigure(){
		super();
		GridLayout manager = new GridLayout(3, false);
		setLayoutManager(manager);
		setBorder(new RectangularDropShadowLineBorder(1));
		minSize = new Dimension(100, 100);
		from = new Figure();
		from.setPreferredSize(150, 100);
		from.setBorder(new GroupBoxBorder("Available"));
		GridData fromGridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, true);
		add(from, fromGridData);
		add(buttonBar = new Figure(), new GridData(GridData.BEGINNING, GridData.CENTER, false, true));
		buttonBar.setPreferredSize(30, 100);
		buttonBar.setLayoutManager(new ToolbarLayout(false));
		ArrowButton east = new ArrowButton(ArrowButton.EAST);
		buttonBar.add(east);
		east.setPreferredSize(new Dimension(30, 30));
		ArrowButton west = new ArrowButton(ArrowButton.WEST);
		buttonBar.add(west);
		west.setPreferredSize(east.getPreferredSize().getCopy());
		ArrowButton north = new ArrowButton(ArrowButton.NORTH);
		buttonBar.add(north);
		north.setPreferredSize(east.getPreferredSize().getCopy());
		ArrowButton south = new ArrowButton(ArrowButton.SOUTH);
		buttonBar.add(south);
		south.setPreferredSize(east.getPreferredSize().getCopy());
		GridData toGridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, true);
		add(to = new Figure(), toGridData);
		to.setPreferredSize(from.getPreferredSize().getCopy());
		to.setBorder(new GroupBoxBorder("Selected"));
		to.setLayoutManager(new ToolbarLayout());
		bindingLabel = new Label();
		to.add(bindingLabel);
		addLayoutListener(new LayoutListener(){
			@Override
			public void invalidate(IFigure container){
				// TODO Auto-generated method stub
			}
			@Override
			public boolean layout(IFigure container){
				// GridData toConstraint=(GridData) getLayoutManager().getConstraint(from);
				// toConstraint.widthHint=(getSize().width-40)/2;
				// GridData fromConstraint=(GridData) getLayoutManager().getConstraint(from);
				// fromConstraint.widthHint=(getSize().width-40)/2;
				// fromConstraint.heightHint=getSize().height;
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public void postLayout(IFigure container){
				// TODO Auto-generated method stub
			}
			@Override
			public void remove(IFigure child){
				// TODO Auto-generated method stub
			}
			@Override
			public void setConstraint(IFigure child,Object constraint){
				// TODO Auto-generated method stub
			}
		});
	}
	@Override
	public ILabel getBindingLabel(){
		return bindingLabel;
	}
}
