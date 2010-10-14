package net.sf.nakeduml.metamodel.statemachines;
import java.io.Serializable;
public enum TransitionKind implements Serializable {
	INTERNAL, LOCAL, EXTERNAL;
	public boolean isInternal() {
		return this == INTERNAL;
	}
	public boolean isLocal() {
		return this == LOCAL;
	}
	public boolean isExternal() {
		return this == EXTERNAL;
	}
	public String getName() {
		return this.name();
	}
}
