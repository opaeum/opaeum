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
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;

public class ImportPackageFromWorkspaceAction extends AbstractOpaeumAction{
	public ImportPackageFromWorkspaceAction(IStructuredSelection selection){
		super(selection, "Import from workspace ...");
	}
	@Override
	public void run(){
		ResourceDialog rd = new ResourceDialog(Display.getCurrent().getActiveShell(), "Import model", SWT.OPEN | SWT.SINGLE);
		if(rd.open() == Dialog.OK){
			Object firstElement = super.selection.getFirstElement();
			Namespace eObject = (Namespace) EmfElementFinder.adaptObject(firstElement);
			List<URI> urIs = rd.getURIs();
			OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(eObject);
			for(URI uri:urIs){
				Resource resource = eObject.eResource().getResourceSet().getResource(uri, true);
				if(resource.getContents().size() > 0 && resource.getContents().get(0) instanceof Package){
					Package library = (Package) resource.getContents().get(0);
					if(!library.getImportedPackages().contains(library)){
						PackageImport pi = UMLFactory.eINSTANCE.createPackageImport();
						pi.setImportedPackage(library);
						ouf.executeAndForget(AddCommand.create(ouf.getEditingDomain(), eObject, UMLPackage.eINSTANCE.getNamespace_PackageImport(), pi));
					}
				}else{
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Failed", "No valid package found in " + uri.toPlatformString(true));
				}
			}
		}
		super.run();
	}
}
