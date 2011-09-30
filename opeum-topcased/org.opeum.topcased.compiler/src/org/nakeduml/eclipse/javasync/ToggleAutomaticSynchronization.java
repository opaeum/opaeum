package org.opeum.eclipse.javasync;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opeum.eclipse.context.NakedUmlEclipseContext;
import org.opeum.eclipse.starter.AbstractOpiumAction;
import org.opeum.topcased.uml.editor.NakedUmlEditor;

public class ToggleAutomaticSynchronization extends AbstractOpiumAction{
	public ToggleAutomaticSynchronization(IStructuredSelection selection2){
		super(selection2, calculateName(selection2));
	}
	private static String calculateName(IStructuredSelection selection2){
		NakedUmlEclipseContext ctx = NakedUmlEditor.getContextFor((IContainer) selection2.getFirstElement());
		return ctx!=null &&  ctx.getAutoSync()?"Turn off Auto Synchronization":"Turn on Auto Synchronization";
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final NakedUmlEclipseContext currentContext = NakedUmlEditor.findOrCreateContextFor(folder);
		currentContext.setAutoSync(!currentContext.getAutoSync());
	}
}
