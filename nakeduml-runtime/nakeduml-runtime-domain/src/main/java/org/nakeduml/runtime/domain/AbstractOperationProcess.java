package org.nakeduml.runtime.domain;

public interface AbstractOperationProcess extends AbstractRequestedWork,AbstractProcess{
	ProcessRequest getProcessRequest();
}
