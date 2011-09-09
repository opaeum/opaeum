package net.sf.nakeduml.emf.reverse;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.ModelImpl;
import org.eclipse.uml2.uml.internal.impl.ProfileImpl;
import org.eclipse.uml2.uml.internal.impl.StereotypeImpl;
import org.eclipse.uml2.uml.resource.UMLResource;

@SuppressWarnings({"unchecked"})
public class Test {
	public static void main(String[] args) {
		// Register the package -- only needed for stand-alone!
		// EPackage.Registry.INSTANCE.put(NotationPackage.eNS_URI,
		// NotationPackage.eINSTANCE);
		// EPackage.Registry.INSTANCE.put(UmlnotationPackage.eNS_URI,
		// UmlnotationPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("Test", UMLResource.Factory.INSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("Test", UMLResource.Factory.INSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("Test", new EcoreResourceFactoryImpl());
		// Get the URI of the model file.
		URI modelFile = URI.createFileURI(new File("Test").getAbsolutePath());
		// Create a resource set.
		ResourceSet resourceSet = new ResourceSetImpl();
		addProfile(resourceSet, "Test", "Test");
		// addProfile(resourceSet,
		// "http:///UMLStandardProfile_0_JvDIQB1oEduyUbGnBTAjIw.profile.uml2",
		// "UML_Standard_Profile.UML_Standard_Profile.profile.uml2");
		// addProfile(resourceSet, "http:///Complete_0.profile.uml2",
		// "UML_Standard_Profile.MagicDraw_Profile.profile.uml2");
		ModelImpl contents = (ModelImpl) resourceSet.getResource(modelFile, true).getContents().get(0);
		NamedElement ownedType = contents.getOwnedMember("Test");
		loadProfile();
	}

	private static EPackage addProfile(ResourceSet resourceSet, String profileUri, String fileOfProfile) {
		URI compleProfile = URI.createFileURI(new File(fileOfProfile).getAbsolutePath());
		ProfileImpl profileImpl = ((ProfileImpl) resourceSet.getResource(compleProfile, true).getContents().get(0));
		EAnnotation annotation = profileImpl.getEAnnotations().get(1);
		resourceSet.getPackageRegistry().put(profileUri, annotation.getContents().get(0));
		return (EPackage) annotation.getContents().get(0);
	}

	private static void loadProfile() {
		// Get the URI of the model file.
		URI modelFile = URI.createFileURI(new File("Test").getAbsolutePath());
		URI profileFile = URI.createFileURI(new File("Test").getAbsolutePath());
		// Create a resource set.
		ResourceSet resourceSet = new ResourceSetImpl();
		ProfileImpl profile = (ProfileImpl) resourceSet.getResource(profileFile, true).getContents().get(0);
		ModelImpl contents = (ModelImpl) resourceSet.getResource(modelFile, true).getContents().get(0);
		ClassImpl ownedType = (ClassImpl) contents.getOwnedType("Test");
		StereotypeImpl st = (StereotypeImpl) profile.getOwnedType("Test");
		System.out.println(ownedType.getValue(st, "Test"));
	}
}