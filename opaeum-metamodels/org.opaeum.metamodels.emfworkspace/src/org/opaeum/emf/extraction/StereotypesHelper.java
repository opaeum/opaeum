package org.opaeum.emf.extraction;

import java.awt.Desktop.Action;
import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil.StereotypeApplicationHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class StereotypesHelper{
	public static boolean hasStereotype(Element c,String string){
		String lowerCase = string.toLowerCase();
		if(getStereotype(c, lowerCase) != null){
			return true;
		}
		return hasKeyword(c, lowerCase);
	}
	public static boolean hasKeyword(Element c,String lowerCase){
		lowerCase = lowerCase.toLowerCase();
		for(Object o:c.getKeywords()){
			if(o.toString().toLowerCase().equals(lowerCase)){
				return true;
			}
		}
		EAnnotation ann = c.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
		if(ann != null){
			for(String string:ann.getDetails().keySet()){
				if(string.toLowerCase().equals(lowerCase)){
					return true;
				}
			}
		}
		return false;
	}
	public static Stereotype getStereotype(Element c,String...lowerCase){
		for(String name:lowerCase){
			name = name.toLowerCase();
			Iterator<Stereotype> iter = c.getAppliedStereotypes().iterator();
			Stereotype s = null;
			while(iter.hasNext()){
				s = (Stereotype) iter.next();
				if(s.getName().toLowerCase().endsWith(name)){
					if(!c.isStereotypeApplied(s)){
						System.out.println("Stereotype application corrupt:" + c + ":" + s);
						return null;
					}
					return s;
				}
			}
		}
		return null;
	}
	public static boolean hasStereotype(Element c,String...possibleNames){
		for(String name:possibleNames){
			if(hasStereotype(c, name)){
				return true;
			}
		}
		return false;
	}
	public static EAnnotation findOrCreateNumlAnnotation(Element v){
		for(EAnnotation eAnnotation:v.getEAnnotations()){
			if(eAnnotation.getSource() != null && eAnnotation.getSource().equals(StereotypeNames.NUML_ANNOTATION)){
				return eAnnotation;
			}
		}
		EAnnotation createEAnnotation = v.createEAnnotation(StereotypeNames.NUML_ANNOTATION);
		return createEAnnotation;
	}
	public static void applyStereotype(Element e1,Stereotype s){
		EObject e = e1;
		while(!(e.eContainer() instanceof org.eclipse.uml2.uml.Package)){
			e = e.eContainer();
		}
		Package p = (Package) e.eContainer();
		ProfileApplication profileApplication = p.getProfileApplication(s.getProfile(), true);
		if(profileApplication == null){
			p.getModel().applyProfile(s.getProfile());
		}
		ENamedElement appliedDefinition = profileApplication.getAppliedDefinition(s);
		if(appliedDefinition instanceof EClass){
			EClass eClass = (EClass) appliedDefinition;
			if(!eClass.isAbstract()){
				StereotypeApplicationHelper.INSTANCE.applyStereotype(e1, eClass);
			}
		}
	}
	public static EStructuralFeature findAttribute(EObject eObject,String string2){
		if(eObject == null){
			return null;
		}else{
			return eObject.eClass().getEStructuralFeature(string2);
		}
	}
	public static EAnnotation getNumlAnnotation(Element action){
		return action.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
	}
}
