package org.nakeduml.runtime.domain;


public interface IActiveEntity extends IActiveObject,IPersistentObject{
	void startClassifierBehavior();
	IProcessObject getClassifierBehavior();
}
