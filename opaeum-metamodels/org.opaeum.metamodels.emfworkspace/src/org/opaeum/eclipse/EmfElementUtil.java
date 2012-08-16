package org.opaeum.eclipse;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;

public class EmfElementUtil{
	public static boolean isMarkedForDeletion(Element e){
		return !(e instanceof EmfWorkspace) && e.eResource() == null;
	}
	public static String getDocumentation(Element elem){
		if(elem.getOwnedComments().size() > 0){
			return elem.getOwnedComments().get(0).getBody();
		}
		return null;
	}
	public static String getHumanName(NamedElement ne){
		for(EObject eObject:ne.getStereotypeApplications()){
			EStructuralFeature f = eObject.eClass().getEStructuralFeature("humanName");
			if(f != null){
				String hn = (String) eObject.eGet(f);
				if(hn != null && hn.length() > 0){
					return hn;
				}
			}
		}
		return NameConverter.separateWords(NameConverter.capitalize(ne.getName()));
	}
}
