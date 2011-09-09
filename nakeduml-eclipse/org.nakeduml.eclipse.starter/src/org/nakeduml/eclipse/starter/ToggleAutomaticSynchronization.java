package org.nakeduml.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class ToggleAutomaticSynchronization extends AbstractOpiumAction{
	public ToggleAutomaticSynchronization(IStructuredSelection selection2){
		super(selection2, NakedUmlEditor.getNakedUmlEclipseContextFor((IContainer) selection2.getFirstElement()).getAutoSync()?"Turn off Auto Synchronization":"Turn on Auto Synchronization");
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final NakedUmlEclipseContext currentContext = NakedUmlEditor.getNakedUmlEclipseContextFor(folder);
		currentContext.setAutoSync(!currentContext.getAutoSync());
	}
}
