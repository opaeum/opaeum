package net.sf.nakeduml.metamodel.activities;
import net.sf.nakeduml.metamodel.actions.ITargetElement;
import net.sf.nakeduml.metamodel.core.INakedElement;
public interface INakedActivityPartition extends INakedElement,ITargetElement{
	INakedElement getRepresents();
	void setRepresents(INakedElement e);
}
