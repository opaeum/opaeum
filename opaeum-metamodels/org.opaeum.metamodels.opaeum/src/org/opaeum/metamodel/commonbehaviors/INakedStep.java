package org.opaeum.metamodel.commonbehaviors;

import nl.klasse.octopus.expressions.internal.types.PathName;

import org.opaeum.metamodel.core.INakedElement;

public interface INakedStep extends INakedElement{
	PathName getStatePath();
}
