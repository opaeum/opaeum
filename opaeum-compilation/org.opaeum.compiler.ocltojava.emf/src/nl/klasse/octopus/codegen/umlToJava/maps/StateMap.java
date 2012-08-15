package nl.klasse.octopus.codegen.umlToJava.maps;

import nl.klasse.tools.common.StringHelpers;

import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class StateMap extends PackageableElementMap {
	private Vertex state = null;

	public StateMap(OJUtil ojUtil, Vertex s) {
		super(ojUtil,s);
		state = s;
	}
	public String javaFieldName(){
		return StringHelpers.replaceAllSubstrings(EmfStateMachineUtil.getStatePath( state), "::", "_").toString();
	}

	public String getter(){
		return "get" + NameConverter.capitalize(javaFieldName());
	}

	
	public OJPathName javaType(){
		return ojUtil.classifierPathname(state);
	}
	
	public String getOnEntryMethod() {
		return "onEntryOf"+state.getName();
	}
	public String getOnExitMethod() {
		return "onExitOf"+state.getName();
	}
	public Vertex getState(){
		return state;
	}
}
