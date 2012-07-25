package org.eclipse.uml2.uml;

public enum ActivityKind {
	PROCESS, SIMPLE_SYNCHRONOUS_METHOD, COMPLEX_SYNCHRONOUS_METHOD;
	public boolean isSimpleSynchronousMethod() {
		return this == SIMPLE_SYNCHRONOUS_METHOD;
	}

	public boolean isProcess() {
		return this == PROCESS;
	}
}
