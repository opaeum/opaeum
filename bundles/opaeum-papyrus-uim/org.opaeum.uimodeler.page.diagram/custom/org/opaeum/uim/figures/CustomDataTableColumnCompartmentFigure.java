package org.opaeum.uim.figures;

import org.eclipse.draw2d.StackLayout;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.uimodeler.common.figures.UimDataTableComposite;

public final class CustomDataTableColumnCompartmentFigure extends ShapeCompartmentFigure{
	UimDataTableComposite parent;
	public CustomDataTableColumnCompartmentFigure(String title,IMapMode mm, Composite parent){
		super(title, mm);
		this.parent=(UimDataTableComposite) parent;
		remove(getTextPane());
		remove(scrollPane);
		setLayoutManager(new StackLayout());
		add(scrollPane);
		setOpaque(false);
		setBorder(null);
	}
}