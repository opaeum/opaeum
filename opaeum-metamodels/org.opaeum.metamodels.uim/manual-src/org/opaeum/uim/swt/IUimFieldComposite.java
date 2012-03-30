package org.opaeum.uim.swt;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public interface IUimFieldComposite{
	public abstract Control getControl();
	public abstract void setControl(Control control);
	public abstract Label getLabel();
	public abstract void layout();
	public abstract Composite getParent();
	public abstract void setBackground(Color cyan);
	public abstract Rectangle getBounds();
	public abstract void setLayout(GridLayout layout);
	public abstract void setData(String figure,Object customFieldColumnFigure);
}