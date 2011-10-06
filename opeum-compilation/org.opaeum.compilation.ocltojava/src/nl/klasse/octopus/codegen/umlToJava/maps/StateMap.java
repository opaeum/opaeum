/*
 * Created on Jul 16, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.maps;

import nl.klasse.octopus.model.IState;
import nl.klasse.tools.common.StringHelpers;

import org.opeum.java.metamodel.OJPathName;

/**
 * StateMap : 
 */
public class StateMap extends PackageableElementMap {
	private IState myState = null;

	/**
	 * 
	 */
	public StateMap(IState s) {
		super(s);
		myState = s;
	}

	public String javaFieldName(){
		return StringHelpers.replaceAllSubstrings(myState.getStatePath().toString(), "::", "_");
	}

	public String getter(){
		return "get" + StringHelpers.firstCharToUpper(javaFieldName());
	}

	public String setter(){
		return "set" + StringHelpers.firstCharToUpper(javaFieldName());
	}
	
	public OJPathName javaType(){
		OJPathName result = StdlibMap.javaBooleanType;
		return result;
	}
	
	public String javaDefaultValue(){
		String result = "false";
		return result;
	}

}
