package net.sf.nakeduml.metamodel.actions;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
public interface INakedSendSignalAction extends INakedInvocationAction {
	void setSignal(INakedSignal signal);
	INakedSignal getSignal();
}