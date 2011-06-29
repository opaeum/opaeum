package net.sf.nakeduml.metamodel.statemachines;
import java.io.Serializable;
public enum StateKind implements Serializable {
	INITIAL("initial"), FINAL("final"), SHALLOW_HISTORY("shallowHistory"), COMPOSITE("composite"), ORTHOGONAL(
			"orthogonal"), JUNCTION("junction"), CHOICE("choice"), FORK("fork"), JOIN("join"), DEEP_HISTORY(
			"deep_history"), SIMPLE("simple");
	private String name;
	StateKind(String name) {
		this.name = name;
	}
	public static StateKind resolve(String name) {
		if (INITIAL.getName().equals(name)) {
			return INITIAL;
		} else if (FINAL.getName().equals(name)) {
			return FINAL;
		} else if (SHALLOW_HISTORY.getName().equals(name)) {
			return SHALLOW_HISTORY;
		} else if (COMPOSITE.getName().equals(name)) {
			return COMPOSITE;
		} else if (ORTHOGONAL.getName().equals(name)) {
			return ORTHOGONAL;
		} else if (JUNCTION.getName().equals(name)) {
			return JUNCTION;
		} else if (CHOICE.getName().equals(name)) {
			return CHOICE;
		} else if (FORK.getName().equals(name)) {
			return FORK;
		} else if (JOIN.getName().equals(name)) {
			return JOIN;
		} else if (DEEP_HISTORY.getName().equals(name)) {
			return DEEP_HISTORY;
		} else if (SIMPLE.getName().equals(name)) {
			return SIMPLE;
		}
		return null;
	}
	public boolean isRestingState() {
		return isFinal() || isSimple() || isComposite() || isOrthogonal();
	}
	public boolean isInitial() {
		return getValue() == INITIAL.getValue();
	}
	public boolean isFinal() {
		return getValue() == FINAL.getValue();
	}
	public boolean isShallowHistory() {
		return getValue() == SHALLOW_HISTORY.getValue();
	}
	public boolean isComposite() {
		return getValue() == COMPOSITE.getValue();
	}
	public boolean isSimple() {
		return getValue() == SIMPLE.getValue();
	}
	public boolean isJunction() {
		return getValue() == JUNCTION.getValue();
	}
	public boolean isChoice() {
		return getValue() == CHOICE.getValue();
	}
	public boolean isFork() {
		return getValue() == FORK.getValue();
	}
	public boolean isJoin() {
		return getValue() == JOIN.getValue();
	}
	public boolean isOrthogonal() {
		return getValue() == ORTHOGONAL.getValue();
	}
	public boolean isDeepHistory() {
		return getValue() == DEEP_HISTORY.getValue();
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return this.ordinal();
	}
}
