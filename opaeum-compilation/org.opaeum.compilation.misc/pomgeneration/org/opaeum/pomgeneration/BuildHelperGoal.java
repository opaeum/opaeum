package org.opaeum.pomgeneration;

public enum BuildHelperGoal {
	ADD_TEST_RESOURCE("generate-resources", "add-test-resource", false),
	ADD_TEST_SOURCE("generate-sources", "add-test-source", true),
	ADD_SOURCE("generate-sources", "add-source", false),
	ADD_RESOURCE("generate-resources", "add-resource", true);
	String phase;
	String goal;
	private boolean isSource;

	private BuildHelperGoal(String phase, String goal, boolean isSource) {
		this.phase = phase;
		this.goal = goal;
		this.isSource = isSource;
	}

	public String getPhase() {
		return phase;
	}

	public String getGoal() {
		return goal;
	}

	public boolean isSource() {
		return isSource;
	}
}