package org.opaeum.eclipse;

import org.eclipse.uml2.uml.Element;

public class EmfElementUtil{
	public static boolean isMarkedForDeletion(Element e){
		return e.eResource()==null;
	}

	public static String getDocumentation(Element elem){
		if(elem.getOwnedComments().size()>0){
			return elem.getOwnedComments().get(0).getBody();
		}
		return null;
	}
}
