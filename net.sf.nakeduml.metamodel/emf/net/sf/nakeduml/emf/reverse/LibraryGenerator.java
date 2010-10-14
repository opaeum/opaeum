package net.sf.nakeduml.emf.reverse;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.load.UML2ModelLoader;
import net.sf.nakeduml.name.NameConverter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.resource.UMLResource;

public class LibraryGenerator extends AbstractUmlGenerator {

	public static void main(String[] args) throws Exception {
		String jarFileName = args[0];
		String targetDir = args[1];
		URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		URL[] urLs = sysloader.getURLs();
		URL jarFileUrl = null;
		for (URL url : urLs) {
			if (url.toString().contains(jarFileName)) {
				jarFileUrl = url;
				break;
			}
		}
		if (jarFileUrl == null) {
			throw new Exception("Jar file not found in the classpath");
		}
		File file = new File(jarFileUrl.toExternalForm().substring(jarFileUrl.getProtocol().length() + 1));
		if (file.exists()) {
			JarFile jarFile = new JarFile(file);
			selectClasses(jarFile);

			EmfElementCreator.registerPathmaps(URI.createURI(findUml2ResourceJar()));
			EmfElementCreator.registerResourceFactories();
			File targetDirFile = new File(targetDir);
			targetDirFile.mkdirs();
			File profileFile = new File(targetDirFile, jarFileName + "." + UMLResource.LIBRARY_FILE_EXTENSION);
			Model library = null;
			URI targetUri = URI.createFileURI(profileFile.getAbsolutePath());
			Enumeration<JarEntry> entries = jarFile.entries();
			ResourceSetImpl resourceSet = new ResourceSetImpl();
			if (profileFile.exists()) {
				library = UML2ModelLoader.loadModel(profileFile.getAbsolutePath());
				importPrimitiveTypes(library);
				createClasses(entries, library);
				library.eResource().save(null);
			} else {
				library = UMLFactory.eINSTANCE.createModel();
				Resource resource = resourceSet.createResource(targetUri);
				resource.getContents().add(library);
				library.setName(NameConverter.toJavaVariableName(jarFileName));
				importPrimitiveTypes(library);
				createClasses(entries, library);
				resource.save(null);
			}
			mappedTypes.store(new FileOutputStream(targetDir + "/" + jarFileName + ".library."+EmfExtractionPhase.MAPPINGS_EXTENSION), "Generated by NakedUml");
		}
	}

	private static void createClasses(Enumeration<JarEntry> entries, Model model) throws ClassNotFoundException {
		// create a profile
		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			String cn = entry.getName();
			cn = cn.replaceAll("/", ".");
			if (cn.endsWith(".class")) {
				Class<?> c = Thread.currentThread().getContextClassLoader().loadClass(cn.substring(0, cn.length() - 6));
				if (!c.isAnnotation()) {
					getClassifierFor(model, c);
				}
			}
		}
	}

}