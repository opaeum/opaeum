package org.opaeum.java.metamodel;

import java.util.Set;

import org.opaeum.java.metamodel.generated.OJElementGEN;

public abstract class OJElement extends OJElementGEN{
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
	public static void main(String[] args){
		replace("asdf", "asdf");
		replace("asdf.", "asdf");
		replace(".asdf", "asdf");
		replace("<asdf>", "asdf");
		replace(" asdf", "asdf");
		replace("asdf)", "asdf");
		replace("(asdf", "asdf");
		replace("(asdf)", "asdf");
		replace("asdf}", "asdf");
		replace("{asdf;", "asdf");
	}
	protected static void replace(String asdf,String suffix){
		System.out.println(asdf.replaceAll("\\b" + suffix + "\\b", "qwer"));
	}
	public abstract void renameAll(Set<OJPathName> match,String suffix);
	protected static String replaceAll(String from,Set<OJPathName> match,String suffix){
		if(from != null && from.length() > 0){
			for(OJPathName ojPathName:match){
//				if(Character.isLowerCase(ojPathName.getLast().charAt(0))){
//					from = from.replaceAll("([\\(<])(" + ojPathName.getLast() + ")([>\\)])", "$1"+ojPathName.getLast() + suffix+"$3");
//				}else{
					from = from.replaceAll("\\b" + ojPathName.getLast() + "\\b", ojPathName.getLast() + suffix);
//				}
			}
		}
		return from;
	}
}