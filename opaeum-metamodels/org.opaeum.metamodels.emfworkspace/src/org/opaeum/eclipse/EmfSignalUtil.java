package org.opaeum.eclipse;

import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;

public class EmfSignalUtil{
	public static Integer getListenerPoolSize(Signal s){
		for(Stereotype stereotype:s.getAppliedStereotypes()){
			if(stereotype.getMember("listenerPoolSize") != null){
				Number n = (Number) s.getValue(stereotype, "listenerPoolSize");
				if(n != null){
					return new Integer(n.intValue());
				}
			}
		}
		return null;
	}
}
