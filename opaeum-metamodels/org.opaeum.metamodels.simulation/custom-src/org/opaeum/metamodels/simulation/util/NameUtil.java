package org.opaeum.metamodels.simulation.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Slot;

public class NameUtil{
	public static String getQualifiedName(Element e){
		EObject o = e;
		StringBuilder sb = new StringBuilder();
		appendNames(o, sb);
		return sb.toString();
	}
	private static void appendNames(EObject o,StringBuilder sb){
		if(o.eContainer() != null){
			appendNames(o.eContainer(), sb);
			sb.append("::");
		}
		if(o instanceof Slot && ((Slot) o).getDefiningFeature() != null){
			sb.append(((Slot) o).getDefiningFeature().getName());
		}else if(o instanceof NamedElement){
			sb.append(((NamedElement) o).getName());
		}else{
			sb.append(o.hashCode());
		}
		
	}
}
