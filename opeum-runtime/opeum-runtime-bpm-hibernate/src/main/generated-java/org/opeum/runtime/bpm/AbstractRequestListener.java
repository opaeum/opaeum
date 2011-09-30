package org.opeum.runtime.bpm;

public interface AbstractRequestListener {
	public void onAbstractRequestComplete(String nodeInstance, AbstractRequest completedProcess);
	
	public void onAbstractRequestUnhandledException(String nodeInstance, Object exception, AbstractRequest completedProcess);

}