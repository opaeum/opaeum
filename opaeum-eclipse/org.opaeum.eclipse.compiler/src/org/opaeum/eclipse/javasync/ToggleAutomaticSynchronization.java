package org.opaeum.eclipse.javasync;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.starter.AbstractOpaeumAction;

public class ToggleAutomaticSynchronization extends AbstractOpaeumAction{
	public ToggleAutomaticSynchronization(IStructuredSelection selection2){
		super(selection2, calculateName(selection2));
	}
	private static String calculateName(IStructuredSelection selection2){
		OpaeumEclipseContext ctx = OpaeumEclipseContext.getContextFor((IContainer) selection2.getFirstElement());
		return ctx!=null &&  ctx.getAutoSync()?"Don't compile automatically":"Compile automatically";
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext currentContext = OpaeumEclipseContext.findOrCreateContextFor(folder);
		currentContext.getConfig().setAutoSync(currentContext.getAutoSync());
		currentContext.getConfig().store();
	}
}
