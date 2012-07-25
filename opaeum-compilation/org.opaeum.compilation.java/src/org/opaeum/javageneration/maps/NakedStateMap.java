package org.opaeum.javageneration.maps;

import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;

import org.eclipse.uml2.uml.Vertex;


public class NakedStateMap extends StateMap {

	private Vertex state;
	public NakedStateMap(Vertex s) {
		super(s);
		this.state=s;
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
