package net.sf.nakeduml.util;

public interface ActiveEntity extends ActiveObject{
	void startClassifierBehavior();
	AbstractProcess getClassifierBehavior();
}
