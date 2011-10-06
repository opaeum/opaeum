package org.opeum.runtime.persistence;


public interface ConversationalPersistence extends AbstractPersistence{
	void close();
	void flush();
	
}
