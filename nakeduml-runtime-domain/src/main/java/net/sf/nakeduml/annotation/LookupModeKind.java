package net.sf.nakeduml.annotation;

import java.io.Serializable;

/**
 * Represents the various options the user interfaces has to select
 * 
 */
public enum LookupModeKind implements Serializable{
	/**
	 * Populates a listbox by reading all the instances of the type of the property/parameter from the database
	 */
	LISTALL,
	/**
	 * Populates a lisbox by retrieving all the instances of the type of the property/parameter that share a common composite with the owning
	 * type
	 */
	LISTCOMPOSITIONSIBLINGS,
	/**
	 * Populates a listbox by retrieving the results from a specified property
	 */
	LISTPROPERTY,
	/**
	 * TODO A popup window is displayed showing a list of results adhering to the specifications of the "criteria" userInteraction
	 */
	POPUP,
	/**
	 * TODO Populates a lisbox by retrieving all the instances of the type of the property/parameter that share a common composite with the
	 * user type
	 */
	LISTUSERSCOMPOSITIONSIBLINGS;
	public boolean isListAll(){
		return this == LISTALL;
	}
	public boolean isListProperty(){
		return this == LISTPROPERTY;
	}
	public boolean isPopup(){
		return this == POPUP;
	}
	public boolean isListCompositionSiblings(){
		
		return this==LISTCOMPOSITIONSIBLINGS;
	}
}