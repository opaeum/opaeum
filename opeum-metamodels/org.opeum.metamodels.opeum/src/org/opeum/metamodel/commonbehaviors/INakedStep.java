package org.opeum.metamodel.commonbehaviors;

import org.opeum.metamodel.core.INakedElement;
import nl.klasse.octopus.expressions.internal.types.PathName;

public interface INakedStep extends INakedElement{
	PathName getStatePath();
}
