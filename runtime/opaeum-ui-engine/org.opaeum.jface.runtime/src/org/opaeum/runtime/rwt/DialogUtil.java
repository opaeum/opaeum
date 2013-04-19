package org.opaeum.runtime.rwt;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.rap.rwt.widgets.DialogCallback;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;

public class DialogUtil{
	public static void open(final Dialog d,final DialogCallback cb){
		d.setBlockOnOpen(false);
		d.open();
		
		if(cb != null){
			d.getShell().addDisposeListener(new DisposeListener(){
				@Override
				public void widgetDisposed(DisposeEvent arg0){
					cb.dialogClosed(d.getReturnCode());
				}
			});
			d.getShell().addShellListener(new ShellListener(){
				@Override
				public void shellDeactivated(ShellEvent arg0){
				}
				@Override
				public void shellClosed(ShellEvent arg0){
					cb.dialogClosed(d.getReturnCode());
				}
				@Override
				public void shellActivated(ShellEvent arg0){
				}
			});
		}
	}
}
