package net.sf.nakeduml.util;

public interface ActiveEntity {
	void startClassifierBehavior();
	AbstractProcess getClassifierBehavior();
	boolean processSignal(AbstractSignal signal);
}
