package org.opaeum.uimodeler.util;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ControlPainter{
	static Shell shlChooseElement;
	public static Shell getFakeShell(){
		final Shell activeShell = Display.getCurrent().getActiveShell();
		if(shlChooseElement == null || shlChooseElement.isDisposed()){
			shlChooseElement = new Shell(SWT.MODELESS | SWT.MIN);
			shlChooseElement.setVisible(true);
			shlChooseElement.moveBelow(activeShell);
			activeShell.setActive();
			shlChooseElement.addShellListener(new ShellListener(){
				@Override
				public void shellIconified(ShellEvent e){
				}
				@Override
				public void shellDeiconified(ShellEvent e){
					shlChooseElement.moveBelow(activeShell);
				}
				@Override
				public void shellDeactivated(ShellEvent e){
				}
				@Override
				public void shellClosed(ShellEvent e){
				}
				@Override
				public void shellActivated(ShellEvent e){
					shlChooseElement.moveBelow(activeShell);
					activeShell.setActive();
				}
			});
		}
		return shlChooseElement;
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
}
