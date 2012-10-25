package org.opaeum.javageneration.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.name.NameConverter;

public class JavaNameGenerator{
	protected boolean hasName(NamedElement p){
		return p.getName() != null;
	}
	public static String toJavaName(String name){
		return NameConverter.toJavaVariableName(name);
	}
	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static String packagePathname(Namespace p){
		Set<String> keywords = new HashSet<String>();
		keywords.add("public");
		keywords.add("static");
		keywords.add("final");
		keywords.add("class");
		keywords.add("void");
		keywords.add("return");
		if(p instanceof Package){
			Package np = (Package) p;
			if(EmfPackageUtil.hasMappedImplementationPackage(np)){
				return EmfPackageUtil.getMappedImplementationPackage(np);
			}else if(EmfPackageUtil.isRootPackage(np) || p instanceof Model || p instanceof Profile || p.getOwner() == null){
				return np.getName().toLowerCase();
			}
		}
		StringBuilder path = new StringBuilder();
		addParentsToPath(p, path);
		String lowerCase = p.getName().toLowerCase();
		if(keywords.contains(lowerCase)){
			path.append(lowerCase + "_");
		}else{
			path.append(lowerCase);
		}
		return path.toString();
	}
	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static String classifierPathname(NamedElement ne){
		if(ne instanceof Classifier){
			Classifier classifier = (Classifier) ne;
			if(EmfClassifierUtil.hasMappedImplementationType(classifier)){
				return EmfClassifierUtil.getMappedImplementationType(classifier);
			}
		}
		Namespace container = EmfElementFinder.getNearestNamespace(ne);
		String path = packagePathname(container);
		String name = NameConverter.toJavaVariableName(ne.getName());
		return path + "." + NameConverter.capitalize(name);
	}
	private static void addParentsToPath(Namespace c,StringBuilder path){
		Namespace parent = (Namespace) c.getOwner();
		if(parent != null){
			if(parent instanceof Package && EmfPackageUtil.hasMappedImplementationPackage((Package) parent)){
				path.append(EmfPackageUtil.getMappedImplementationPackage(((Package) parent)));
			}else{
				addParentsToPath(parent, path);
				path.append(NameConverter.toJavaVariableName(parent.getName().toLowerCase()));
			}
			path.append(".");
		}
	}
}
