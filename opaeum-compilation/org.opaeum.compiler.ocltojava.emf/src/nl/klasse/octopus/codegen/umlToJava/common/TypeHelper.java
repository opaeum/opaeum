/*
 * Created on Jun 4, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.common;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.eclipse.uml2.uml.Classifier;

/**
 * TypeHelper : 
 */
public class TypeHelper {
	/**
	 * 
	 */
	public TypeHelper() {
		super();
	}

	static public String valueToListElem(Classifier elementType, String expStr) {
		ClassifierMap mapper = new ClassifierMap(elementType);
		if (mapper.javaTypePath().getLast().equals("int")) {
			return "new Integer(" + expStr + ")";	
		} else if (mapper.javaTypePath().getLast().equals("float")) {
			return "new Float(" + expStr + ")";	
		} else if (mapper.javaTypePath().getLast().equals("boolean")) {
			return "new Boolean(" + expStr + ")";	
		} else {
			return expStr;	
		}
	}

	/**
	 * @param classifier
	 * @param string
	 * @return
	 */
	static public String listElemToValue(Classifier elementType, String expStr) {
		ClassifierMap mapper = new ClassifierMap(elementType);
		if (mapper.javaTypePath().getLast().equals("int")) {
			return expStr + ".intValue()";	
		} else if (mapper.javaTypePath().getLast().equals("float")) {
			return expStr + ".floatValue()";	
		} else if (mapper.javaTypePath().getLast().equals("boolean")) {
			return expStr + ".booleanValue()";	
		} else {
			return expStr;	
		}
	}

	/**
	 * @param classifier
	 * @param string
	 * @return
	 */
	static public String stringToValue(Classifier elementType, String expStr) {
		ClassifierMap mapper = new ClassifierMap(elementType);
		if (mapper.javaTypePath().getLast().equals("int")) {
			return "Integer.valueOf(" + expStr + ").intValue()";	
		} else if (mapper.javaTypePath().getLast().equals("float")) {
			return "Float.valueOf(" + expStr + ").floatValue()";	
		} else if (mapper.javaTypePath().getLast().equals("boolean")) {
			return "Boolean.valueOf(" + expStr + ").booleanValue()";	
		} else {
			return expStr;	
		}
	}


}
