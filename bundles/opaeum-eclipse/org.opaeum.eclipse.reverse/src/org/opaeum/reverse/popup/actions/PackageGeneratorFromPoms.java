package org.opaeum.reverse.popup.actions;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.DocumentRoot;
import org.apache.maven.pom.POMPackage;
import org.apache.maven.pom.Parent;
import org.apache.maven.pom.util.POMResourceFactoryImpl;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

public class PackageGeneratorFromPoms{
	ResourceSet resourceSet = new ResourceSetImpl();
	private Map<File,DocumentRoot> dirRootMap = new HashMap<File,DocumentRoot>();
	// private Map<DocumentRoot,Package> rootPackageMap = new HashMap<DocumentRoot,Package>();
	private Map<String,Package> coordinatePackageMap = new HashMap<String,Package>();
	private Model model;
	private Map<DocumentRoot,Package> rootPackageMap = new HashMap<DocumentRoot,Package>();
	public PackageGeneratorFromPoms(Model model){
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new POMResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(POMPackage.eNS_URI, POMPackage.eINSTANCE);
		this.model = model;
	}
	public Collection<Package> generateUml(Collection<IContainer> containers,IProgressMonitor m) throws Exception{
		for(IContainer c:containers){
			findOrCreatePackage(model, c.getLocation().toFile());
		}
		Set<Entry<DocumentRoot,Package>> entrySet = rootPackageMap.entrySet();
		for(Entry<DocumentRoot,Package> entry:entrySet){
			for(Dependency dependency:entry.getKey().getProject().getDependencies().getDependency()){
				Package imported = coordinatePackageMap.get(key(dependency));
				if(imported != null){
					entry.getValue().getPackageImport(imported, true);
				}
			}
		}
		return coordinatePackageMap.values();
	}
	public String key(Dependency dependency){
		return dependency.getGroupId() + dependency.getArtifactId();
	}
	private Package findOrCreatePackage(Model model,File dir){
		DocumentRoot root = getRoot(dir);
		Package parent = model;
		if(root.getProject().getParent() != null){
			parent = findOrCreateParentPackage(root.getProject().getParent(), model, dir);
			if(parent==null){
				//parent not available, just create in root of model
				parent=model;
			}
		}
		Package result = parent.getNestedPackage(calculatePackageName(root), false, UMLPackage.eINSTANCE.getPackage(), true);
		coordinatePackageMap.put(key(root), result);
		rootPackageMap.put(root, result);
		return result;
	}
	private String key(DocumentRoot root){
		return root.getProject().getGroupId() + root.getProject().getArtifactId();
	}
	private Package findOrCreateParentPackage(Parent parent,Model model,File dir){
		String relativePath = parent.getRelativePath() == null || parent.getRelativePath().isEmpty() ? ".." : parent.getRelativePath();
		File parentsDir = new File(dir, relativePath);
		return findOrCreatePackage(model, parentsDir);
	}
	private String calculatePackageName(DocumentRoot root){
		return root.getProject().getArtifactId().replace('-', '_');
	}
	private DocumentRoot getRoot(File dir){
		DocumentRoot result = dirRootMap.get(dir);
		if(result == null){
			URI uri = URI.createFileURI(dir.getAbsolutePath());
			uri = uri.appendFragment("pom.xml");
			dirRootMap.put(dir, result = (DocumentRoot) resourceSet.getResource(uri, true).getContents().get(0));
		}
		return result;
	}
}
