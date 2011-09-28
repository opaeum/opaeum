package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedElement;
import nl.klasse.octopus.expressions.internal.types.PathName;

public interface INakedStep extends INakedElement{
	PathName getStatePath();
}
