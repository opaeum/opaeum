package org.opaeum.uim.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.opaeum.uimodeler.util.UimFigureUtil;

public final class CustomUserInterfaceFigure extends BorderItemsAwareFreeFormLayer implements ISWTFigure{
	long lastValidated = 0;
	private Composite composite;
	public CustomUserInterfaceFigure(){
		super();
		composite = new Composite(UimFigureUtil.getFakeShell(), SWT.NONE);
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
		// // HACK seems to be a bug in GMF that asynchronously continues to revalidate,
		// if(lastValidated==0|| (lastValidated + 20 > System.currentTimeMillis())){
		// lastValidated=System.currentTimeMillis();
		super.validate();
		// }
	}
	@Override
	public Widget getWidget(){
		return composite;
	}
	@Override
	public void setLabelText(String string){
	}
	@Override
	public void markForRepaint(){
		// TODO Auto-generated method stub
	}
}