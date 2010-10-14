package net.sf.nakeduml.metamodel.actions;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
public interface INakedSendObjectAction extends INakedInvocationAction{
	INakedInputPin getObject();
	void setRequest(INakedInputPin pin);
}