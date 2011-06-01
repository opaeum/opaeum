package org.nakeduml.runtime.domain;

public interface ITaskInvocation extends IBusinessServiceInvocation{
	TaskRequest getTaskInstance();
}
