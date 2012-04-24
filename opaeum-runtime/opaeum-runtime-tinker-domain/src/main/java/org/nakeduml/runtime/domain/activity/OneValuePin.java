package org.nakeduml.runtime.domain.activity;


public abstract class OneValuePin<O> extends ValuePin<O, SingleObjectToken<O>> {

	public OneValuePin() {
		super();
	}

	protected abstract O getValue();

	@Override
	protected int countNumberOfElementsOnTokens() {
		return getInTokens().size();
	}	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nValuePin //TODO");
		// sb.append(this.nodeStat.toString());
		return sb.toString();
	}

}
