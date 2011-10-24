package org.opaeum.runtime.domain;

import java.util.Collection;

public class FailedConstraintsException extends RuntimeException {
	private static final long serialVersionUID = 9146993200774829479L;
	Collection<String> failedConstraints;
	boolean pre;

	public FailedConstraintsException(boolean pre, Collection<String> failedConstraints) {
		super();
		this.failedConstraints = failedConstraints;
		this.pre=pre;
	}

	public Collection<String> getFailedConstraints() {
		return failedConstraints;
	}
	public boolean getPre(){
		return pre;
	}
	
}
