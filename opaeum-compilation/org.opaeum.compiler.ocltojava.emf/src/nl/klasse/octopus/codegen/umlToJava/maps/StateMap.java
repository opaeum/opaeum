package nl.klasse.octopus.codegen.umlToJava.maps;

import nl.klasse.tools.common.StringHelpers;

import org.eclipse.uml2.uml.State;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.java.metamodel.OJPathName;

public class StateMap extends PackageableElementMap {
	private State myState = null;

	public StateMap(State s) {
		super(s);
		myState = s;
	}

	public String javaFieldName(){
		return StringHelpers.replaceAllSubstrings(EmfStateMachineUtil.getStatePath( myState), "::", "_").toString();
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
