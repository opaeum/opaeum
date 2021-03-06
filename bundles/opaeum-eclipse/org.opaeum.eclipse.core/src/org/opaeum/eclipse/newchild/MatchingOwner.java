package org.opaeum.eclipse.newchild;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

public class MatchingOwner{
	EClass eClass;
	String[] stereotypes;
	public MatchingOwner(EClass eClass,String...stereotypes){
		super();
		this.eClass = eClass;
		this.stereotypes = stereotypes;
	}
	public boolean matches(EObject e){
		if(eClass.equals(e.eClass())){
			if(this.stereotypes.length == 0){
				return true;
			}else if(e instanceof Element){
				for(Stereotype st:((Element) e).getAppliedStereotypes()){
					for(String stName:this.stereotypes){
						if(st.getName().equals(stName)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
