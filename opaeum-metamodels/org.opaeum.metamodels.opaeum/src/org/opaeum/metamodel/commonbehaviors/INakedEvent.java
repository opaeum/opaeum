package org.opeum.metamodel.commonbehaviors;

import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;

public interface INakedEvent extends INakedElement,INakedElementOwner{
	INakedClassifier getContext();
}