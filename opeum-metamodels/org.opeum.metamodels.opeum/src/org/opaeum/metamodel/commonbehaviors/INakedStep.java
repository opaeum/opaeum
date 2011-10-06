package org.opeum.metamodel.commonbehaviors;

import nl.klasse.octopus.expressions.internal.types.PathName;

import org.opeum.metamodel.core.INakedElement;

public interface INakedStep extends INakedElement{
	PathName getStatePath();
}
