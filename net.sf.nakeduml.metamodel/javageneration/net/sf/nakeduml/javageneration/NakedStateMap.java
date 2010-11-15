package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.metamodel.statemachines.INakedState;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;

public class NakedStateMap extends StateMap {

	private INakedState state;
	public NakedStateMap(INakedState s) {
		super(s);
		this.state=s;
	}
	public String getFireTimersMethod() {
		return "fireTimersFor" + state.getMappingInfo().getJavaName();
	}
	public String getCancelTimersMethod() {
		return "cancelTimersFor" + state.getMappingInfo().getJavaName();
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
	public String getDoActivityMethod() {
		return "onDoOf"+state.getMappingInfo().getJavaName();
	}

}
