package org.nakeduml.runtime.domain;


public interface IActiveEntity extends IActiveObject,AbstractEntity{
	void startClassifierBehavior();
	IProcessObject getClassifierBehavior();
}
