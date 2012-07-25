package org.eclipse.uml2.uml;
public interface INakedActivityPartition extends INakedElement,ITargetElement{
	INakedElement getRepresents();
	void setRepresents(INakedElement e);
}
