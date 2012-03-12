package org.opaeum.uim.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;

public class CustomLinkToEntityColumnFigure extends CustomFieldColumnFigure{
	private Link link;
	public CustomLinkToEntityColumnFigure(Composite comp){
		super(comp);
	}
	@Override
	protected Control createDefaultControl(){
		link = new Link(getComposite(), SWT.BORDER);
		link.setForeground(ColorConstants.blue);
		link.setText("<Object Name>");
		return link;
	}
}
