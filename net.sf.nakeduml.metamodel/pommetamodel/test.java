import java.io.File;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.DocumentRoot;
import org.apache.maven.pom.Model;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.POMPackage;
import org.apache.maven.pom.util.POMResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();

		// Register the appropriate resource factory to handle all file
		// extensions.
		//
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new POMResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put(POMPackage.eNS_URI,
				POMPackage.eINSTANCE);

		DocumentRoot droot = POMFactory.eINSTANCE.createDocumentRoot();
		Model root = POMFactory.eINSTANCE.createModel();
		droot.setProject(root);
		root.setName("asdf");
		root.setDependencies(POMFactory.eINSTANCE.createDependenciesType());
		Dependency dep = POMFactory.eINSTANCE.createDependency();
		dep.setArtifactId("ffff");
		root.getDependencies().getDependency().add(dep);
		Resource r = resourceSet.createResource(URI.createFileURI(new File(".")
				.getCanonicalPath()
				+ "/testpom.xml"));
		System.out.println(r);
		r.getContents().add(droot);
		r.save(null);

	}

}
