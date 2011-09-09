package org.nakeduml.runtime.domain;


public interface IActiveEntity extends IActiveObject{
	void startClassifierBehavior();
	IProcessObject getClassifierBehavior();
}
