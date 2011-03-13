package org.nakeduml.runtime.domain;


public interface ActiveEntity extends ActiveObject{
	void startClassifierBehavior();
	AbstractProcess getClassifierBehavior();
}
