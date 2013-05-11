package org.opaeum.eclipse.menu;

import java.util.List;

import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.commands.ApplyProfileCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;

public class ApplyProfileFromWorkspaceAction extends AbstractOpaeumAction{
	public ApplyProfileFromWorkspaceAction(IStructuredSelection selection){
		super(selection, "Apply Profile from workspace ...");
	}
	@Override
	public void run(){
		ResourceDialog rd = new ResourceDialog(Display.getCurrent().getActiveShell(), "Import and apply profile ", SWT.OPEN | SWT.SINGLE);
		if(rd.open() == Dialog.OK){
			Object firstElement = super.selection.getFirstElement();
			Package pkg = (Package) EmfElementFinder.adaptObject(firstElement);
			List<URI> urIs = rd.getURIs();
			OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(pkg);
			for(URI uri:urIs){
				Resource resource = pkg.eResource().getResourceSet().getResource(uri, true);
				if(resource.getContents().size() > 0 && resource.getContents().get(0) instanceof Profile){
					Profile profile= (Profile) resource.getContents().get(0);
					if(!pkg.getAppliedProfiles().contains(profile) && profile.isDefined()){
						ouf.executeAndForget(new ApplyProfileCommand(pkg,profile,true));
					}
				}else{
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Failed", "No valid profile  found in " + uri.toPlatformString(true));
				}
			}
		}
		super.run();
	}
}
