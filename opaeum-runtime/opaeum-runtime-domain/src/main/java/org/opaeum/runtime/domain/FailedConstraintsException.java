package org.opaeum.runtime.domain;

import java.util.Map;

public class FailedConstraintsException extends RuntimeException {
	private static final long serialVersionUID = 9146993200774829479L;
	Map<String,String> failedConstraints;
	boolean pre;

	public FailedConstraintsException(boolean pre, Map<String,String> failedConstraints) {
		super();
		this.failedConstraints = failedConstraints;
		this.pre=pre;
	}

	public Map<String,String> getFailedConstraints() {
		return failedConstraints;
	}
	public boolean getPre(){
		return pre;
	}
	
}
