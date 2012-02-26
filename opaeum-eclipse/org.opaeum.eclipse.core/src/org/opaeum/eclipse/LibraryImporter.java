package org.opaeum.eclipse;

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
		for(Resource resource:model.eResource().getResourceSet().getResources()){
			if(resource.getContents().get(0) instanceof Model){
				Model m=(Model) resource.getContents().get(0);
				if(!m.eIsProxy() && resource.getURI().lastSegment().equals(librName)){
					library = m;
				}
			}
		}
		EList<PackageImport> packageImports = model.getPackageImports();
		for(PackageImport packageImport:packageImports){
		}
		return library;
	}
}
