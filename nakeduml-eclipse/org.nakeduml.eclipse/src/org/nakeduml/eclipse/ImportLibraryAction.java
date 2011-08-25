package org.nakeduml.eclipse;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;

public class ImportLibraryAction implements IObjectActionDelegate{
	private IStructuredSelection selection;
	@Override
	public void run(IAction arg0){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			if(element instanceof Model){
				Model model = (Model) element;
				String librName = "OpiumSimpleTypes.library.uml";
				importLibraryIfNecessary(model, librName);
			}
		}
	}
	public static Model importLibraryIfNecessary(Model model,String librName){
		Model library = findLibrary(model, librName);
		if(library == null){
			Resource resource = model.eResource().getResourceSet().getResource(URI.createURI(StereotypeNames.MODELS_PATHMAP + "libraries/" + librName), true);
			library = (Model) resource.getContents().get(0);
			model.createPackageImport(library);
		}
		return library;
	}
	public static Model findLibrary(Model model,String librName){
		Model library = null;
		EList<PackageImport> packageImports = model.getPackageImports();
		for(PackageImport packageImport:packageImports){
			if(!packageImport.getImportedPackage().eIsProxy() && packageImport.getImportedPackage().eResource().getURI().lastSegment().equals(librName)){
				library = (Model) packageImport.getImportedPackage();
			}
		}
		return library;
	}
	@Override
	public void selectionChanged(IAction arg0,ISelection selection){
		this.selection = (IStructuredSelection) selection;
	}
	@Override
	public void setActivePart(IAction arg0,IWorkbenchPart arg1){
	}
}
