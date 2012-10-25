package org.opaeum.papyrus.uml.modelexplorer;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.papyrus.views.modelexplorer.dialog.NavigatorSearchDialog;
import org.eclipse.papyrus.views.modelexplorer.handler.SearchElementHandler;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpaeumSearchElementHandler extends SearchElementHandler{
	private final class OpaeumNavigatorSearchDialog extends NavigatorSearchDialog{
		private OpaeumNavigatorSearchDialog(Shell shell,TreeViewer viewer){
			super(shell, viewer);
		}
		@Override
		protected KeyListener getKeyListener(){
			final KeyListener original=super.getKeyListener();
			// TODO Auto-generated method stub
			return new KeyListener() {
			  long lastEvent=0;
				public void keyPressed(KeyEvent e) {
				}
			
				public void keyReleased(final KeyEvent e) {
					lastEvent=System.currentTimeMillis();
					Display.getDefault().timerExec(400, new Runnable(){
						public void run(){
							if(System.currentTimeMillis()> lastEvent+399){
								original.keyReleased(e);
							}
						}
					});
				}
			
			};
		}
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event);
		if(shell == null) {
			return null;
		}
		NavigatorSearchDialog dialog = new OpaeumNavigatorSearchDialog(shell, getSelectedTreeViewer(event));
		dialog.open();
		return null;
	}
}
