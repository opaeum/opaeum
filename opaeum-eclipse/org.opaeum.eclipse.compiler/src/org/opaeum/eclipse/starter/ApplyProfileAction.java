package org.opaeum.eclipse.starter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.ModelLibrary;
import org.opaeum.eclipse.commands.ApplyProfileCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class ApplyProfileAction extends AbstractOpaeumAction{
	private ModelLibrary library;
	public ApplyProfileAction(IStructuredSelection selection,ModelLibrary lib){
		super(selection, lib.getName());
		this.library = lib;
	}
	@Override
	public void run(){
		final Model model = (Model) getElementFrom();
		final Profile profile = (Profile) model.eResource().getResourceSet().getResource(library.getUri(), true).getContents().get(0);
		if(profile.getDefinition() == null){
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "Profile not defined yet", "Please define your profile before deploying it");
		}else{
			OpaeumEclipseContext.findOpenUmlFileFor(model).executeAndForget(new ApplyProfileCommand(model,profile,true));
		}
	}
}
