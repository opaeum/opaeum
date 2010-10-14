/* (c) Copyright 2003, Klasse Objecten
 */
package nl.klasse.octopus.model;

/** VisibilityKind represents the different visibilitiet in UML: public, private and protected.
 * It is a type safe enumeration according to item 21 in "Effective Java" by
 * Joshua Bloch.
 *
 * @author anneke
 * @version $Id: VisibilityKind.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public class VisibilityKind {
	public static final VisibilityKind NONE = new VisibilityKind("no visibility indication");
	public static final VisibilityKind PUBLIC = new VisibilityKind("public");
	public static final VisibilityKind PRIVATE = new VisibilityKind("private");
	public static final VisibilityKind PROTECTED = new VisibilityKind("protected");

	private static VisibilityKind featureDefault = PUBLIC;
	private static VisibilityKind operationDefault = PUBLIC;

	private final String name;	

	/**
	 * Constructor for VisibilityKind.
	 */
	private VisibilityKind(String name) {
		this.name = name;
	}
	
	public String toString() { 
		if (name == "public") return "+";
		if (name == "private") return "-";
		if (name == "protected") return "#";
		return "";		
	}

    static public VisibilityKind getMetaType(String name ){
        if ( name.equals( "public" ))  return PUBLIC; 
		if ( name.equals( "private" )) return PRIVATE; 
		if ( name.equals( "protected" )) return PROTECTED; 
		return NONE;
    } 
    
	static public void setFeatureDefault(String def){
		featureDefault = getMetaType(def);
	}
	
	static public void setOperationDefault(String def){
		operationDefault = getMetaType(def);
	}
	/**
	 * @return
	 */
	static public VisibilityKind getFeatureDefault() {
		return featureDefault;
	}

	/**
	 * @return
	 */
	static public VisibilityKind getOperationDefault() {
		return operationDefault;
	}

}
