package org.opeum.javageneration;

import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;

import org.opeum.metamodel.statemachines.INakedState;


public class NakedStateMap extends StateMap {

	private INakedState state;
	public NakedStateMap(INakedState s) {
		super(s);
		this.state=s;
	}
	public String getOnEntryMethod() {
		return "onEntryOf"+state.getMappingInfo().getJavaName();
	}
	public String getOnExitMethod() {
		return "onExitOf"+state.getMappingInfo().getJavaName();
	}
	public INakedState getState(){
		return state;
	}


}
