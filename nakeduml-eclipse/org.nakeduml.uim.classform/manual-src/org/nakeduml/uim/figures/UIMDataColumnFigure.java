package org.nakeduml.uim.figures;


import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.LineBorder;
import org.nakeduml.uim.layouts.StackLayout;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.IContainerFigure;
import org.topcased.draw2d.figures.ILabelFigure;

//TODO  - change to container, allow addition of userfields and navigations
public class UIMDataColumnFigure extends Figure implements IContainerFigure,
		ILabelFigure {
	private EditableLabel label;
	private Figure contentPane;

	public UIMDataColumnFigure() {
		super();
		GridLayout xyLayout = new GridLayout(1, false);
		setLayoutManager(xyLayout);
		add(this.label = new EditableLabel(), new GridData(50, 20));
        label.setBorder(new LineBorder(1));
		GridData fieldGridData = new GridData();
		fieldGridData.grabExcessHorizontalSpace = true;
		fieldGridData.grabExcessVerticalSpace = true;
        Figure container = new Figure();
        container.setLayoutManager(new StackLayout());
        container.setOpaque(true);
        container.setBorder(new LineBorder(1));
		add(this.contentPane = container, fieldGridData);
		addLayoutListener(new LayoutListener() {
			@Override
			public void invalidate(IFigure arg0) {
			}
			@Override
			public boolean layout(IFigure arg0) {
				GridData constraint = (GridData) getLayoutManager()
						.getConstraint(getLabel());
				constraint.widthHint = getSize().width-8;
				// constraint.heightHint=fig.getSize().height;
				GridData constraint2 = (GridData) getLayoutManager()
						.getConstraint(getContentPane());
				constraint2.widthHint = getSize().width-8;
				constraint2.heightHint = getSize().height - 36;
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

	public EditableLabel getLabel() {
		return label;
	}

	@Override
	public IFigure getContentPane() {
		return contentPane;
	}

}
