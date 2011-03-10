package jbpm.jbpm.nodedefinition;

public interface EISConnection {
	String execute(String command);

	void destroy();

	boolean isValid();
}
