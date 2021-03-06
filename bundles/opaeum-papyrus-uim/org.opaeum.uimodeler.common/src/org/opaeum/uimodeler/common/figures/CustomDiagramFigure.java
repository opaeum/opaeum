package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uimodeler.common.UimFigureUtil;

public final class CustomDiagramFigure extends BorderItemsAwareFreeFormLayer implements ISWTFigure{
	private DiagramComposite composite;
	public CustomDiagramFigure(){
		super();
		composite = new DiagramComposite(UimFigureUtil.getFakeShell(), SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setData(UimFigureUtil.FIGURE, this);
	}
	@Override
	public boolean isMirrored(){
		try{
			return super.isMirrored();
		}catch(Exception e){
			return false;
		}
	}
	public boolean containsPoint(int x,int y){
		return getBounds().contains(x, y);
	}
	public IFigure findFigureAt(int x,int y,TreeSearch search){
		if(!isEnabled())
			return null;
		if(!containsPoint(x, y))
			return null;
		if(search.prune(this))
			return null;
		IFigure child = findDescendantAtExcluding(x, y, search);
		if(child != null)
			return child;
		if(search.accept(this))
			return this;
		return null;
	}
	public void validate(){
		super.validate();
	}
	@Override
	public IUimWidget getWidget(){
		return composite;
	}
	@Override
	public void setLabelText(String string){
	}
}