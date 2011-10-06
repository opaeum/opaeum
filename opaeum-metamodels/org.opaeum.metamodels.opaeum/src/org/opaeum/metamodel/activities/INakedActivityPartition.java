package org.opaeum.metamodel.activities;
import org.opaeum.metamodel.actions.ITargetElement;
import org.opaeum.metamodel.core.INakedElement;
public interface INakedActivityPartition extends INakedElement,ITargetElement{
	INakedElement getRepresents();
	void setRepresents(INakedElement e);
}
