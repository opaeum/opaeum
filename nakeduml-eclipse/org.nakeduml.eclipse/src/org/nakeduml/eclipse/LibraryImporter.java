package org.nakeduml.eclipse;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageImport;

public class LibraryImporter{
	public static Model importLibraryIfNecessary(Model model,String librName){
		URI uri = URI.createURI(StereotypeNames.MODELS_PATHMAP + "libraries/" + librName);
		return importLibraryIfNecessary(model, uri);
	}
	public static Model importLibraryIfNecessary(Model model,URI uri){
		Model library = findLibrary(model, uri.lastSegment());
		if(library == null){
			Resource resource = model.eResource().getResourceSet().getResource(uri, true);
			library = (Model) resource.getContents().get(0);
			model.createPackageImport(library);
		}
		return library;
	}
	public static Model findLibrary(Model model,String librName){
		Model library = null;
		EList<PackageImport> packageImports = model.getPackageImports();
		for(PackageImport packageImport:packageImports){
			if(packageImport.getImportedPackage() instanceof Model &&  !packageImport.getImportedPackage().eIsProxy() && packageImport.getImportedPackage().eResource().getURI().lastSegment().equals(librName)){
				library = (Model) packageImport.getImportedPackage();
			}
		}
		return library;
	}
}
