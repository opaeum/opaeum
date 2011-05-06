package org.nakeduml.uim.figures;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.ToolbarLayout;
import org.nakeduml.uim.layouts.StackLayout;
import org.topcased.draw2d.figures.ContainerFigure;
import org.topcased.draw2d.figures.ILabel;

public class UIMTabPanelFigure extends ContainerFigure {
	IFigure tabPane;
	int selectedIndex;
	private StackLayout stackLayout;

	public UIMTabPanelFigure() {
		tabPane = new Figure();
		ToolbarLayout tbl = new ToolbarLayout(true);
		tbl.setSpacing(12);
		tabPane.setLayoutManager(tbl);
		super.add(tabPane, new GridData(GridData.GRAB_HORIZONTAL), 1);
		super.getContentPane().setBorder(null);
	}

	protected IFigure createContainer() {
		Figure container = new Figure() {
			@Override
			public void add(final IFigure figure, Object constraint,
					final int index) {
				super.add(figure, constraint, index);
				if (figure instanceof UIMTabFigure) {
					selectedIndex = index;
					ILabel label = ((UIMTabFigure) figure).getLabel();
					tabPane.add(label, index);
					label.addMouseListener(new MouseListener() {

						@Override
						public void mouseDoubleClicked(MouseEvent me) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent me) {
							setActiveTabIndex(index);

						}

						@Override
						public void mouseReleased(MouseEvent me) {
							// TODO Auto-generated method stub

						}

					});
				}
			}

			@Override
			public void remove(IFigure figure) {
				super.remove(figure);
				if (figure instanceof UIMTabFigure) {
					ILabel label = ((UIMTabFigure) figure).getLabel();
					tabPane.remove(label);
					label.invalidate();
					tabPane.invalidate();
				}
			}

		};
		stackLayout = new StackLayout();
		container.setLayoutManager(stackLayout);
		container.setOpaque(true);
		return container;
	}

	public void setActiveTabIndex(int index) {
		final List<Figure> tabs = getContentPane().getChildren();
		final List<Figure> tabLabels = tabPane.getChildren();
		for (int i = 0; i < tabs.size(); i++) {
			if (i == index) {
				tabLabels.get(i).setBackgroundColor(ColorConstants.lightGray);
			} else {
				tabLabels.get(i).setBackgroundColor(ColorConstants.white);
			}
		}
		stackLayout.activeChild = index;
		invalidateTree();
		revalidate();
	}

}
