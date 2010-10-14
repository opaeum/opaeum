package net.sf.nakeduml.metamodel.activities;

import net.sf.nakeduml.metamodel.core.INakedParameter;

/**
 * From Octopus' perspective a ParameterNode is an attribute of the Activity -it needs to be persisted -it is accessible from OCL
 * 
 * @author barnar_a
 * 
 */
public interface INakedParameterNode extends INakedObjectNode{
	INakedParameter getParameter();
	void setParameter(INakedParameter p);
	boolean isException();
}
