package net.sf.nakeduml.metamodel.actions;
import java.util.List;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
/**
 * Opaque actions are used only to assign arbitrary tasks to users. As such, it requires a target or a swimlane to
 * assign the task to a user or pool of users, hence IActionWithTarget. <BR>
 * It also needs to be persisted and linked to the controlling process. 
 * 
 * @author barnar_a
 * 
 */
public interface INakedOpaqueAction extends INakedCallAction{
	List<INakedInputPin> getInputValues();
	List<INakedOutputPin> getOutputValues();
	List<INakedPin> getPins();
}
