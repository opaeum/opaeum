package org.opaeum.metamodel.bpm;

import org.eclipse.uml2.uml.INakedElement;

public interface INakedDefinedResponsibility extends INakedElement{
	INakedResponsibilityDefinition getTaskDefinition();

}
