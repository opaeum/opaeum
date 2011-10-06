package org.opaeum.metamodel.commonbehaviors;

import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;

public interface INakedEvent extends INakedElement,INakedElementOwner{
	INakedClassifier getContext();
}