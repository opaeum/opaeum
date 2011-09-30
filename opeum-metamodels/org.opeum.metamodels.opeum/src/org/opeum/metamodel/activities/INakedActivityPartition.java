package org.opeum.metamodel.activities;
import org.opeum.metamodel.actions.ITargetElement;
import org.opeum.metamodel.core.INakedElement;
public interface INakedActivityPartition extends INakedElement,ITargetElement{
	INakedElement getRepresents();
	void setRepresents(INakedElement e);
}
