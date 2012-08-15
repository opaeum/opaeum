package org.opaeum.eclipse;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class EmfPackageUtil{
	public static String getIdentifier(Package p){
		for(Stereotype st:p.getAppliedStereotypes()){
			if(st.getAttribute("artifactIdentifier", null) != null){
				String s = (String) p.getValue(st, "artifactIdentifier");
				if(s != null && s.length() > 0){
					return s;
				}
			}
		}
		return p.eResource().getURI().trimFileExtension().lastSegment();
	}
	public static boolean isRootObject(Element o){
		return o instanceof Model || o instanceof Profile;
	}
	public static Collection<Package> getAllDependencies(Package p){
		Set<Package> result = new TreeSet<Package>(new ElementComparator());
		addImports(result, p);
		return result;
	}
	public static boolean isRootPackage(Package p){
		Object value = getValue(p, TagNames.IS_ROOT_PACKAGE);
		return Boolean.TRUE.equals(value);
	}
	public static boolean isSchema(Package p){
		Object value = getValue(p, TagNames.IS_SCHEMA);
		return Boolean.TRUE.equals(value);
	}
	public static boolean hasMappedImplementationPackage(Package p){
		Object value = getValue(p, TagNames.MAPPED_IMPLEMENTATION_PACKAGE);
		return value != null && value.toString().trim().length() > 0;
	}
	public static String getMappedImplementationPackage(Package p){
		Object value = getValue(p, TagNames.MAPPED_IMPLEMENTATION_PACKAGE);
		return value.toString().trim();
	}
	private static Object getValue(Package p,String tagName){
		for(EObject sa:p.getStereotypeApplications()){
			if(sa.eClass().getEStructuralFeature(tagName) != null){
				return sa.eGet(sa.eClass().getEStructuralFeature(tagName));
			}
		}
		return null;
	}
	private static Stereotype getAppropriateStereotype(Package p){
		Stereotype st = null;
		if(p instanceof Model && StereotypesHelper.hasStereotype(p, StereotypeNames.MODEL)){
			st = StereotypesHelper.getStereotype(p, StereotypeNames.MODEL);
		}else if(StereotypesHelper.hasStereotype(p, StereotypeNames.PACKAGE)){
			st = StereotypesHelper.getStereotype(p, StereotypeNames.PACKAGE);
		}
		return st;
	}
	private static void addImports(Set<Package> result,Package ro){
		if(result.contains(ro)){
			return;
		}else{
			result.add(ro);
			Collection<PackageImport> imports = ro.getPackageImports();
			for(PackageImport imp:imports){
				if(EmfPackageUtil.isRootObject(imp.getImportedPackage())){
					addImports(result, imp.getImportedPackage());
				}
			}
			Collection<ProfileApplication> pas = ro.getProfileApplications();
			for(ProfileApplication imp:pas){
				addImports(result, imp.getAppliedProfile());
			}
		}
	}
	public static boolean isRegeneratingLibrary(Model model){
		if(StereotypesHelper.hasStereotype(model, StereotypeNames.MODEL)){
			EObject sa = model.getStereotypeApplication(StereotypesHelper.getStereotype(model, StereotypeNames.MODEL));
			EStructuralFeature f = sa.eClass().getEStructuralFeature("modelType");
			if(f != null){
				EEnumLiteral value = (EEnumLiteral) sa.eGet(f);
				return value.getName().equals("REGENERATING_LIBRARY");
			}
		}
		return false;
	}
	public static String getFileName(Package ro){
		return ro.eResource().getURI().lastSegment();
	}
	public static boolean isLibrary(Model model){
		Set<String> ignore = new HashSet<String>();
		ignore.add("OpaeumSimpleTypes".toLowerCase());
		ignore.add("UMLPrimitiveTypes".toLowerCase());
		ignore.add("PrimitiveTypes".toLowerCase());
		ignore.add("JavaPrimitiveTypes".toLowerCase());
		ignore.add("OpaeumSimpleTypes".toLowerCase());
		if(ignore.contains(model.getName().toLowerCase())){
			return true;
		}else if(StereotypesHelper.hasStereotype(model, StereotypeNames.MODEL)){
			Stereotype st = StereotypesHelper.getStereotype(model, StereotypeNames.MODEL);
			EObject sa = model.getStereotypeApplication(st);
			EStructuralFeature f = sa.eClass().getEStructuralFeature("modelType");
			if(f == null){
				return false;
			}else{
				EEnumLiteral value = (EEnumLiteral) sa.eGet(f);
				return value.getName().equals("REFERENCED_LIBRARY");
			}
		}else{
			return StereotypesHelper.hasStereotype(model, "EPackage", "MetaModel");
		}
	}
}
