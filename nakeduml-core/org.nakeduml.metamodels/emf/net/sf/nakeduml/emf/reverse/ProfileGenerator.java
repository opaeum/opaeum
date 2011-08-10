package net.sf.nakeduml.emf.reverse;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.extraction.NameSpaceExtractor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

public class ProfileGenerator extends AbstractUmlGenerator{
	private static org.eclipse.uml2.uml.Class classMetaClass;
	private static org.eclipse.uml2.uml.Class propertyMetaClass;
	private static org.eclipse.uml2.uml.Class operationMetaClass;
	private static org.eclipse.uml2.uml.Class classifierMetaClass;

	static void createStereotypeForAnnotation(Class annotation, Profile ecoreProfile) {
		Stereotype stereotype = (Stereotype) ecoreProfile.getMember(annotation.getSimpleName());
		if (stereotype == null) {
			stereotype = ProfileBuilder.createStereotype(ecoreProfile, annotation.getSimpleName(), true);
			stereotype.setIsAbstract(false);
			Target target = (Target)annotation.getAnnotation(Target.class);
			for (ElementType e : target.value()) {
				org.eclipse.uml2.uml.Class metaClass = null;
				if (e == ElementType.TYPE) {
					metaClass = classMetaClass;
				} else if (e == ElementType.FIELD) {
					metaClass = propertyMetaClass;
					stereotype.createExtension(classifierMetaClass, false);
				} else if (e == ElementType.METHOD) {
					metaClass = operationMetaClass;
				}
				if (metaClass != null) {
					stereotype.createExtension(metaClass, false);
				}
			}
		}
		for (Method m : annotation.getDeclaredMethods()) {
			Class<?> returnType = m.getReturnType();
			int upperLimit = 1;
			if (returnType.isArray()) {
				returnType = returnType.getComponentType();
				upperLimit = LiteralUnlimitedNatural.UNLIMITED;
			}
			Property myProperty = null;
			if (returnType == Boolean.class || returnType == boolean.class) {
				myProperty = createBooleanProperty(stereotype, m);
			} else if (returnType == Integer.class || returnType == int.class || returnType == Long.class || returnType == long.class
					|| returnType == Short.class || returnType == short.class || returnType == Byte.class || returnType == byte.class) {
				myProperty = createIntegerProperty(stereotype, m);
			} else if (returnType == String.class) {
				myProperty = createStringProperty(stereotype, m);
			} else if (returnType.isEnum()) {
				Enumeration enumeration = getEnumeration(ecoreProfile, returnType);
				myProperty = createEnumerationProperty(stereotype, m, enumeration);
			} else if (returnType == Class.class) {
				myProperty = ProfileBuilder.createAttribute(stereotype, m.getName(), classMetaClass, 0, 1);
			}
			if (!returnType.isAnnotation()) {
				// Do not support nested annotations yet
				if (myProperty == null) {
					System.out.println(m.getName() + " has no known returntype");
				} else {
					myProperty.setUpper(upperLimit);
				}
			}
		}
		mappedTypes.put(stereotype.getQualifiedName(), annotation.getName());
	}

	@SuppressWarnings("unchecked")
	protected static Enumeration getEnumeration(Profile ecoreProfile, Class<?> returnType) {
		return (Enumeration) getClassifierFor(ecoreProfile, returnType);
	}

	private static Property createEnumerationProperty(Stereotype stereotype, Method m, org.eclipse.uml2.uml.Enumeration enumeration) {
		Property myProperty = (Property) stereotype.getMember(m.getName());
		if (myProperty == null) {
			myProperty = ProfileBuilder.createAttribute(stereotype, m.getName(), enumeration, 0, 1);
			if (m.getDefaultValue() != null) {
				// could be array
			}
		}
		return myProperty;
	}



	private static Property createStringProperty(org.eclipse.uml2.uml.Class stereotype, Method m) {
		Property myProperty = (Property) stereotype.getMember(m.getName());
		if (myProperty == null) {
			// create a property
			myProperty = ProfileBuilder.createAttribute(stereotype, m.getName(), stringPrimitiveType, 0, 1);
			if (m.getDefaultValue() instanceof String) {
				LiteralString literalBoolean = UMLFactory.eINSTANCE.createLiteralString();
				literalBoolean.setValue(((String) m.getDefaultValue()));
				myProperty.setDefaultValue(literalBoolean);
			}
		}
		return myProperty;
	}

	private static Property createIntegerProperty(org.eclipse.uml2.uml.Class stereotype, Method m) {
		Property myProperty = (Property) stereotype.getMember(m.getName());
		if (myProperty == null) {
			// create a property
			myProperty = ProfileBuilder.createAttribute(stereotype, m.getName(), integerPrimitiveType, 0, 1);
			if (m.getDefaultValue() instanceof Number) {
				LiteralInteger literalBoolean = UMLFactory.eINSTANCE.createLiteralInteger();
				literalBoolean.setValue(((Number) m.getDefaultValue()).intValue());
				myProperty.setDefaultValue(literalBoolean);
			}
		}
		return myProperty;
	}

	private static Property createBooleanProperty(org.eclipse.uml2.uml.Class stereotype, Method m) {
		Property myProperty = (Property) stereotype.getMember(m.getName());
		if (myProperty == null) {
			// create a property
			myProperty = ProfileBuilder.createAttribute(stereotype, m.getName(), booleanPrimitiveType, 0, 1);
			if (m.getDefaultValue() != null) {
				LiteralBoolean literalBoolean = UMLFactory.eINSTANCE.createLiteralBoolean();
				literalBoolean.setValue(((Boolean) m.getDefaultValue()).booleanValue());
				myProperty.setDefaultValue(literalBoolean);
			}
		}
		return myProperty;
	}

	public static void main(String[] args) throws Exception {
		String libraryHome = args[0];
		String jarFileName = args[1];
		String profileName = args[2];
//		String metamodelResourceJar = args[3];
		String targetDir = args[4];
		URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		Class sysclass = URLClassLoader.class;
		Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
		method.setAccessible(true);
		File jbossHome = new File(libraryHome);
		appendJars(sysloader, method, jbossHome);
		JarFile jarFile = new JarFile(new File(jbossHome, jarFileName));
		java.util.Enumeration<JarEntry> entries = jarFile.entries();
//		EmfElementCreator.registerPathmaps(URI.createURI("jar:file:/" + metamodelResourceJar + "!/"));
		
		EmfElementCreator.registerPathmaps(URI.createURI(findUml2ResourceJar()));
		
		EmfElementCreator.registerResourceFactories();
		File rsaFile = new File(targetDir + "/" + profileName + ".epx");
		File profileFile = new File(targetDir + "/" + profileName + "." + UMLResource.PROFILE_FILE_EXTENSION);
		Profile ecoreProfile = null;
		Resource resource = null;
		URI targetUri = URI.createFileURI(profileFile.getAbsolutePath());
		if (rsaFile.exists()) {
			rsaFile.renameTo(profileFile);
			resource = EmfElementCreator.loadResource(URI.createFileURI(profileFile.getAbsolutePath()));
			ecoreProfile = (org.eclipse.uml2.uml.Profile) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
			createStereotypesForAnnotations(entries, ecoreProfile);
			ecoreProfile.define();
			resource.save(null);
		} else {
			ecoreProfile = ProfileBuilder.createProfile(profileName);
			importPrimitiveTypes(ecoreProfile);
			importMetaModel(ecoreProfile);
			createStereotypesForAnnotations(entries, ecoreProfile);
			ecoreProfile.define();
			EmfElementCreator.save(ecoreProfile, targetUri);
		}
		mappedTypes.store(new FileOutputStream(targetDir + "/" + profileName + "." +NameSpaceExtractor.MAPPINGS_EXTENSION), "Generated by NakedUml");
		new File(targetUri.toFileString()).renameTo(rsaFile);
	}

	private static void createStereotypesForAnnotations(java.util.Enumeration<JarEntry> entries, Profile ecoreProfile) throws ClassNotFoundException {
		// create a profile
		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			String cn = entry.getName();
			cn = cn.replaceAll("/", ".");
			if (cn.endsWith(".class")) {
				Class<?> c = Thread.currentThread().getContextClassLoader().loadClass(cn.substring(0, cn.length() - 6));
				if (c.isAnnotation()) {
					createStereotypeForAnnotation(c, ecoreProfile);
				}
			}
		}
	}

	private static void importMetaModel(Profile ecoreProfile) {
		Model umlMetamodel = (Model) EmfElementCreator.load(URI.createURI(UMLResource.UML_METAMODEL_URI));
		classMetaClass = (org.eclipse.uml2.uml.Class) umlMetamodel.getOwnedType("Class");
		propertyMetaClass = (org.eclipse.uml2.uml.Class) umlMetamodel.getOwnedType("Property");
		operationMetaClass = (org.eclipse.uml2.uml.Class) umlMetamodel.getOwnedType("Operation");
		classifierMetaClass = (org.eclipse.uml2.uml.Class) umlMetamodel.getOwnedType("Classifier");
		ecoreProfile.createMetamodelReference(umlMetamodel);
	}


	
	private static void appendJars(Object classLoader, Method method, File deployDir) throws Exception {
		File[] jars = deployDir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith("ar");
			}
		});
		for (File file : jars) {
			method.invoke(classLoader, new Object[] { file.toURL() });
		}
	}
	
}
