package net.sf.nakeduml.util;
public class ExceptionHolder extends RuntimeException {
	private static final long serialVersionUID = 5555525547023115058L;
	Object source;
	Object context;
	Object exception;
	
	public ExceptionHolder(Object source, Object context, Object exception) {
		this.source = source;
		this.context = context;
		this.exception = exception;
	}
	public Object getContext() {
		return this.context;
	}
	public Object getException() {
		return this.exception;
	}
	public Object getSource() {
		return this.source;
	}
}
