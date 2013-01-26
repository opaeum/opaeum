package org.opaeum.runtime.domain;

import java.util.Collection;

public class FailedConstraintsException extends RuntimeException {
	private static final long serialVersionUID = 9146993200774829479L;
	Collection<FailedConstraint> failedConstraints;
	boolean pre;

	public FailedConstraintsException(boolean pre, Collection<FailedConstraint> failedConstraints) {
		super();
		this.failedConstraints = failedConstraints;
		this.pre=pre;
	}

	public Collection<FailedConstraint> getFailedConstraints() {
		return failedConstraints;
	}
	public boolean getPre(){
		return pre;
	}
	
}
