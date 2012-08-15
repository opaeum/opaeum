package org.opaeum.emf.reverse;

import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

public abstract class EmfElementCreator {
	public static boolean DEBUG = true;
	protected static final ResourceSet RESOURCE_SET = new ResourceSetImpl();

	protected static void out(String output) {
		if (DEBUG) {
			System.out.println(output);
		}
	}

	protected static void err(String error) {
		System.err.println(error);
	}

	public static void registerResourceFactories() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.LIBRARY_FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	public static void registerPathmaps(URI uri) {
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
	}

	public static void save(org.eclipse.uml2.uml.Package package_, URI uri) {
		Resource resource = RESOURCE_SET.createResource(uri);
		EList<EObject> contents = resource.getContents();
		contents.add(package_);
//		for (Iterator allContents = UML2Util.getAllContents(package_, true, false); allContents.hasNext();) {
//			EObject eObject = (EObject) allContents.next();
//			if (eObject instanceof Element) {
//				contents.addAll(((Element) eObject).getStereotypeApplications());
//			}
//		}
		try {
			resource.save(null);
			out("Done.");
		} catch (IOException ioe) {
			err(ioe.getMessage());
		}
	}

	public static Resource loadResource(URI uri) {
		return RESOURCE_SET.getResource(uri, true);
	}

	public static org.eclipse.uml2.uml.Package load(URI uri) {
		org.eclipse.uml2.uml.Package package_ = null;
		try {
			Resource resource = RESOURCE_SET.getResource(uri, true);
			package_ = (org.eclipse.uml2.uml.Package) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
		} catch (WrappedException we) {
			err(we.getMessage());
			System.exit(1);
		}
		return package_;
	}
}