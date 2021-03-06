package org.opaeum.uimodeler.common;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.opaeum.uim.swt.GridPanelComposite;
import org.opaeum.uimodeler.common.figures.ISWTFigure;

public class UimFigureUtil{
	public static final String FIGURE = "FIGURE";
	public static final String ELEMENT = "ELEMENT";
	public static final String OPAEUM_IMAGE = "OPAEUM_IMAGE";
	static Shell shlChooseElement;
	public static Shell getFakeShell(){
		final Shell activeShell = Display.getCurrent().getActiveShell();
		if(shlChooseElement == null || shlChooseElement.isDisposed()){
			shlChooseElement = new Shell(SWT.MODELESS | SWT.MIN);
			shlChooseElement.setLayout(new StackLayout());
			shlChooseElement.setVisible(false);
			shlChooseElement.setSize(1800, 1200);
			shlChooseElement.setLocation(0, 0);
			if(activeShell != null){
				activeShell.setActive();
				shlChooseElement.addShellListener(new ShellListener(){
					@Override
					public void shellIconified(ShellEvent e){
					}
					@Override
					public void shellDeiconified(ShellEvent e){
						// Shell[] shells = Display.getCurrent().getShells();
						// for(int i = 0; i < shells.length-1){
						// if(shlChooseElement==shells[i]){
						// shells[i].setActive();
						// }
						// }
						// shlChooseElement.moveBelow(activeShell);
					}
					@Override
					public void shellDeactivated(ShellEvent e){
					}
					@Override
					public void shellClosed(ShellEvent e){
					}
					@Override
					public void shellActivated(ShellEvent e){
						// shlChooseElement.moveBelow(activeShell);
						// activeShell.setActive();
					}
				});
			}
		}
		return shlChooseElement;
	}
	public static org.eclipse.draw2d.geometry.Rectangle toDraw2DRectangle(Control ctl){
		if(ctl.isDisposed()){
			return new org.eclipse.draw2d.geometry.Rectangle(0,0,300,30);
		}
		return new org.eclipse.draw2d.geometry.Rectangle(getAbsoluteX(ctl), getAbsoluteY(ctl), ctl.getBounds().width, ctl.getBounds().height);
	}
	public static int getAbsoluteY(Control ctl){
		if(ctl.getParent() instanceof Shell){
			// Add the offset of the top level grid which is not carried in the SWT shell
			return ctl.getBounds().y;// + ((Figure) ctl.getData(UimFigureUtil.FIGURE)).getBounds().y;
		}else{
			return ctl.getBounds().y;// +getAbsoluteY(ctl.getParent());
		}
	}
	public static int getAbsoluteX(Control ctl){
		Rectangle bnds = ctl.getBounds();
		if(ctl.getParent() instanceof Shell){
			return ctl.getBounds().x;// + ((Figure) ctl.getData(UimFigureUtil.FIGURE)).getBounds().x;
		}else{
			int absoluteY = getAbsoluteY(ctl.getParent());
			return bnds.x;// +absoluteY;
		}
	}
	public static Composite getNearestComposite(EditPart parent){
		ISWTFigure f = getNearestSwtFigure(parent);
		if(f == null){
			throw new IllegalStateException();
		}else if(f.getWidget() instanceof GridPanelComposite){
			return ((GridPanelComposite) f.getWidget()).getContentPane();
		}else if(f.getWidget() instanceof Composite){
			return (Composite) f.getWidget();
		}else{
			return ((Control) f.getWidget()).getParent();
		}
	}
	private static ISWTFigure getNearestSwtFigure(EditPart parent){
		if(parent instanceof AbstractGraphicalEditPart && ((AbstractGraphicalEditPart) parent).getContentPane() instanceof ISWTFigure){
			return (ISWTFigure) ((AbstractGraphicalEditPart) parent).getContentPane();
		}else if(parent == null){
			return null;
		}else{
			return getNearestSwtFigure(parent.getParent());
		}
	}
	@SuppressWarnings("unchecked")
	public static <T>T findChildEditPart(EditPart ep,Class<T> t){
		for(Object object:ep.getChildren()){
			if(t.isInstance(object)){
				return (T) object;
			}
		}
		return null;
	}
	public static void applyBounds(org.eclipse.draw2d.geometry.Rectangle bounds,org.eclipse.swt.graphics.Rectangle bounds2){
		bounds.x = bounds2.x;
		bounds.y = bounds2.y;
		bounds.width = bounds2.width;
		bounds.height = bounds2.height;
	}
}
