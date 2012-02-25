package org.opaeum.runtime.domain;

public class ExceptionHolder extends RuntimeException {
	private static final long serialVersionUID = 5555525547023115058L;
	private Object source;
	private String parameterName;
	private Object exception;

	public ExceptionHolder(Object source, String parameterName, Object exception) {
		this.source = source;
		this.parameterName = parameterName;
		this.exception = exception;
	}

	public String getParameterName() {
		return this.parameterName;
	}

	public Object getValue() {
		return this.exception;
	}
	public boolean isParameter(String parameterName){
		return this.parameterName.equals(parameterName);
	}
	public Object getSource() {
		return this.source;
	}
}
