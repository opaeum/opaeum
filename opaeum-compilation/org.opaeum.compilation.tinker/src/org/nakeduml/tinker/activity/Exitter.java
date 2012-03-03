package org.nakeduml.tinker.activity;

public class Exitter {
	private boolean b;

	public Exitter(boolean b) {
		super();
		this.b = b;
	}

	public boolean shouldExit() {
		return b;
	}

	public void setExit(boolean b) {
		this.b = b;
	}
	
}
