package org.opeum.eclipse.starter;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.opeum.eclipse.ModelLibrary;
import org.opeum.eclipse.context.OpeumEclipseContext;

public class ApplyProfileAction extends AbstractOpiumAction{
	private ModelLibrary library;
	public ApplyProfileAction(IStructuredSelection selection,ModelLibrary lib){
		super(selection, lib.getName());
		this.library = lib;
	}
	@Override
	public void run(){
		final Model model = (Model) selection.getFirstElement();
		final OpeumEclipseContext currentContext = OpeumEclipseContext.getCurrentContext();
		EditingDomain ed = currentContext.getEditingDomain();
		final Profile profile = (Profile) model.eResource().getResourceSet().getResource(library.getUri(), true).getContents().get(0);
		if(profile.getDefinition() == null){
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "Profile not defined yet", "Please define your profile before deploying it");
		}else{
			ed.getCommandStack().execute(new AbstractCommand(){
				
				@Override
				public boolean canExecute(){
					return true;
				}
				@Override
				public void execute(){
					System.out.println("sdfg");
					redo();
				}
				@Override
				public void redo(){
					if(model.getAllAppliedProfiles().contains(profile)){
						if(model.getProfileApplication(profile).getAppliedDefinition() != profile.getDefinition()){
							model.applyProfile(profile);
						}
					}else{
						model.applyProfile(profile);
					}
				}
			});
		}
	}
}