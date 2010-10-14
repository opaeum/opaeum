package util;

import java.util.Collection;

public class FailedConstraintsException extends Exception {
	private Boolean pre;
	private Collection<String> failedConstraints;

	/** Constructor for FailedConstraintsException
	 * 
	 * @param pre 
	 * @param failedConstraints 
	 */
	public FailedConstraintsException(Boolean pre, Collection<String> failedConstraints) {
		this.failedConstraints=failedConstraints;
		this.pre=pre;
	}

	public Collection<String> getFailedConstraints() {
		return failedConstraints;
	}
	
	public Boolean getPre() {
		return pre;
	}
	
	public void setFailedConstraints(Collection<String> failedConstraints) {
		this.failedConstraints=failedConstraints;
	}
	
	public void setPre(Boolean pre) {
		this.pre=pre;
	}

}