package net.sf.nakeduml.emf.extraction;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;

public class StereotypesHelper {
	public static boolean hasStereotype(Element c, String string) {
		String lowerCase = string.toLowerCase();
		if (getStereotype(c, lowerCase) != null) {
			return true;
		}
		for (Object o : c.getKeywords()) {
			if (o.toString().toLowerCase().equals(lowerCase)) {
				return true;
			}
		}
		return false;
	}

	public static Stereotype getStereotype(Element c, String... lowerCase) {
		for (String name : lowerCase) {
			name = name.toLowerCase();
			Iterator<Stereotype> iter = c.getAppliedStereotypes().iterator();
			Stereotype s = null;
			while (iter.hasNext()) {
				s = (Stereotype) iter.next();
				if (s.getName().toLowerCase().endsWith(name)) {
					return s;
				}
			}
		}
		return null;
	}

	public static boolean hasStereotype(Element c, String... possibleNames) {
		for (String name : possibleNames) {
			if (hasStereotype(c, name)) {
				return true;
			}
		}
		return false;
	}

	public static Object getValue(Element element,String st,String attr){
		for(Stereotype stereotype:element.getAppliedStereotypes()){
			if(stereotype.getName().equalsIgnoreCase(st)){
				return element.getValue(stereotype, attr);
			}
		}
		return null;
	}
}
