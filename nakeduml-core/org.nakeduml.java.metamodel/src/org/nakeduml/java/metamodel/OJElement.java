package org.nakeduml.java.metamodel;

import org.nakeduml.java.metamodel.generated.OJElementGEN;

public class OJElement extends OJElementGEN{
	/******************************************************
	 * The constructor for this classifier.
	 *******************************************************/
	public OJElement(){
		super();
	}
	/******************************************************
	 * The following operations are the implementations of the operations defined for this classifier.
	 *******************************************************/
	public String toJavaString(){
		return "";
	}
	/******************************************************
	 * End of getters and setters.
	 *******************************************************/
	public void copyDeepInfoInto(OJElement copy){
		super.copyInfoInto(copy);
	}
	public int hashCode(){
		return getName().hashCode();
	}
	public boolean equals(Object other){
		if(getClass().isInstance(other)){
			OJElement e = (OJElement) other;
			if(e.getName() == null || e.getName().trim().isEmpty() || getName() == null || getName().trim().isEmpty()){
				return e == this;
			}else if(e.getName().equals(getName())){
				return true;
			}
		}
		return false;
	}
}