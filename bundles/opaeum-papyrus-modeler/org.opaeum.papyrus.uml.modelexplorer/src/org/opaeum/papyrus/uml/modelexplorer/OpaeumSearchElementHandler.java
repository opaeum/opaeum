package org.opaeum.papyrus.uml.modelexplorer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.papyrus.views.modelexplorer.dialog.NavigatorSearchDialog;
import org.eclipse.papyrus.views.modelexplorer.handler.SearchElementHandler;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpaeumSearchElementHandler extends SearchElementHandler{
	private final class OpaeumNavigatorSearchDialog extends NavigatorSearchDialog{
		private ILabelProvider labelProvider;
		private ITreeContentProvider contentProvider;
		private OpaeumNavigatorSearchDialog(Shell shell,TreeViewer viewer){
			super(shell, viewer);
		}
		private <T>T getFieldValue(String name){
			try{
				Field f = NavigatorSearchDialog.class.getDeclaredField(name);
				f.setAccessible(true);
				return (T) f.get(this);
			}catch(Exception e){
				return null;
			}
		}
		protected void launchSearch(final String pattern,final Object[] root){
			final boolean caseSensitive = this.<Button>getFieldValue("caseButton").getSelection();
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(null);
			try{
				dialog.run(true, true, new IRunnableWithProgress(){
					public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException{
						try{
							labelProvider = getFieldValue("labelProvider");
							contentProvider = getFieldValue("contentProvider");
							
							Field f = NavigatorSearchDialog.class.getDeclaredField("matchedObjects");
							f.setAccessible(true);
							f.set(OpaeumNavigatorSearchDialog.this, searchPattern(pattern, caseSensitive, Arrays.asList(root), monitor));
							currentIndex = 0;
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				});
			}catch(InvocationTargetException e){
				e.printStackTrace();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		private List<Object> searchPattern(String pattern,boolean caseSensitive,List<Object> objects,IProgressMonitor monitor){
			if(monitor.isCanceled()){
				return Collections.emptyList();
			}
			List<Object> matches = new ArrayList<Object>();
			List<Object> children = new ArrayList<Object>();
			String objectLabel;
			for(Object o:objects){
				// Search matches in this level
				if(!(o instanceof Diagram)){
					objectLabel = caseSensitive ? labelProvider.getText(o) : labelProvider.getText(o).toUpperCase();
					if(objectLabel.contains(pattern)){
						matches.add(o);
					}
					EObject parentEObj = (EObject) getAdapter(o, EObject.class);
					Object[] currentChildren = contentProvider.getChildren(o);
					addDescendantsThatAreDirectChildrenInModel(children, parentEObj, currentChildren);
				}
			}
			if(!children.isEmpty()){
				matches.addAll(searchPattern(pattern, caseSensitive, children, monitor));
			}
			return matches;
		}
		protected void addDescendantsThatAreDirectChildrenInModel(List<Object> children,EObject parentEObj,Object[] currentChildren){
			for(int i = 0;i < currentChildren.length;i++){
				Object child = currentChildren[i];
				if(child instanceof IAdaptable && ((IAdaptable) child).getAdapter(EObject.class) != null){
					EObject eObject = (EObject) ((IAdaptable) child).getAdapter(EObject.class);
					if(eObject != null && eObject.eContainer() != null && (parentEObj == null || eObject.eContainer().equals(parentEObj))){
						children.add(child);
					}
				}else{
					addDescendantsThatAreDirectChildrenInModel(children, parentEObj, contentProvider.getChildren(child));
				}
			}
		}
		protected KeyListener getKeyListener(){
			final KeyListener original = null;
			// TODO Auto-generated method stub
			return new KeyListener(){
				long lastEvent = 0;
				public void keyPressed(KeyEvent e){
				}
				public void keyReleased(final KeyEvent e){
					lastEvent = System.currentTimeMillis();
					Display.getDefault().timerExec(400, new Runnable(){
						public void run(){
							if(System.currentTimeMillis() > lastEvent + 399){
								original.keyReleased(e);
							}
						}
					});
				}
			};
		}
	}
	public Object execute(ExecutionEvent event) throws ExecutionException{
		Shell shell = HandlerUtil.getActiveShell(event);
		if(shell == null){
			return null;
		}
		NavigatorSearchDialog dialog = new OpaeumNavigatorSearchDialog(shell, getSelectedTreeViewer(event));
		dialog.open();
		return null;
	}
}
