package org.opaeum.uimodeler.util;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.opaeum.uim.figures.ISWTFigure;

public class UimFigureUtil{
	public static final String FIGURE = "FIGURE";
	public static final String ELEMENT = "ELEMENT";
	static Shell shlChooseElement;
	public static Shell getFakeShell(){
		final Shell activeShell = Display.getCurrent().getActiveShell();
		if(shlChooseElement == null || shlChooseElement.isDisposed()){
			shlChooseElement = new Shell(SWT.MODELESS | SWT.MIN);
			StackLayout sl = new StackLayout();
			shlChooseElement.setLayout(sl);
			shlChooseElement.setVisible(true);
			shlChooseElement.setLocation(-1000, -1000);
			shlChooseElement.setSize(1800, 1200);
			shlChooseElement.moveBelow(activeShell);
			if(activeShell != null){
				activeShell.setActive();
				shlChooseElement.addShellListener(new ShellListener(){
					@Override
					public void shellIconified(ShellEvent e){
					}
					@Override
					public void shellDeiconified(ShellEvent e){
//						Shell[] shells = Display.getCurrent().getShells();
//						for(int i = 0; i < shells.length-1){
//							if(shlChooseElement==shells[i]){
//								shells[i].setActive();
//							}
//						}
//						shlChooseElement.moveBelow(activeShell);
					}
					@Override
					public void shellDeactivated(ShellEvent e){
					}
					@Override
					public void shellClosed(ShellEvent e){
					}
					@Override
					public void shellActivated(ShellEvent e){
//						shlChooseElement.moveBelow(activeShell);
						// activeShell.setActive();
					}
				});
			}
		}
		return shlChooseElement;
	}
	public static org.eclipse.draw2d.geometry.Rectangle toDraw2DRectangle(Rectangle r){
		return new org.eclipse.draw2d.geometry.Rectangle(r.x, r.y, r.width, r.height);
	}
	public static void paint(Control c,Figure f,Graphics g){
		final Image image = new Image(Display.getDefault(), f.getBounds().width, f.getBounds().height);
		GC gc = new GC(image);
		c.print(gc);
		ImageFigure ig = new ImageFigure(image);
		ig.setBounds(f.getBounds());
		ig.paint(g);
		c.dispose();
	}
	public static Composite getNearestComposite(EditPart parent){
		ISWTFigure f = getNearestSwtFigure(parent);
		if(f == null){
			Control[] children = getFakeShell().getChildren();
			for(Control control:children){
				control.dispose();
			}
			return getFakeShell();
		}else if(f.getWidget() instanceof Composite){
			return (Composite) f.getWidget();
		}else if(f.getWidget() instanceof Control){
			return ((Control) f.getWidget()).getParent();
		}else{
			return null;
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
		bounds.x=bounds2.x;
		bounds.y=bounds2.y;
		bounds.width=bounds2.width;
		bounds.height=bounds2.height;
		
	}
}
