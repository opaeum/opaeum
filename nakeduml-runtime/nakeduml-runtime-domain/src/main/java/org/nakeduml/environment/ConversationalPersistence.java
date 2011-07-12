package org.nakeduml.environment;


public interface ConversationalPersistence extends AbstractPersistence{
	void close();
	void flush();
	
}
